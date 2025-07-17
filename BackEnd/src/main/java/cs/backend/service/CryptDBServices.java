package cs.backend.service;

public interface CryptDBServices {
    public  String getDBstr();
    public String getKey(String prika);
    public  String encryptString(String plainText, String secretKey)throws Exception;
    public  String decryptString(String cipherText, String secretKey)throws Exception;

    String safeDecrypt(String cipherText, String secretKey);

    boolean isBase64(String str);
}
