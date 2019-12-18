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
}
