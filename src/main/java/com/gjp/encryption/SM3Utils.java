package com.gjp.encryption;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

public class SM3Utils {

    private static final String sault = "tkvyrnoju9";

    public static String encrypt(String source) {
        if(source == null || source.length() <= 0){
            return source;
        }
        source = Hex.toHexString(source.getBytes(StandardCharsets.UTF_8));
        KeyParameter keyParameter = new KeyParameter(sault.getBytes(StandardCharsets.UTF_8));

        SM3Digest digest = new SM3Digest();
        HMac hMac = new HMac(digest);
        hMac.init(keyParameter);
        hMac.update(source.getBytes(StandardCharsets.UTF_8), 0, source.length());
        byte[] result = new byte[digest.getDigestSize()];
        hMac.doFinal(result, 0);
        return Hex.toHexString(result);
    }

    private static String encryptNoKey(String source) {
        if(source == null || source.length() <= 0){
            return source;
        }
        source = Hex.toHexString(source.getBytes(StandardCharsets.UTF_8));
//        source = new BASE64Encoder().encode(source.getBytes(StandardCharsets.UTF_8));
        SM3Digest digest = new SM3Digest();
        digest.update(source.getBytes(StandardCharsets.UTF_8), 0, source.length());
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);

        return Hex.toHexString(result);
    }

}
