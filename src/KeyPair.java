public class KeyPair {

    private long exponent;
    private long modulus;

    public KeyPair(long exp, long mod) {
        exponent = exp;
        modulus = mod;
    }

    public long getExponent() {
        return exponent;
    }

    public long getModulus() {
        return modulus;
    }
}
