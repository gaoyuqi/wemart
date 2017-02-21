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
        String content = "appId="+appId+"&userId="+userId;
        String sign = null;
        boolean result = false;
        try
        {
            sign = sign(content, privateKey);
            result = verify(content, sign, publicKey);
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