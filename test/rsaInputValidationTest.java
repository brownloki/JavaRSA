import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class rsaInputValidationTest {

    @Test
    public void gcdValidationTest() {

        assertThrows(IllegalArgumentException.class, () -> {rsa.gcd(-32, 1);}, "negative first number");
        assertThrows(IllegalArgumentException.class, () -> {rsa.gcd(1, -32);}, "negative second number");

    }

    @Test
    public void inverseValidationTest() {

        assertEquals(1, rsa.inverse(1, 0).getS(), "doesn't catch base case");
        assertThrows(IllegalArgumentException.class, () -> {rsa.inverse(1, 2);},
                "doesn't catch number > mod");
        assertThrows(IllegalArgumentException.class, () -> {rsa.inverse(64, 8);},
                "doesn't catch gcd != 1");

    }
}
