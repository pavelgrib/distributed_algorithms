package process;

import base.Message;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/9/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class ELEProcess implements DistributedProcess {
    @Override
    public boolean registeR() {
        return false;  //To change body of implemented methods use File | Settings
        // | File Templates.
    }

    @Override
    public void receive(Message m) {
        //To change body of implemented methods use File | Settings | File Templates.
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

    @Override
    public void execute(Runnable task) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
