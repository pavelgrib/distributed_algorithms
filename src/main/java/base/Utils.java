package base;

import process.PID;

public class Utils {

	public static final int INFINITY = -1;
	
	public static final String REGISTRAR_ADDR = "localhost";
	public static final int    REGISTRAR_PORT = 6667;
	public static final int    FAULTMNGR_PORT = 6665;

	public static final String SEPARATOR = "<|>";
	
	public static final boolean DEBUG = true;
	
	public static final boolean SELFMSGENABLED = false;
	
	public static final int MSG_QUEUE_SIZE = 100;
	public static final int DELAY = 100; /* msec; 1sec = 1000msec */
	public static final boolean GAUSSIAN = false;
	
	/* For measurement purposes */
	public static final long STEP = 100;
	public static final long MAX  = 2000;
	
	public static synchronized void out (String s) {
		
		if (DEBUG) {
			String x = String.format("[DBG] %s%n", s);
			System.out.print(x);
			System.out.flush();
		}
	}

	public static synchronized void out (PID id, String s) {
		
		/* Printf assumes there are no more than 999 processes */
		if (DEBUG) {
			String x = String.format("[%03d] %s%n", id.getNumber(), s);
			System.out.print(x);
			System.out.flush();
		}
	}
	
	/* For measurement purposes */
	public static String getPayload (int size) {
		char [] payload = new char[size];
		for (int i = 0; i < size; i++)
			payload[i] = 'x';
		return new String(payload);
	}
	
	public static void main (String [] args) {
		PID test_pid = PID.newInstance(1);
        Utils.out(test_pid, "test");
		Utils.out("test");
	}
}
