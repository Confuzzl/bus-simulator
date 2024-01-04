package bus_simulation;

public class Passenger {
	public static class List {
		public short ADULT_COUNT = 0;
		public short ELDERLY_COUNT = 0;
		public short STUDENT_COUNT = 0;

//		void addAll(final Collection<PASSENGER_TYPE> passengers) {
//			passengers.forEach((final PASSENGER_TYPE passenger) -> {
//				add(passenger);
//			});
//		}
		void addAll(final List passengerList) {
			ADULT_COUNT += passengerList.ADULT_COUNT;
			ELDERLY_COUNT += passengerList.ELDERLY_COUNT;
			STUDENT_COUNT += passengerList.STUDENT_COUNT;
		}

		void add(final PASSENGER_TYPE passenger) {
			switch (passenger) {
			case ADULT -> ADULT_COUNT++;
			case ELDERLY -> ELDERLY_COUNT++;
			case STUDENT -> STUDENT_COUNT++;
			}
		}

		void removeAll() {
			removeAllAdults();
			removeAllElderly();
			removeAllStudents();
		}

		void removeAdult() {
			removeAdult(1);
		}

		void removeAdult(final int n) {
			ADULT_COUNT -= n;
		}

		void removeAllAdults() {
			ADULT_COUNT = 0;
		}

		void removeElderly() {
			removeElderly(1);
		}

		void removeElderly(final int n) {
			ELDERLY_COUNT -= n;
		}

		void removeAllElderly() {
			ELDERLY_COUNT = 0;
		}

		void removeStudent() {
			removeStudent(1);
		}

		void removeStudent(final int n) {
			STUDENT_COUNT -= n;
		}

		void removeAllStudents() {
			STUDENT_COUNT = 0;
		}

		int size() {
			return ADULT_COUNT + ELDERLY_COUNT + STUDENT_COUNT;
		}
	}

	public enum PASSENGER_TYPE {
		ADULT(2.75), ELDERLY(1), STUDENT(0);

		public final double FARE;

		PASSENGER_TYPE(final double FARE) {
			this.FARE = FARE;
		}
	}
}
