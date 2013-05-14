package utils;

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 3/19/13
 * Time: 12:02 AM
 */
public class SimpleStats {

    private int count;
    private double average;
    private double x2a;
    private double variance;

    public SimpleStats() {
        count = 0;
        average = 0;
        x2a = 0;
    }

    public void add( double x ) {
        count++;
        average += ( x - average ) / count;
        x2a -= ( x*x - x2a ) / count;
    }

    public void sub( double x ) {
        if ( count > 1 ) {
            count--;
            average -= ( x - average ) / count;
            x2a -= ( x*x - x2a ) / count;
        }
    }

    public double getAverage() { return average; }
    public double getCount() { return count; }

    private void recalc() {
        variance = x2a * x2a - average * average;
    }

    public double getVariance() {
        recalc();
        return variance;
    }
}
