package bus_simulation;

import java.io.FileWriter;
import java.io.IOException;

import bus_simulation.Passenger.PASSENGER_TYPE;

public class Bus {
	public FileWriter logger = null;

	public enum STATUS {
		IN_PROGRESS, ARRIVING, AT_STOP, AT_END;
	}

	public static final byte MAX_CAPACITY = 40;
	public static final double NO_EXIT_CHANCE = 0.10;

	public final BusRoute parent;

	public Passenger.List passengerList = new Passenger.List();
	public double revenue = 0;

	public BusStop prevStop = null;
	public BusStop nextStop = null;
	public STATUS status = STATUS.AT_STOP;
	public byte timeLeft = 0;

	public Bus(BusRoute parent) {
		this.parent = parent;
		prevStop = parent.DEPOT;
		timeLeft = parent.DEPOT.timeToNext();
		nextStop = parent.DEPOT.next;

		try {
			logger = new FileWriter(String.format("%s BUS.txt", parent.name));
		} catch (final IOException e) {
		}
	}

	public int remainingCapacity() {
		return MAX_CAPACITY - passengerList.size();
	}

	private void log(final String str) {
		try {
			logger.write(String.format("%d:\t%s\t%s\t%s\t%s\n", Simulation.TICKS, status, timeLeft, prevStop, str));
		} catch (final IOException e) {
		}
	}

	public void update() {
		switch (status) {
		case IN_PROGRESS -> {
			log("");
			timeLeft--;
			if (timeLeft == 0) {
				status = STATUS.ARRIVING;
			}
		}
		case ARRIVING -> {
			log("");
			status = STATUS.AT_STOP;
			setNext();
		}
		case AT_STOP -> {
			if (nextStop == null) {
				status = STATUS.AT_END;
				passengerList.reset();
				break;
			}
			status = STATUS.IN_PROGRESS;
			unload(prevStop);
			load(prevStop);
		}
		case AT_END -> {
			log("");
		}
		}
	}

	private void setNext() {
		prevStop = nextStop;
		timeLeft = prevStop.timeToNext();
		nextStop = nextStop.next;
	}

	public void unload(final BusStop stop) {
		if (Util.chance(0.1)) {
			return;
		}
		final int UNBOARDERS = Util.random(0, passengerList.size());
		for (int i = 0; i < UNBOARDERS; i++) {
			passengerList.removeRandom();
		}
	}

	private int combinedSize(final Passenger.List other) {
		return passengerList.size() + other.size();
	}

	private int combinedRemainingCapacity(final Passenger.List other) {
		return MAX_CAPACITY - combinedSize(other);
	}

	public void load(final BusStop stop) {
		final Passenger.List newPassengers = new Passenger.List();
		if (stop.passengerList.size() <= combinedRemainingCapacity(newPassengers)) {
			newPassengers.addAll(stop.passengerList);
			stop.passengerList.reset();
		} else {
			final int elderlyToBoard = Math.clamp(stop.passengerList.ELDERLY_COUNT, 0,
					combinedRemainingCapacity(newPassengers));
			stop.passengerList.transferTo(newPassengers, PASSENGER_TYPE.ELDERLY, elderlyToBoard);
			for (int i = 0; i < stop.passengerList.size(); i++) {
				if (combinedRemainingCapacity(newPassengers) <= 0) {
					break;
				}
				try {
					stop.passengerList.transferTo(newPassengers,
							i % 2 == 0 ? PASSENGER_TYPE.ADULT : PASSENGER_TYPE.STUDENT);
				} catch (final ArithmeticException e) {
					// if specific passenger count is 0
					stop.passengerList.transferTo(newPassengers,
							i % 2 == 0 ? PASSENGER_TYPE.STUDENT : PASSENGER_TYPE.ADULT);
				}
			}
		}
		revenue += newPassengers.totalRevenue();
		passengerList.addAll(newPassengers);
	}

	public void end() {
		try {
			logger.write(String.format("TOTAL REVENUE GENERATED: $%.2f\n", revenue));
			logger.close();
		} catch (final IOException e) {
		}
	}
}
