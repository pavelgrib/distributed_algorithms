package process;

import base.Message;

/**
 * User: paul
 * Date: 3/10/13
 * Time: 9:01 AM
 */
public interface DistributedProcess {
    boolean registeR();

    void begin();

    void receive(Message m);

    boolean unicast(Message m);

    void broadcast(String type, String payload);

    void execute(Runnable task);
}
