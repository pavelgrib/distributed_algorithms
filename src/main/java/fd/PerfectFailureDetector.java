package fd;

import base.Message;
import process.DistributedProcess;

import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/9/13
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class PerfectFailureDetector implements FailureDetector {

    private Map<DistributedProcess, Double> suspects;

    @Override
    public void begin() {
        initializeSuspectsList();
        scheduleHeartbeats();
    }

    private void initializeSuspectsList() {
        suspects = new HashMap<DistributedProcess, Double>();
        //To change body of created methods use File | Settings | File Templates.
    }

    private void scheduleHeartbeats() {

    }

    @Override
    public void receive(Message m) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSuspect(Integer process) {
        return false;  //To change body of implemented methods use File | Settings
        // | File Templates.
    }

    @Override
    public int getLeader() {
        return 0;  //To change body of implemented methods use File | Settings |
        // File Templates.
    }

    @Override
    public void isSuspected(Integer process) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
