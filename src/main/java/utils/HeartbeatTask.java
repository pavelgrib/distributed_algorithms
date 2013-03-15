package utils;

import process.DistributedProcess;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/13/13
 * Time: 9:34 PM
 */
public class HeartbeatTask {

    private DistributedProcess process;
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    Runnable task;

    public HeartbeatTask getInstance() {
        final HeartbeatTask hbt = new HeartbeatTask(process);
        hbt.scheduler.schedule( new Runnable() {
            @Override
            public void run() {
                hbt.process.broadcast("HEARTBEAT",
                        String.valueOf(System.currentTimeMillis()));
            }
        }, Constants.HEARTBEAT_FREQUENCY, TimeUnit.MILLISECONDS );
        return hbt;
    }

    private HeartbeatTask( DistributedProcess process) {
        this.process = process;
    }
}
