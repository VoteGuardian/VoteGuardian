package com.blockchain.voteguardian.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AesUtil {
    private SecretKeySpec secretKey;

    public AesUtil(String reqSecretKey) throws UnsupportedEncodingException{

        //바이트 배열로부터 SecretKey를 구축
        this.secretKey = new SecretKeySpec(reqSecretKey.getBytes("UTF-8"), "AES");
    }

    public String AesCBCEncode(String plainText) throws Exception {

        //Cipher 객체 인스턴스화(Java에서는 PKCS#5 = PKCS#7이랑 동일)
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");

        //Cipher 객체 초기화
        c.init(Cipher.ENCRYPT_MODE, secretKey);

        //Encrpytion/Decryption
        byte[] encrpytionByte = c.doFinal(plainText.getBytes("UTF-8"));

        //Hex Encode
        return Hex.encodeHexString(encrpytionByte);
    }

    //AES CBC PKCS5Padding 복호화(Hex | Base64)
    public String AesCBCDecode(String encodeText) throws Exception {

        //Cipher 객체 인스턴스화(Java에서는 PKCS#5 = PKCS#7이랑 동일)
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");

        //Cipher 객체 초기화
        c.init(Cipher.DECRYPT_MODE, secretKey);

        //Decode Hex
        byte[] decodeByte = Hex.decodeHex(encodeText.toCharArray());

        return new String(c.doFinal(decodeByte), "UTF-8");
    }
}
