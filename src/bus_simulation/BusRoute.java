package bus_simulation;

import java.io.File;
import java.io.IOException;

public class BusRoute {
	public static byte NAME_ASCII = 65;
	public static final byte MIN_STOPS = 3, MAX_STOPS = 3;

	public final BusStop DEPOT;
	public final BusStop HEAD_STOP;
	public byte STOP_COUNT;

	public final String name;

	public BusRoute() {
		name = String.format("ROUTE %s", (char) NAME_ASCII++);

//		DEPOT = BusStop.createDepot(this);
		DEPOT = new BusStop(this, "FOO_DEPOT", (byte) 2, true);
		HEAD_STOP = DEPOT;

		STOP_COUNT = (byte) Util.random(MIN_STOPS, MAX_STOPS);
		BusStop currentStop = HEAD_STOP;
//		for (int i = 0; i < STOP_COUNT; i++) {
//			currentStop.next = BusStop.randomStop(this);
//			currentStop = currentStop.next;
//		}
		currentStop.next = new BusStop(this, "FOO_1", (byte) 3);

		try {
			File log = new File(String.format("%s.txt", name));
			log.createNewFile();
		} catch (final IOException e) {
		}
	}

	public void update() {

	}
}
