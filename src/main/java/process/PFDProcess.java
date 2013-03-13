package process;

import base.Message;

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
    public boolean unicast(Message m) {
        return false;  //To change body of implemented methods use File | Settings
        // | File Templates.
    }

    @Override
    public void broadcast(String type, String payload) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
