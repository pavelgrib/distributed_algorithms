package process;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/17/13
 * Time: 1:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DistributedTask {

    public boolean startTask();
    public boolean killTask();
}
