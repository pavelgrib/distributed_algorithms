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
        return String.format("%d", id);
    }

    @Override
    public int compareTo(PID otherID) {
        return this.id - otherID.getNumber();
    }

    public boolean equals(PID otherID) {
        return compareTo(otherID) == 0;
    }

    public static void main( String[] args ) {
        PID id2 = PID.newInstance(2);
        PID id3 = PID.newInstance(2);

        System.out.println( id3.equals(id2) );
    }
}
