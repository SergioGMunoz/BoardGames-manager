package utils;

public class Debugger {
	static boolean debugEnabled = false;
	
	public static void print(String msg) {
		if (debugEnabled) {
			System.out.println(msg);
		}
	}
	
	public static void printErr (String msg) {
		if (debugEnabled) {
			System.err.println("❌ " + msg);
		}
	}
	
	public static void setDebug(boolean enabled) {
		debugEnabled=enabled;
	}
}
