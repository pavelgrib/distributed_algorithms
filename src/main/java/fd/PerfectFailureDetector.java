package fd;

import base.*;
import base.Process;
import process.PID;
import utils.Constants;
import utils.HeartbeatTask;

import java.util.concurrent.*;


/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/9/13
 * Time: 11:11 AM
 */
public class PerfectFailureDetector extends BasicFailureDetector implements
        FailureDetector {

    private static final boolean INTERRUPT_IF_RUNNING = true;


    public PerfectFailureDetector( Process owner ) {
        super(owner);
    }

    @Override
    public void begin() {
        initializeSuspectsList();
        scheduleHeartbeats( Constants.HEARTBEAT_FREQUENCY );
        scheduleSuspicions();
    }

    @Override
    protected void initializeSuspectsList() {
        otherProcesses = new ConcurrentHashMap<PID, Boolean>( this.owner.getNo() );
        heartbeats = new ConcurrentHashMap<PID, Long>( this.owner.getNo() );

        // initially no-one is suspected
        long currentTime = System.currentTimeMillis();
        for ( PID id : heartbeats.keySet() ) {
            heartbeats.put( id, currentTime );
        }
    }

    /* only run initially to send periodic heartbeats */
    private void scheduleHeartbeats(final long frequency ) {
        hbTask = HeartbeatTask.getInstance(owner);
        scheduler.scheduleAtFixedRate( hbTask, 0, frequency,
                TimeUnit.MILLISECONDS );
    }

    /* only run initially to schedule all the processes to be suspected */
    @Override
    protected void scheduleSuspicions() {
        for ( int i = 1; i < owner.getNo() && i != owner.getPid().getNumber(); ++i ) {
            final PID id = PID.newInstance(i);
            scheduler.schedule( new Runnable() {
                @Override
                public void run() {
                    otherProcesses.put( id, Boolean.TRUE );
                }
            }, Constants.HEARTBEAT_FREQUENCY + 2 * Utils.DELAY,
                    TimeUnit.MILLISECONDS );
        }
    }

    @Override
    public void receive(Message m) {
        PID source = m.getSource();

        heartbeats.replace( source, Long.valueOf(m.getPayload() ) );
        suspicionFutures.get(m.getSource()).cancel( INTERRUPT_IF_RUNNING );

        // kick off new suspicion task
        updateSuspicionFuture( m.getSource(), Constants.HEARTBEAT_FREQUENCY + 2 *
                Utils.DELAY );
    }

    @Override
    public boolean isSuspected(PID processID) {
        return otherProcesses.get( processID ).equals( Boolean.TRUE );
    }

    @Override
    public int getLeader() {
        return 0;
    }
}
