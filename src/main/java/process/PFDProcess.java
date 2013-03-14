package process;

import base.Message;
import utils.Timestamp;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/9/13
 * Time: 11:11 AM
 * Implementation of a Process Perfect Failure Detector
 */
public class PFDProcess extends base.Process {

    public PFDProcess(String name, int pid, int totalN) {
        super(name, pid, totalN);

    }

    @Override
    public boolean registeR() {
        return false;  //To change body of implemented methods use File | Settings
        // | File Templates.
    }

    @Override
    public void receive(Message m) {

    }

    @Override
    public void initExecutor() {
        threadPool = Executors.newCachedThreadPool();
    }

    // submits task to be executed in the future
    public void submitTaskToBeRun(Runnable task, Timestamp futureTime) {
        if ( Timestamp.inTheFuture(futureTime) ) {
            threadPool.submit( task, futureTime );
        } else {
            threadPool.execute( task );
        }
    }



}
