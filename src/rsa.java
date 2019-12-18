public class rsa {

    public static long gcd(long first, long second) throws IllegalArgumentException {

        //input validation
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Arguments must be greater than 0.");
        }

        //done if a zero is found (gcd = other number)
        if (first == 0) {
            return second;
        }
        else if (second == 0) {
            return first;
        }
        //done if 1 is found (gcd =1)
        else if (first == 1 || second == 1) {
            return 1;
        }
        //otherwise call recursively
        else {
            long larger = Math.max(first, second);
            long smaller = Math.min(first, second);
            return gcd(smaller, larger % smaller);
        }
    }

    /**
     * Method to find the modular multiplicative inverse of two numbers
     * based on pseudocode written by Richard Chang, UMBC
     * https://www.csee.umbc.edu/~chang/cs203.s09/exteuclid.shtml
     * @param number -- number to find inverse of
     * @param mod -- field to find inverse over
     * @return -- modular multiplicative of number (mod mod)
     */
    public static triple inverse(long number, long mod) throws IllegalArgumentException {

        //input validation
        if (gcd(number, mod) != 1) {
            throw new IllegalArgumentException("gcd of two numbers must be 1");
        }
        else if (number < mod) {
            throw new IllegalArgumentException(("number must be less than mod"));
        }

        if (mod == 0) {
            return new triple(number, 1, 0);
        }

        triple newVals = inverse(mod, number % mod);

        long d = newVals.getD();
        long s = newVals.getT();
        long t = newVals.getS() - (number / mod) * newVals.getT();

        return new triple(d, s, t);
    }
}

