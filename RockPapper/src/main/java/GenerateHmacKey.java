import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GenerateHmacKey {
    public byte[] generate(byte[] bytes, int numberComputers) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(bytes, "HmacSHA256");
        mac.init(keySpec);
        String key = String.valueOf(numberComputers);
        return mac.doFinal(key.getBytes(StandardCharsets.UTF_8));
    }
}
