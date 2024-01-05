package bus_simulation;

import java.io.FileWriter;
import java.io.IOException;

public class BusRoute {
	public static byte NAME_ASCII = 65;
	public static final byte MIN_STOPS = 10, MAX_STOPS = 20;

	public FileWriter logger = null;

	public final BusStop DEPOT;
	public final BusStop HEAD_STOP;
	public byte STOP_COUNT;

	public final String name;

	public final Bus bus;

	public BusRoute() {
		name = String.format("ROUTE %s", (char) NAME_ASCII++);

		DEPOT = BusStop.createDepot(this);
		HEAD_STOP = DEPOT;

		STOP_COUNT = (byte) Util.random(MIN_STOPS, MAX_STOPS);
		BusStop currentStop = HEAD_STOP;
		for (int i = 0; i < STOP_COUNT; i++) {
			currentStop.next = BusStop.randomStop(this);
			currentStop = currentStop.next;
		}

		bus = new Bus(this);

		try {
			logger = new FileWriter(String.format("%s.txt", name));
		} catch (final IOException e) {
		}
	}

	public void update() {
		for (BusStop current = DEPOT.next; current != null; current = current.next) {
			current.update();
			try {
				logger.write(String.format("%d:\t%s\t%s\n", Simulation.TICKS, current.name, current.passengerList));
			} catch (final IOException e) {
			}
		}
		bus.update();
	}

	public void end() {
		try {
			logger.close();
		} catch (final IOException e) {
		}
		bus.end();
	}
}
