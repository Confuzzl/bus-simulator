package bus_simulation;

public class Simulation {
	public static final byte BUS_COUNT = 1;
	public static final short SLEEP_TIME = 1_000;

	public static boolean RUNNING = true;

	public static long TICKS = 0;

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
//		for (BusStop current = route.HEAD_STOP; current != null; current = current.next) {
//			System.out.println(current);
//		}

		Bus bus = new Bus(route);
		for (int i = 0; i < 5; i++) {
			bus.update();
			TICKS++;
		}

//		System.out.println(route.HEAD_STOP.passengerList);
//		Bus bus = new Bus();
//		bus.load(route.HEAD_STOP);
//		bus.unload(route.HEAD_STOP);
//		System.out.println(bus.passengerList);
//		System.out.println(route.HEAD_STOP.passengerList);

	}
}
