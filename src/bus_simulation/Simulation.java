package bus_simulation;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Simulation {
	public static class KillThread extends Thread {
		public boolean running = true;

		final Scanner scanner = new Scanner(System.in);

		@Override
		public void run() {
			System.out.println("PRESS ENTER TO END SIMULATION");
			scanner.nextLine();
			end();
		}

		public void end() {
			System.out.println("ENDING SIMULATION");
			running = false;
		}

	}

	public static final byte ROUTE_COUNT = 5;
	public static final short SLEEP_TIME = 1_000;

	public static long TICKS = 0;
	public static long MAX_TICKS = 5;

	public static final Set<BusRoute> ROUTES = new HashSet<BusRoute>();

	public static KillThread KILLTHREAD = new KillThread();

	public static void start() throws InterruptedException {
		KILLTHREAD.start();

		for (int i = 0; i < ROUTE_COUNT; i++) {
			ROUTES.add(new BusRoute());
		}

		while (KILLTHREAD.running) {
			if (TICKS >= MAX_TICKS) {
				KILLTHREAD.end();
			}

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
