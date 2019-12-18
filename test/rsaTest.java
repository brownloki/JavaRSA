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

    }

}