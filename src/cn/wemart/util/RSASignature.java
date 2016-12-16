package cn.wemart.util;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSASignature
{

    private static String privateKey = LoadSignString.privateKey;
    private static String publicKey = LoadSignString.publicKey;
    private static KeyPairGenerator keyGen = null;

    /**
     * 
     * @param appId 前端为scenId
     * @param userId 前端为buyerId
     */
    public static String getSign(String appId,String userId) throws UnsupportedEncodingException {
//        System.out.println( URLEncoder.encode(baozouSign, "UTF-8"));
//        System.out.println(privateKey.length());
//        System.out.println(publicKey.length());
//        System.out.println(endTime);
//        System.out.println(startTime);

//    	System.out.println("++++++++++++"+privateKey+"++++++++++++");
//    	System.out.println("++++++++++++"+publicKey+"++++++++++++");

//        privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIWzeML/ReL0y7MF4PxP9PgDAiLes01cYbfTEhYUDuh+2v2pW9g61zlJU8MxP5Sc0y7ThHD5jBoFxmCUBniRfgygO1h7QNMr+zprqJ9HTFaMHFwbl6Leql8nOzL2vCMTZon+7iGhN9FpVK6iQnGOdCXc3iL3AFBgXL2HNUZqAcSJAgMBAAECgYBb7q7/rdVZHCegfk9YToZLro3eejDh5b7PTlPmWH5zwR5FQxmDEdqBanktAwUL49WWIhy0LfL2xbfFGqNkbe40M3e5DoDnwYhhYpRmJQbNy8QHagwAgJlNiLAZ7UZvH0W0oRvs7pqlE/nXyhm8JoAV+XBYH5q36aryVOf+lQaSAQJBAMPNkrPhSOLszl9SZ3CVredOjDj8Mi92ZB9wzvkfouo44VQTbdEtBlAd58Q+UDrJskH4xPWOUq/5yC38ZvicYcECQQCuzj5FKSNYJlnC8LleCJ/S3yTAk3YFY77BeVqu43hljPZ5e9vjfv+qlXlCo7qdOhdiKhG6XzrGH2OsUi8uMgTJAkEAiEF4Q2EUvYWZH1BS10/x0rMgdU9bBRhYGGECRu6zCldGxqE2nIe+iuYksLXEUwSk3a/mMBM4i2lA2m94oK0CgQJAGt7FH6h1MGWkwpxyQgwdVTLL2XZRW/67Ic5frCZ3KVUDxiTN5ihtcHFNlHrKgsSdN4+z7ewZgr7seV6gtf8mUQJANAeFVbmbn6nVAvNnna7urJYwHiO6fk0bkxJHzajwfqbAkq4/jAuFIVheFVxTH8JY7oMjPvCg/+EVUiESwjO39A==";
//        publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFs3jC/0Xi9MuzBeD8T/T4AwIi3rNNXGG30xIWFA7oftr9qVvYOtc5SVPDMT+UnNMu04Rw+YwaBcZglAZ4kX4MoDtYe0DTK/s6a6ifR0xWjBxcG5ei3qpfJzsy9rwjE2aJ/u4hoTfRaVSuokJxjnQl3N4i9wBQYFy9hzVGagHEiQIDAQAB";
        String content = "appId="+appId+"&userId="+userId;
        String sign = null;
        boolean result = false;
        try
        {
            sign = sign(content, privateKey);
//            System.out.println(sign);
            result = verify(content, sign, publicKey);
//            System.out.println("result: " + result);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return sign;
    }

    public static KeyPair genKeypair()
    {
        if (keyGen == null)
        {
            try
            {
                keyGen = KeyPairGenerator.getInstance("RSA");
            } catch (NoSuchAlgorithmException e)
            {
            }
            if (keyGen == null)
            {
                return null;
            }
            keyGen.initialize(1024);
        }
        return keyGen.generateKeyPair();
    }

    public static String getPublicKeyString(KeyPair keyPair)
    {
        if (keyPair == null)
        {
            return "";
        }
        return Base64.encodeBase64String(keyPair.getPublic().getEncoded());
    }

    public static String getPrivateKeyString(KeyPair keyPair)
    {
        if (keyPair == null)
        {
            return "";
        }
        return Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
    }

    /**
     * 签名
     *
     * @param content
     * @param privateKey
     * @return
     */
    public static String sign(String content, String privateKey)
    {
        try
        {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decodeBase64(privateKey.getBytes()));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance("SHA1WithRSA");

            signature.initSign(priKey);
            signature.update(content.getBytes("UTF-8"));

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 验签
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean verify(String content, String sign, String publicKey)
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(publicKey.getBytes());
            PublicKey pubKey = keyFactory
                    .generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature
                    .getInstance("SHA1WithRSA");

            signature.initVerify(pubKey);
            signature.update(content.getBytes("UTF-8"));

            boolean bverify = signature.verify(Base64.decodeBase64(sign
                    .getBytes()));
            return bverify;

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static String getPublicKey() {
        return publicKey;
    }
}