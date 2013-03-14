package utils;

import java.sql.Time;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/13/13
 * Time: 7:29 PM
 * Class to keep track of times a message is received... should not be changed
 * after creating, so singleton is appropriate pattern to use
 */
public class Timestamp {

    private final long milliseconds;

    public Timestamp() {
        this.milliseconds = System.currentTimeMillis();
    }

    private Timestamp(long millisSince1970) {
        this.milliseconds = millisSince1970;
    }

    public boolean before(Timestamp anotherTimestamp) {
        return this.milliseconds < anotherTimestamp.getMillis();
    }

    private long getMillis() {
        return milliseconds;
    }

    public static boolean inTheFuture(Timestamp futureTime) {
        return (new Timestamp()).before(futureTime);
    }
}
