package bus_simulation;

import bus_simulation.Passenger.PASSENGER_TYPE;

public class BusStop {
	public static final double ARRIVAL_CHANCE = 0.30;
	public static final byte MIN_GROUP_SIZE = 2, MAX_GROUP_SIZE = 5;
	public static final double ELDERLY_CHANCE = 0.05;
	public static final double STUDENT_CHANCE = 0.10;

	public static final byte MIN_TIME = 2, MAX_TIME = 5;

//	public Bus currentBus = null;
	public final Passenger.List passengerList = new Passenger.List();
	public final String name;

	public final BusRoute parent;
	public final boolean isDepot;

	public BusStop next = null;
	public final byte timeToNext;

	public boolean isTail() {
		return next == null;
	}

	public BusStop(final BusRoute parent, final String name, final byte timeToNext) {
		this(parent, name, timeToNext, false);
	}

	public BusStop(final BusRoute parent, final String name, final byte timeToNext, final boolean isDepot) {
		this.parent = parent;
		this.name = name;
		this.timeToNext = timeToNext;
		this.isDepot = isDepot;
	}

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

	public static BusStop randomStop(final BusRoute parent) {
		return new BusStop(parent, StreetNames.getRandom(), (byte) Util.random(MIN_TIME, MAX_TIME));
	}

	public static BusStop createDepot(final BusRoute parent) {
		return new BusStop(parent, String.format("%s DEPOT", parent.name), (byte) Util.random(MIN_TIME, MAX_TIME),
				true);
	}

	@Override
	public String toString() {
		return String.format("%s -> %s", name, next != null ? next.name : "END");
	}
}
