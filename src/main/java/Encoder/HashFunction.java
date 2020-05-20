package Encoder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {

    public static String getHash(String input) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(Byte.parseByte(input));
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashValue;
    }
}
