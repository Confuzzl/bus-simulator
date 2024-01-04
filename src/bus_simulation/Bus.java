package bus_simulation;

public class Bus {
	static final byte MAX_CAPACITY = 40;
	static final double NO_EXIT_CHANCE = 0.10;

	Passenger.List passengerList = new Passenger.List();

	int remainingCapacity() {
		return MAX_CAPACITY - passengerList.size();
	}

	void board(final BusStop stop) {
		if (stop.passengerList.size() <= remainingCapacity()) {
			passengerList.addAll(stop.passengerList);
			stop.passengerList.removeAll();
			return;
		}
		int elderlyToBoard = Util.clamp(stop.passengerList.ELDERLY_COUNT, remainingCapacity());
	}
}
