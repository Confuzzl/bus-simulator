package bus_simulation;

public class Util {
	public static int random(final int min, final int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	public static boolean chance(final double chance) {
		return Math.random() < chance;
	}

//	public static int clamp(final int min, final int n, final int max) {
//		return Math.max(min, Math.min(n, max));
//	}
//
//	public static int clamp(final int n, final int max) {
//		return clamp(0, n, max);
//	}

	public static <T> T randomElement(T[] array) {
		return array[random(0, array.length - 1)];
	}
}
