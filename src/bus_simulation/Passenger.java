package bus_simulation;

public class Passenger {
	public static class List {
		public short ADULT_COUNT = 0;
		public short ELDERLY_COUNT = 0;
		public short STUDENT_COUNT = 0;

		public void addAll(final List passengerList) {
			add(PASSENGER_TYPE.ADULT, passengerList.ADULT_COUNT);
			add(PASSENGER_TYPE.ELDERLY, passengerList.ELDERLY_COUNT);
			add(PASSENGER_TYPE.STUDENT, passengerList.STUDENT_COUNT);
		}

		public void reset() {
			removeAll(PASSENGER_TYPE.ADULT);
			removeAll(PASSENGER_TYPE.ELDERLY);
			removeAll(PASSENGER_TYPE.STUDENT);
		}

		public void add(final PASSENGER_TYPE passenger) {
			add(passenger, 1);
		}

		public void add(final PASSENGER_TYPE passenger, final int n) {
			switch (passenger) {
			case ADULT -> ADULT_COUNT += n;
			case ELDERLY -> ELDERLY_COUNT += n;
			case STUDENT -> STUDENT_COUNT += n;
			}
		}

		public void remove(final PASSENGER_TYPE passenger) {
			remove(passenger, 1);
		}

		public void remove(final PASSENGER_TYPE passenger, final int n) {
			if (switch (passenger) {
			case ADULT -> ADULT_COUNT;
			case ELDERLY -> ELDERLY_COUNT;
			case STUDENT -> STUDENT_COUNT;
			} - n < 0) {
				throw new ArithmeticException();
			}
			add(passenger, -n);
		}

		public void remove(final List other) {
			remove(PASSENGER_TYPE.ADULT, other.ADULT_COUNT);
			remove(PASSENGER_TYPE.ELDERLY, other.ELDERLY_COUNT);
			remove(PASSENGER_TYPE.STUDENT, other.STUDENT_COUNT);
		}

		public void removeAll(final PASSENGER_TYPE passenger) {
			remove(passenger, switch (passenger) {
			case ADULT -> ADULT_COUNT;
			case ELDERLY -> ELDERLY_COUNT;
			case STUDENT -> STUDENT_COUNT;
			});
		}

		public PASSENGER_TYPE getRandom() {
			final double ADULT_CHANCE = ADULT_COUNT / (double) size(), ELDERLY_CHANCE = ELDERLY_COUNT / (double) size(),
					STUDENT_CHANCE = STUDENT_COUNT / (double) size();
			if (Util.chance(ADULT_CHANCE + ELDERLY_CHANCE)) {
				if (Util.chance(ADULT_CHANCE / (ADULT_CHANCE + ELDERLY_CHANCE))) {
					return PASSENGER_TYPE.ADULT;
				}
				return PASSENGER_TYPE.ELDERLY;
			}
			return PASSENGER_TYPE.STUDENT;
		}

		public void transferTo(final List other, final PASSENGER_TYPE passenger) {
			transferTo(other, passenger, 1);
		}

		public void transferTo(final List other, final PASSENGER_TYPE passenger, final int n) {
			remove(passenger, n); // throws if 0
			other.add(passenger, n);
		}

		public int size() {
			return ADULT_COUNT + ELDERLY_COUNT + STUDENT_COUNT;
		}

		public double totalRevenue() {
			return ADULT_COUNT * PASSENGER_TYPE.ADULT.FARE + ELDERLY_COUNT * PASSENGER_TYPE.ELDERLY.FARE
					+ STUDENT_COUNT * PASSENGER_TYPE.STUDENT.FARE;
		}

		@Override
		public String toString() {
			return String.format("[%d ADULTS, %d ELDERLY, %d STUDENTS]", ADULT_COUNT, ELDERLY_COUNT, STUDENT_COUNT);
		}

	}

	public enum PASSENGER_TYPE {
		ADULT(2.75), ELDERLY(1), STUDENT(0);

		public final double FARE;

		private PASSENGER_TYPE(final double FARE) {
			this.FARE = FARE;
		}
	}
}
