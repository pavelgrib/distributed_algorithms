package process;

import base.Message;
import base.Process;
import fd.PerfectFailureDetector;
import java.util.concurrent.Executors;

/**
 * User: paul
 * Date: 3/9/13
 * Time: 11:11 AM
 * Implementation of a Process Perfect Failure Detector
 */
public class PFDProcess extends Process {

    public PFDProcess(String name, PID pid, int totalN) {
        super(name, pid, totalN);
        this.failureDetector = new PerfectFailureDetector(this);
        threadPool = Executors.newCachedThreadPool();
    }

    @Override
    public void receive(Message m) {
        String type = m.getType();
        if ( type == "HEARTBEAT" ) {
            System.out.println( m.toString() );
            this.failureDetector.receive(m);
        } else {
            System.out.println("Unknown message type " + type);
        }
    }

    // submits task to be executed in the future
    public void submitTaskToBeRun(Runnable task, Long futureTime) {
        if ( futureTime > System.currentTimeMillis() ) {
            threadPool.submit( task, futureTime );
        } else {
            threadPool.execute( task );
        }
    }

    public static void main( String[] args ) {
        PID id = PID.newInstance( Integer.valueOf(args[1]) );
        PFDProcess p = new PFDProcess( args[0], id, Integer.valueOf(args[2]) );
        p.registeR();

    }
}
