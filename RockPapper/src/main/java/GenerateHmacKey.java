import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateHmacKey {
    public byte[] generate(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        return sha256.digest(bytes);
    }
}
