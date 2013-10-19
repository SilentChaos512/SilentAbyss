package silentAbyss;

public class AbyssLog {
	
	// oneTimePrint will only display a message if this is false.
	private static boolean onceOnly = false;

	// Displays "[Silent's Abyss]" followed by s.
	public static void print(String s) {
		System.out.println("[Silent's Abyss] " + s);
	}
	
	// Prints XYZ coordinates in a nice format.
	public static String coord(int x, int y, int z) {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	// Used for debugging. Will print only the first time it is called, or after calling resetOneTimePrint.
	public static void oneTimePrint(String s) {
		if (!onceOnly) {
			AbyssLog.print("[1t] " + s);
		}
		
		onceOnly = true;
	}
	
	// Allows oneTimePrint to be used again.
	public static void resetOneTimePrint() {
		onceOnly = false;
	}
}
