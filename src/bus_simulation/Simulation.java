package bus_simulation;

public class Simulation {
	public static final byte BUS_COUNT = 1;
	public static final short SLEEP_TIME = 1_000;

	public static boolean RUNNING = true;

	public void start() throws InterruptedException {
//		Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			
//			@Override
//			public void run() {
//				
//			}
//		}, 0, 1000);
//		while (RUNNING) {
//			Thread.sleep(SLEEP_TIME);
//		}

		BusRoute route = new BusRoute();
		BusStop current = route.HEAD_STOP;
		for (int i = 0; i < route.STOP_COUNT; i++) {
			System.out.println(current);
			current = current.next;
		}
//		System.out.println(route.HEAD_STOP.passengerList);
//		Bus bus = new Bus();
//		bus.load(route.HEAD_STOP);
//		bus.unload(route.HEAD_STOP);
//		System.out.println(bus.passengerList);
//		System.out.println(route.HEAD_STOP.passengerList);

	}
}
