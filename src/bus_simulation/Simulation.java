package bus_simulation;

import java.util.HashSet;
import java.util.Set;

public class Simulation {
	public static final byte ROUTE_COUNT = 1;
	public static final short SLEEP_TIME = 1_000;

	public static boolean RUNNING = true;

	public static long TICKS = 0;

	public static final Set<BusRoute> ROUTES = new HashSet<BusRoute>();

	public static void start() throws InterruptedException {
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

		for (int i = 0; i < ROUTE_COUNT; i++) {
			ROUTES.add(new BusRoute());
		}
		for (int i = 0; i < 100; i++) {
			ROUTES.forEach((final BusRoute route) -> {
				route.update();
			});
			TICKS++;
		}
		ROUTES.forEach((final BusRoute route) -> {
			route.end();
		});

	}
}
