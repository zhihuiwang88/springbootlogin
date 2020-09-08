package com.tmall.springboottmall.controller;

import java.util.Base64;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.Data;



/**
 * java签名
 * @author Dell
 *
 */
@Data
public class SignatureTest {

	private String secretId;
    private String secretKey;
    private long currentTime;
    private int random;
    private int signValidDuration;
	
	
    private static final String HMAC_ALGORITHM = "HmacSHA1";
    private static final String CONTENT_CHARSET = "UTF-8";

    public static byte[] byteMerger(byte[] byte1, byte[] byte2) {
        byte[] byte3 = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, byte3, 0, byte1.length);
        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
        return byte3;
    }
	
    public String getUploadSignature() throws Exception {
        String strSign = "";
        String contextStr = "";

        long endTime = (currentTime + signValidDuration);
        contextStr += "secretId=" + java.net.URLEncoder.encode(secretId, "utf8");
        contextStr += "&currentTimeStamp=" + currentTime;
        contextStr += "&expireTime=" + endTime;
        contextStr += "&random=" + random;
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(this.secretKey.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
            mac.init(secretKey);

            byte[] hash = mac.doFinal(contextStr.getBytes(CONTENT_CHARSET));
            byte[] sigBuf = byteMerger(hash, contextStr.getBytes("utf8"));
            strSign = base64Encode(sigBuf);
            strSign = strSign.replace(" ", "").replace("\n", "").replace("\r", "");
        } catch (Exception e) {
            throw e;
        }
        return strSign;
    }
    
    
    private String base64Encode(byte[] buffer) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(buffer);
    }
    
    public static void main(String[] args) {
        SignatureTest sign = new SignatureTest();
        sign.setSecretId("个人 API 密钥中的 Secret Id");
        sign.setSecretKey("个人 API 密钥中的 Secret Key");
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2);

        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
        }
    }
    
	
}
