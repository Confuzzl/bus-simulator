package bus_simulation;

import java.util.HashSet;
import java.util.Set;

public class Simulation {
	public static final byte ROUTE_COUNT = 5;
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

		for (int i = 0; i < ROUTE_COUNT; i++) {
			ROUTES.add(new BusRoute());
		}

		while (RUNNING) {
			ROUTES.forEach((final BusRoute route) -> {
				route.update();
			});
			TICKS++;
			Thread.sleep(SLEEP_TIME);
		}

		ROUTES.forEach((final BusRoute route) -> {
			route.end();
		});

	}
}
