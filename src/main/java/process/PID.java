package process;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/18/13
 * Time: 11:13 AM
 */
public class PID implements Comparable<PID> {

    private int id;
    public static PID registrarPID = newInstance(0);

    public static PID newInstance( int id ) {
        return new PID(id);
    }

    private PID(int id) {
        this.id = id;
    }

    public int getNumber() { return this.id; }

    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public int compareTo(PID otherID) {
        return this.id - otherID.getNumber();
    }
}
