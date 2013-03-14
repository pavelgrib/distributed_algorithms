package utils;

import process.DistributedProcess;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/13/13
 * Time: 9:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeartbeatTask implements Runnable {

    private DistributedProcess process;


    @Override
    public void run() {
        process.broadcast();
    }
}
