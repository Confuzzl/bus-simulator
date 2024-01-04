package bus_simulation;

import bus_simulation.Passenger.PASSENGER_TYPE;

public class Bus {
	public static final byte MAX_CAPACITY = 40;
	public static final double NO_EXIT_CHANCE = 0.10;

	public Passenger.List passengerList = new Passenger.List();

	public int remainingCapacity() {
		return MAX_CAPACITY - passengerList.size();
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
				// if passenger count is 0
				stop.passengerList.transferTo(passengerList,
						i % 2 == 0 ? PASSENGER_TYPE.STUDENT : PASSENGER_TYPE.ADULT);
			}
			if (passengerList.size() == MAX_CAPACITY) {
				break;
			}
		}
	}
}
