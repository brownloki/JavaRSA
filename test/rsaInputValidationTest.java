import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class rsaInputValidationTest {

    @Test
    public void gcdNegativeNumbers() {

        assertThrows(IllegalArgumentException.class, () -> {rsa.gcd(-32, 1);}, "negative first number");
        assertThrows(IllegalArgumentException.class, () -> {rsa.gcd(1, -32);}, "negative second number");

    }
}
