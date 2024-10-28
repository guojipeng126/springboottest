package com.gjp.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

public class SM4Utils {

    public static final String SM4_ECB_PKCS7 = "SM4/ECB/PKCS7Padding";
//    public static final String SM4_CBC_PKCS7 = "SM4/CBC/PKCS7Padding";
    public static final String PROVIDER_BC = "BC";

    /**
     * 加密
     * @param key 密钥串
     * @param source 待加密原文
     * @return
     * @throws Exception
     */
    public static String encryptBase64(String key, String source) throws Exception{
        byte[] res = encryptWithSM4(key.getBytes(StandardCharsets.UTF_8), null, source.getBytes());
//        return new BASE64Encoder().encode(res);
        return Hex.toHexString(res);
    }

    /**
     * 解密
     * @param key 密钥串
     * @param var0  加密之后的密文
     * @return
     * @throws Exception
     */
    public static String decryptStr(String key, String var0) throws Exception{
//        byte[] dec_input_tmp = new BASE64Decoder().decodeBuffer(var0);
        byte[] dec_input_tmp = Hex.decode(var0);
        return new String(SM4Utils.decryptWithSM4(key.getBytes(StandardCharsets.UTF_8), null, dec_input_tmp));
    }

    public static byte[] encryptWithSM4(byte[] key, byte[] iv, byte[] input) throws Exception {
        return cryptWithSM4(key, iv, input, Cipher.ENCRYPT_MODE, SM4_ECB_PKCS7, PROVIDER_BC);
    }

    public static byte[] encryptWithSM4(byte[] key, byte[] iv, byte[] input, String algorithm, String provider) throws Exception {
        return cryptWithSM4(key, iv, input, Cipher.ENCRYPT_MODE, algorithm, provider);
    }

    public static byte[] decryptWithSM4(byte[] key, byte[] iv, byte[] input) throws Exception {
        return cryptWithSM4(key, iv, input, Cipher.DECRYPT_MODE, SM4_ECB_PKCS7, PROVIDER_BC);
    }

    public static byte[] decryptWithSM4(byte[] key, byte[] iv, byte[] input, String algorithm, String provider) throws Exception {
        return cryptWithSM4(key, iv, input, Cipher.DECRYPT_MODE, algorithm, provider);
    }

    public static byte[] cryptWithSM4(byte[] key, byte[] iv, byte[] input, int mode, String algorithm, String provider) throws Exception {
        SecretKeySpec spec = new SecretKeySpec(key, "SM4");
        Cipher cipher;
        if (provider == null || provider.isEmpty()) {
            cipher = Cipher.getInstance(algorithm);
        } else {
            cipher = Cipher.getInstance(algorithm, provider);
        }
        if (iv == null || iv.length == 0) {
            cipher.init(mode, spec);
        } else {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(mode, spec, ivParameterSpec);
        }
        return cipher.doFinal(input);
    }

    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
}
