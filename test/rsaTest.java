import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class rsaTest {

    @Test
    public void gcdTest() {

        assertEquals(1, rsa.gcd(13, 37), "gcd of 1");
        assertEquals(12, rsa.gcd(24, 36), "smaller first");
        assertEquals(12, rsa.gcd(36, 24), "smaller last");
        assertEquals(12, rsa.gcd(12, 36), "gcd of one of the two numbers");

    }

    @Test
    public void modularInverseTest() {

        assertEquals(5, rsa.inverse(60, 13).getS(), "does calculation wrong");
        assertEquals(37, rsa.inverse(229, 353).getS(), "does calculation wrong");
        assertEquals(14, rsa.inverse(823, 281).getS(), "does calculation wrong");

    }

    @Test
    public void publicKeyTest() {

        assertEquals(107, rsa.generatePublicKeys(7,19).getExponent(), "public key wrong");
        assertEquals(65537, rsa.generatePublicKeys(5861,7177).getExponent(), "public key wrong");
    }

    @Test
    public void privateKeyTest() {

        assertEquals(25, rsa.generatePrivateKeys(7, 19, 13).getExponent());
    }

    @Test public void encryptDecryptTest() {

        KeyPair publicKey = rsa.generatePublicKeys(521, 7079);
        KeyPair privateKey = rsa.generatePrivateKeys(521, 7079, publicKey.getExponent());

        long message = 2048;
        //decrypt the encrypted message
        assertEquals(message, rsa.encryptDecrypt(rsa.encryptDecrypt(message, publicKey), privateKey));

        message = -365;
        assertEquals(message, rsa.encryptDecrypt(rsa.encryptDecrypt(message, publicKey), privateKey));

        message = 456792;
        assertEquals(message, rsa.encryptDecrypt(rsa.encryptDecrypt(message, publicKey), privateKey));

    }

}