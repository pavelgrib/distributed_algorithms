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
public class PerfectFailureDetector implements FailureDetector {

    private static final boolean INTERRUPT_IF_RUNNING = true;


    private ConcurrentSkipListSet<PID> suspects; // list of process IDs
    private ConcurrentHashMap<PID, Long> heartbeats;
    private Process owner;
    private HeartbeatTask hbTask;
    private ScheduledExecutorService scheduler;
    private ConcurrentHashMap<PID, ScheduledFuture<?>> suspicionFutures;

    public PerfectFailureDetector( Process owner ) {
        this.owner = owner;
    }

    @Override
    public void begin() {
        initializeSuspectsList();
        scheduleHeartbeats();
        scheduleSuspicions();
    }

    private void initializeSuspectsList() {
        suspects = new ConcurrentSkipListSet<PID>();
        heartbeats = new ConcurrentHashMap<PID, Long>( owner.getNo() );

        for ( PID key : heartbeats.keySet() ) {
            heartbeats.put( key, System.currentTimeMillis() );
        }
    }

    private void scheduleHeartbeats() {
        hbTask = HeartbeatTask.getInstance(owner);
        scheduler.scheduleAtFixedRate( hbTask, 0, Constants.HEARTBEAT_FREQUENCY,
                TimeUnit.MILLISECONDS );
    }

    private void scheduleSuspicions() {
        for ( int i = 1; i < owner.getNo() && i != owner.getPid().getNumber(); ++i ) {
            final PID id = PID.newInstance(i);
            scheduler.schedule( new Runnable() {
                @Override
                public void run() {
                    suspects.add( id );
                }
            }, Constants.HEARTBEAT_FREQUENCY + 2 * Utils.DELAY,
                    TimeUnit.MILLISECONDS );
        }
    }

    @Override
    public void receive(Message m) {
        if ( m.getType() == "HEARTBEAT" ) {
            receiveHeartbeat(m);
        }
    }

    private void receiveHeartbeat( final Message m ) {
        // update last heartbeat
        PID source = m.getSource();

        if ( heartbeats.contains( source ) ) {
            heartbeats.replace( source, Long.valueOf(m.getPayload() ) );
        } else {
            heartbeats.put( source, Long.valueOf( m.getPayload() ) );
        }
        suspicionFutures.get(m.getSource()).cancel( INTERRUPT_IF_RUNNING );

        // kick off new suspicion task
        updateSuspicionFuture( m.getSource() );
    }

    @Override
    public boolean isSuspect(PID processID) {
        return suspects.contains( processID );
        // | File Templates.
    }

    private void updateSuspicionFuture(final PID otherProcess) {
        suspicionFutures.put( otherProcess, scheduler.schedule( new Runnable() {
            @Override
            public void run() {
                suspects.add( otherProcess );
            }
        }, Constants.HEARTBEAT_FREQUENCY + 2 * Utils.DELAY,
                TimeUnit.MILLISECONDS ) );
    }

    @Override
    public int getLeader() {
        return 0;  //To change body of implemented methods use File | Settings |
        // File Templates.
    }

    @Override
    public boolean isSuspected(PID processID ) {
        return suspects.contains( processID );
    }


}
