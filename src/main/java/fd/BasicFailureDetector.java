package fd;

import base.*;
import base.Process;
import process.PID;
import utils.HeartbeatTask;

import java.util.concurrent.*;

/**
 * User: paul
 * Date: 3/21/13
 * Time: 8:21 PM
 */
public abstract class BasicFailureDetector {
    protected ConcurrentHashMap<PID, Boolean> otherProcesses; // list of process IDs
    protected ConcurrentHashMap<PID, Long> heartbeats;
    protected base.Process owner;
    protected HeartbeatTask hbTask;
    protected ScheduledExecutorService scheduler;
    protected ConcurrentHashMap<PID, ScheduledFuture<?>> suspicionFutures;

    public BasicFailureDetector(Process owner) {
        this.owner = owner;

    }

    protected abstract void initializeSuspectsList();

    /* only run initially to schedule all the processes to be suspected */
    protected abstract void scheduleSuspicions();

    protected void updateSuspicionFuture(final PID otherProcess, final long when) {
        suspicionFutures.put( otherProcess, scheduler.schedule( new Runnable() {
            @Override
            public void run() {
                otherProcesses.put( otherProcess, Boolean.TRUE );
            }
        }, when, TimeUnit.MILLISECONDS ) );
    }
}
