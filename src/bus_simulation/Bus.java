package bus_simulation;

import bus_simulation.Passenger.PASSENGER_TYPE;

public class Bus {
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
	public STATUS status = STATUS.IN_PROGRESS;
	public byte timeLeft = 0;

	public Bus(BusRoute parent) {
		this.parent = parent;
		prevStop = parent.DEPOT;
		timeLeft = prevStop.timeToNext;
		nextStop = prevStop.next;
	}

	public int remainingCapacity() {
		return MAX_CAPACITY - passengerList.size();
	}

	public void update() {
		switch (status) {
		case IN_PROGRESS -> {
			timeLeft--;
			if (timeLeft == 0) {
				status = STATUS.ARRIVING;
			}
		}
		case ARRIVING -> {
			status = STATUS.AT_STOP;
		}
		case AT_STOP -> {
			if (nextStop.isTail()) {
				status = STATUS.AT_END;
				return;
			}
			setNext();
			status = STATUS.IN_PROGRESS;
		}
		case AT_END -> {
			passengerList.reset();
		}
		}
	}

	private void setNext() {
		prevStop = nextStop;
		timeLeft = prevStop.timeToNext;
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

	public void load(final BusStop stop) {
		if (stop.passengerList.size() <= remainingCapacity()) {
			passengerList.addAll(stop.passengerList);
			stop.passengerList.reset();
			return;
		}
		int elderlyToBoard = Math.clamp(stop.passengerList.ELDERLY_COUNT, 0, remainingCapacity());
		stop.passengerList.transferTo(passengerList, PASSENGER_TYPE.ELDERLY, elderlyToBoard);
		for (int i = 0; i < stop.passengerList.size(); i++) {
			try {
				stop.passengerList.transferTo(passengerList,
						i % 2 == 0 ? PASSENGER_TYPE.ADULT : PASSENGER_TYPE.STUDENT);
			} catch (final ArithmeticException e) {
				// if specific passenger count is 0
				stop.passengerList.transferTo(passengerList,
						i % 2 == 0 ? PASSENGER_TYPE.STUDENT : PASSENGER_TYPE.ADULT);
			}
			if (passengerList.size() == MAX_CAPACITY) {
				break;
			}
		}

		revenue += passengerList.totalRevenue();
	}
}
