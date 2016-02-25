package cn.wemart.util;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.utils.DateUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public class RSASignature
{

    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALsgvG5f085Iv+/SSUQOuipniwmd4kvptQlOoO+F43wPi4T9dSQdm2kp0qAvdPlAI254cp3kpIio9xny0wzP0kOOwhQ1dqdWRszOxl+BxKoc3F0/kA9ql1lCmVrwfc6p510GBcFUIXu5vv1JMBd53tc7v+TjtURdpEQ/4vO2dWbHAgMBAAECgYBP9ELO3j7/qvHnpHANlkVRDQDJUR551Re3LFNzRD/YqEOyxSEiF0xp+Ka4Ls7KGO+Kqgg/EoyCn79E5Q3sMXfjDe3ajfe5Ljt2caUbkMtQ4QpApR7cMwD4s8TA8Np49K8oKhV6H1Fbc9Ng3G0hMd7Ossbiwe+pUhZ/5sPQzRabYQJBAO/3DM2CgJ9cWD6UyhcBDXTqeMEO4mJcrDWwOUnkhK8ucqiKhXu3WE7ca0rE+64foNNwzk6ShN5sc2M8dnuSAWkCQQDHodRpmMYG53ywj6oGQ9NAAYdSklnnQHAxcqkIOX+rjMA5/p+nFoj1U7U6yyj4RPnfH/SMOja2rSD0UB4bNPCvAkAnQVHt2Jhz+WZSLoL0ym5KHwVMB8RO9PwS+FYW2XQ/OCj3m1mEyJSAqhw/KgRGEtR7YmvNfLiRrkoeBMkZI+RpAkEAu9seTowz9PVK0b8vSRv0I6wMkngj38g0i2ORiqrI6D7X+PsRc6NyGCHATeY11ILb+8pPTtHNANQkSaNG4WVNAwJAGrMBcuELIN3hMOlscQ7lTh0DR4inxGubqJGs8tPeI8AgZJ++/aYoEolnZV8TpzcDkvParq8yL2oSoeA2p2KeIQ==";
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC7ILxuX9POSL/v0klEDroqZ4sJneJL6bUJTqDvheN8D4uE/XUkHZtpKdKgL3T5QCNueHKd5KSIqPcZ8tMMz9JDjsIUNXanVkbMzsZfgcSqHNxdP5APapdZQpla8H3OqeddBgXBVCF7ub79STAXed7XO7/k47VEXaREP+LztnVmxwIDAQAB";
    private static KeyPairGenerator keyGen = null;

    /**
     * 
     * @param appId 前端为scenId
     * @param userId 前端为buyerId
     */
    public static String getSign(String appId,String userId) throws UnsupportedEncodingException {
        String baozouSign = RSASignature.sign("trade_no=20150731113209320089", "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ3S1HREOSJt6SdOtu+O862gtuQTG41hFLclC4n83WapwG/jWC5T5cZ0x3GaxN/oLXqdLI23JqD+R//k4RolyY/24u+sxsDfmSZFzhJFLKRVbPrTsCqGZDrSHXssMN2i7bci+DemQzht9jOPyDcU3coXA/JhIDi1spsMOxKl+3dbAgMBAAECgYBjzv85ACQ519SJXUhnu7nzZh4DounbwTd+k1j2zQmfcV8X42jS84/ur8+p0r0K9SQ91MRmYxt02Rrjg/WvuZH8EVsa3gWyYNOMYtKVD5gKN1Z9JdZleUUctLTmKV4KXObxzsVdLnJ388ltQkqnFPtHV/WTme8cqCZ7AL1W/ZIg4QJBAMxSLMbrd30wMFXlOWylJ5tu7B4ErNxVlxg6wJsgPZNkbrZ0rwpciF1rfpSryIQUWHUg7ehKL/qjr5D6SX3gAmkCQQDFve+4s4sQeF7KbFzBLKp/86DPw1yocpdciITKYjJRxGoTjLAUT+Y+woNlYDG9VMOHJfageUsV4s+d8HpS56sjAkBJn4aw+uHogze3i8yAYJaABJ0iR+79yf2S5oDGpPxrIvJd3nfAVQ1yFCzdZ3TuWuJw+jefzzOwrdCyfe2DZPeZAkA0mxoaPhGh+RPmsDtg3NwjHBJNE4EkAxBHer4xBTyzyhgaSVRSowry6VaBcrzozJMT8kKRYJNcKDHpp58zTRSXAkBnGbGaG97/pqRbgJ0itPfMJtiRZAXD9YLJNuIrQvvtwVUwtRn0BoXAhKO9i17sesxdzNc0NSzyy8ag/0BCgPs1");
//        System.out.println( URLEncoder.encode(baozouSign, "UTF-8"));
//        System.out.println(privateKey.length());
//        System.out.println(publicKey.length());
        Date now = new Date();
        String endTime = DateUtils.formatDate(now, "yyyy-MM-dd 23:59:59");
        String startTime = DateUtils.formatDate(new Date(now.getTime() - 7 * 24 * 60 * 60 * 1000), "yyyy-MM-dd 00:00:00");
//        System.out.println(endTime);
//        System.out.println(startTime);


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