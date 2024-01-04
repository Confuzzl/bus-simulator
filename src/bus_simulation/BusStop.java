package bus_simulation;

import bus_simulation.Passenger.PASSENGER_TYPE;

public class BusStop {
	static final double ARRIVAL_CHANCE = 0.30;
	static final byte MIN_GROUP_SIZE = 2, MAX_GROUP_SIZE = 5;
	static final double ELDERLY_CHANCE = 0.05;
	static final double STUDENT_CHANCE = 0.10;

	public Bus currentBus = null;
	public Passenger.List passengerList = new Passenger.List();

	final public static class Node extends BusStop {
		BusStop next = null;

		public void update() {
			if (!Util.chance(ARRIVAL_CHANCE)) {
				return;
			}
			for (int i = 0; i < Util.random(MIN_GROUP_SIZE, MAX_GROUP_SIZE); i++) {
				if (Util.chance(ELDERLY_CHANCE + BusStop.STUDENT_CHANCE)) {
					if (Util.chance(ELDERLY_CHANCE / (ELDERLY_CHANCE + BusStop.STUDENT_CHANCE))) {
						passengerList.add(PASSENGER_TYPE.ELDERLY);
					} else {
						passengerList.add(PASSENGER_TYPE.STUDENT);
					}
				} else {
					passengerList.add(PASSENGER_TYPE.ADULT);
				}
			}
		}
	}

	final public static class Tail extends BusStop {
	}
}
