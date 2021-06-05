import java.security.SecureRandom;

public class SecurityRandomNumber {
    public byte[] rand(byte[] bytes){
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }
}
