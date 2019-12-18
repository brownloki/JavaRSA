/**
 * Simple class to store three numbers in an object.
 * Variable lettering corresponds to those in the
 * Extended Euclidean algorithm pseudocode written
 * by Richard Chang, UMBC
 */
public class triple {

    private long d;
    private long s;
    private long t;

    //constructor sets values of triple
    public triple(long d, long s, long t) {
        this.d = d;
        this.s = s;
        this.t = t;
    }

    //getters
    public long getS() {
        return s;
    }
    public long getD() {
        return d;
    }
    public long getT() {
        return t;
    }
}

