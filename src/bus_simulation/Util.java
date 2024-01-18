package bus_simulation;

public class Util {
	public static int random(final int min, final int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	public static boolean chance(final double chance) {
		return Math.random() < chance;
	}

	public static <T> T randomElement(final T[] array) {
		return array[random(0, array.length - 1)];
	}

	public static int clamp(final int min, final int val, final int max) {
		return Math.max(min, Math.min(val, max));
	}
}
