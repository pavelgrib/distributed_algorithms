package fd;

import base.Message;
import process.PID;

public interface FailureDetector {
	
	/* Initiates communication tasks, e.g. sending heartbeats periodically */
	void begin ();
	
	/* Handles in-coming (heartbeat) messages */
	void receive(Message m);

	/* Returns the next leader of the system; used only for §2.1.2.
	 * Or, it should also be used to notify a process if the leader
	 * changed.
	 */
	int getLeader();
	
	/* Notifies a blocking thread that ‘process’ has been suspected. 
	 * Used only for tasks in §2.1.3 */
	boolean isSuspected(PID processID);

}
