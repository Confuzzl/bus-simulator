package bus_simulation;

public class Simulation {
	static final byte BUS_COUNT = 1;
	static final short SLEEP_TIME = 1_000;

	static boolean RUNNING = true;

	public void start() throws InterruptedException {
//		Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			
//			@Override
//			public void run() {
//				
//			}
//		}, 0, 1000);
		while (RUNNING) {
			Thread.sleep(SLEEP_TIME);
		}
	}
}
