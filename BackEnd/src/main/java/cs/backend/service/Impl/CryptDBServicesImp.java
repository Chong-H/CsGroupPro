package cs.backend.service.Impl;

import cs.backend.service.CryptDBServices;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Service
public class CryptDBServicesImp implements CryptDBServices {

    @Override
    public String getDBstr() {
        return "";
    }

    @Override
    public String getKey(String prika) {
        return "abcdefghabcdefgh";
    }

    @Override
    public String encryptString(String plainText, String secretKey) throws Exception {
        // 确保密钥长度为16字节
        if (secretKey.length() != 16) {
            throw new IllegalArgumentException("Secret key must be 16 bytes long");
        }

        byte[] key = secretKey.getBytes();
        byte[] iv = "1234567890123456".getBytes(); // 初始化向量，必须是16字节

        // 创建AES密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // 创建加密器
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // 加密数据
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Override
    public String decryptString(String cipherText, String secretKey) throws Exception {

        try {
//            byte[] key = secretKey.getBytes();
            byte[] key = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 16);
            byte[] iv = "1234567890123456".getBytes(); // 初始化向量，必须是16字节

            // 创建AES密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // 创建解密器
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // 解密数据
            byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
            byte[] decryptedBytes = cipher.doFinal(cipherBytes);
            return new String(decryptedBytes);
        }catch (Exception e) {
            System.err.println("Decryption failed for cipherText: " + cipherText);
            throw e;
        }
    }
    @Override
    public String safeDecrypt(String cipherText, String secretKey) {
        try {
            if (cipherText == null || cipherText.length() % 4 != 0 ) {
                // 不合法密文，直接返回原始值
                return cipherText;
            }
            return decryptString(cipherText, secretKey);
        } catch (Exception e) {
            // 解密失败也返回原文
            return cipherText;
        }
    }
    @Override
    public boolean isBase64(String str) {
        try {
            // 长度必须是4的倍数（Base64编码要求）
            if (str == null || str.length() % 4 != 0) return false;

            // 尝试Base64解码，如果失败就不是合法的Base64
            Base64.getDecoder().decode(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
