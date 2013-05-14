package fd;

import base.*;
import process.PID;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * User: paul
 * Date: 3/9/13
 * Time: 11:11 AM
 */
public class EventuallyPerfectFailureDetector extends
        BasicFailureDetector implements FailureDetector {

    public EventuallyPerfectFailureDetector(base.Process process) {
        super(process);
    }

    @Override
    protected void initializeSuspectsList() {
        this.otherProcesses = new ConcurrentHashMap<PID,
                Boolean>( this.owner.getNo() );
    }

    @Override
    protected void scheduleSuspicions() {
        for ( int i = 0; i < this.owner.getNo(); ++i ) {

        }
    }

    @Override
    public void begin() {

    }

    @Override
    public void receive(Message m) {

    }

    @Override
    public int getLeader() {
        return 0;
    }

    @Override
    public boolean isSuspected(PID processID) {
        return otherProcesses.get( processID ).equals(Boolean.TRUE);
    }
}
