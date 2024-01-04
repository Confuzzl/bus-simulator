package bus_simulation;

public class BusRoute {
	static final byte MIN_STOPS = 10, MAX_STOPS = 20;
//	static final LinkedList<BusStop> ROUTES = new LinkedList<BusStop>();
	final BusStop HEAD_STOP = BusStop.randomStop();
	byte STOP_COUNT;

	public BusRoute() {
		STOP_COUNT = (byte) Util.random(MIN_STOPS, MAX_STOPS);
		BusStop currentStop = HEAD_STOP;
		for (int i = 0; i < STOP_COUNT - 1; i++) {
			currentStop.next = BusStop.randomStop();
//			System.out.println(currentStop);
			currentStop = currentStop.next;
		}
//		System.out.println(currentStop.name);
//		currentStop.next = null;
//		BusStop tail = BusStop.randomStop();
//		System.out.println(tail);
//		currentStop.next = tail;
//		System.out.println(currentStop);
	}
}
