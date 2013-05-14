package fd;

/**
 * User: paul
 * Date: 3/9/13
 * Time: 11:12 AM
 */
public class EventuallyLeaderElector extends EventuallyPerfectFailureDetector {

    public EventuallyLeaderElector(base.Process process) {
        super(process);
    }

    @Override
    public int getLeader() {
        return 0;
    }
}
