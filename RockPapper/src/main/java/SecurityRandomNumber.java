import java.security.SecureRandom;

public class SecurityRandomNumber {
    public byte[] rand() {
        byte[] bytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }
}
