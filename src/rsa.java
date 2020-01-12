import java.io.*;
import java.util.Scanner;


public class rsa {

    public static void main(String[] args) throws IOException {

        Scanner kb = new Scanner(System.in);
        String fileName = "";

        //get filename from user
        do {
            if (!fileName.equals("")) {
                System.out.println("Invalid file name. Enter a new one.");
            }
            System.out.print("Enter the name of a .txt file to be encrypted: ");
            fileName = kb.next();
            System.out.println();
        } while (!fileName.substring(fileName.length() - 4).equals(".txt"));

        //create scanner to read in contents of file
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);

        FileWriter outputFile = new FileWriter("encrypted.txt");

        System.out.println("Want to specify two prime numbers for key generation? Y/N: ");
        String specifyPrimes = kb.next();

        //wants to enter their own primes
        if (specifyPrimes.toLowerCase().charAt(0) == 'y') {

        }
        //doesn't want to enter their own primes
        else {

        }

        //read in letter by letter
        //direct copy spaces
        //dashes in between letters?

        while (inputFile.hasNext()) {

        }

        kb.close();
        System.out.println("done");
    }

    /**
     * Calculate the greatest common denominator of two longs
     * @param first -- one of the two longs to find gcd of
     * @param second -- the other long
     * @return -- gcd of the two
     * @throws IllegalArgumentException -- if negative numbers are passed
     */
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
     * Note: sometimes generates negative inverses (which are still correct). These will be accounted for in the
     * key generation process.
     * @param number -- number to find inverse of
     * @param mod -- field to find inverse over
     * @return -- modular multiplicative of number (mod mod)
     * @throws IllegalArgumentException -- if number < mod or gcd of the two != 1
     */
    public static Triple inverse(long number, long mod) throws IllegalArgumentException {

        //input validation
        if (gcd(number, mod) != 1) {
            throw new IllegalArgumentException("gcd of two numbers must be 1");
        }

        if (mod == 0) {
            return new Triple(number, 1, 0);
        }

        Triple newVals = inverse(mod, number % mod);

        long d = newVals.getD();
        long s = newVals.getT();
        long t = newVals.getS() - (number / mod) * newVals.getT();

        return new Triple(d, s, t);
    }

    /**
     * Method to generate a public keypair for two prime numbers. This keypair is commonly used to encrypt
     * @param p -- one prime
     * @param q -- the other prime
     * @return -- a KeyPair where the exponent is the largest integer coprime to phiN, and the mod is n.
     */
    public static KeyPair generatePublicKeys(long p, long q) {

        //calculate n and phi(n) for later
        long phiN = (p - 1) * (q - 1);
        long n = p * q;
        long publicExp;

        //use 65537 as a default exponent if primes are large
        if (phiN > 65537) {
            publicExp = 65537;
        }
        //otherwise find largest int coprime to phi(n)
        else {
            publicExp = 1;
            for (int i = 0; i < phiN; i++) {
                if (gcd(i, phiN) == 1) {
                    publicExp = i;
                }
            }
        }

        return new KeyPair(publicExp, n);
    }

    /**
     * Method to calculate the private exponent using Euclid's algorithm. This keypair is commonly used to decrypt.
     * @param p -- one prime
     * @param q -- the other prime
     * @param publicExp -- the public exponent from another keypair
     * @return -- a keypair containing n and the private exponent
     */
    public static KeyPair generatePrivateKeys(long p, long q, long publicExp) {

        //find modular multiplicative inverse of the public exponent
        long phiN = (p - 1) * (q - 1);
        long n = p * q;
        long privateExp = inverse(publicExp, phiN).getS();

        //correct for negative inverses by adding phi(n) to make it positive but still correct
        if (privateExp < 0) {
            privateExp += phiN;
        }

        //return the keypair with the new exponent and n
        return new KeyPair(privateExp, n);
    }

    /**
     * Method to encrypt or decrypt a message using modular exponentiation
     * Credit to Nikita Tiwari: https://www.geeksforgeeks.org/modular-exponentiation-power-in-modular-arithmetic/
     * @param message -- message to be transformed
     * @param pair -- public or private KeyPair with exponent and modulo
     * @return -- the encrypted/decrypted message
     */
    public static long encryptDecrypt(long message, KeyPair pair) {

        long exp = pair.getExponent();
        long mod = pair.getModulus();
        long result = 1;

        //Update x if it is greater than or equal to p
        message = message % mod;

        while (exp > 0)
        {
            //If exp is odd, multiply message with result
            if((exp & 1)==1)
                result = (result * message) % mod;

            //make exp even
            exp = exp / 2;
            message = (message * message) % mod;
        }
        return result;
    }


}

