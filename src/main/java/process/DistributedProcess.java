package process;

import base.Message;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/10/13
 * Time: 9:01 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DistributedProcess {
    boolean registeR();

    void receive(Message m);

    boolean unicast(Message m);

    void broadcast(String type, String payload);
}
