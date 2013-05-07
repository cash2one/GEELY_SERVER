package com.fsc.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:加密解密工具 封装了一些加密工具方法, 包括 3DES, MD5 等 DES加解密程序，desDecrypt解密；desEncrypt加密 MD5加密程序, MD5加密-不可逆的字符串变换算法</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class CryptoUtil {
    private final static String secretKey = "i4j2kj34sdeljksd993jjf22";

    public CryptoUtil() {
    }

    /**
     * MD5加密 - 不可逆的字符串变换算法
     * 将“字节串”变换成一个128bit的大整数,表示成16进制的ASCII需要32个字符
     * @param String
     * @return String
     */
    public final static String MD5(String s) {
        char[] hexDigits = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
                'D', 'E', 'F'
            };

        //    	char[] hexDigits = {
        //                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
        //                'D', 'E', 'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
        //            };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }

            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 3DES 加密（密钥固定）
     * @param String 明文串
     * @return String 密文串
     * @throws Exception
     */
    public static String desEncrypt(String src) throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        javax.crypto.SecretKey key;
        byte[] bytReturn;
        key = genDESKey(secretKey.getBytes());
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
        bytReturn = cipher.doFinal(src.getBytes());

        return base64Encode(bytReturn);
    }

    /**
     * 3DES 加密（密钥固定）
     * @param String 明文串
     * @param StringBuffer 异常消息
     *
     * @return String 密文串
     */
    public static String desEncrypt(String src, StringBuffer sbfMessage) {
        byte[] bytReturn = null;

        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(
                    "DESede");
            javax.crypto.SecretKey key;
            key = genDESKey(secretKey.getBytes());
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
            bytReturn = cipher.doFinal(src.getBytes());
        } catch (NoSuchAlgorithmException e1) {
            sbfMessage.append(e1.getMessage());
        } catch (NoSuchPaddingException e2) {
            sbfMessage.append(e2.getMessage());
        } catch (InvalidKeyException e3) {
            sbfMessage.append(e3.getMessage());
        } catch (IllegalBlockSizeException e4) {
            sbfMessage.append(e4.getMessage());
        } catch (BadPaddingException e5) {
            sbfMessage.append(e5.getMessage());
        } catch (Exception e6) {
            sbfMessage.append(e6.getMessage());
        }

        return base64Encode(bytReturn);
    }

    /**
     * 3DES加密（密钥动态产生）
     * @param String 明文串
     * @param String 密钥源串
     * @return String 密文串
     * @throws Exception
     */
    public static String desEncrypt(String src, String keysrc)
        throws Exception {
        byte[] bytReturn = null;
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");

        javax.crypto.SecretKey key;
        int keysrcLength = keysrc.length();

        if (keysrcLength < 24) {
            for (int i = keysrcLength; i < 24; i++) {
                keysrc = keysrc + keysrc;
            }

            keysrc = keysrc.substring(0, 24);
        }

        key = genDESKey(keysrc.getBytes());

        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
        bytReturn = cipher.doFinal(src.getBytes());

        return base64Encode(bytReturn);
    }

    /**
     * 3DES加密（密钥动态产生）
     * @param String 明文串
     * @param String 密钥源串
     * @param StringBuffer 异常消息
     * @return String 密文串
     */
    public static String desEncrypt(String src, String keysrc,
        StringBuffer sbfMessage) {
        byte[] bytReturn = null;

        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(
                    "DESede");

            javax.crypto.SecretKey key;
            int keysrcLength = keysrc.length();

            if (keysrcLength < 24) {
                for (int i = keysrcLength; i < 24; i++) {
                    keysrc = keysrc + keysrc;
                }

                keysrc = keysrc.substring(0, 24);
            }

            key = genDESKey(keysrc.getBytes());

            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
            bytReturn = cipher.doFinal(src.getBytes());
        } catch (NoSuchAlgorithmException e1) {
            sbfMessage.append(e1.getMessage());
        } catch (NoSuchPaddingException e2) {
            sbfMessage.append(e2.getMessage());
        } catch (InvalidKeyException e3) {
            sbfMessage.append(e3.getMessage());
        } catch (IllegalBlockSizeException e4) {
            sbfMessage.append(e4.getMessage());
        } catch (BadPaddingException e5) {
            sbfMessage.append(e5.getMessage());
        } catch (Exception e6) {
            sbfMessage.append(e6.getMessage());
        }

        return base64Encode(bytReturn);
    }

    /**
     * 3DES 解密（密钥固定）
     * @param String 密文串
     * @return String 明文串
     * @throws Exception
     */
    public static String desDecrypt(String crypt) throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        javax.crypto.SecretKey key;
        key = genDESKey(secretKey.getBytes());

        byte[] bytReturn;
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
        bytReturn = cipher.doFinal(base64DecodeToBytes(crypt));

        return base64Decode(base64Encode(bytReturn));
    }

    /**
     * 3DES 解密（密钥固定）
     * @param String 密文串
     * @param StringBuffer 异常消息
     *
     * @return String 明文串
     */
    public static String desDecrypt(String crypt, StringBuffer sbfMessage) {
        byte[] bytReturn = null;

        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(
                    "DESede");
            javax.crypto.SecretKey key;
            key = genDESKey(secretKey.getBytes());
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
            bytReturn = cipher.doFinal(base64DecodeToBytes(crypt));
        } catch (NoSuchAlgorithmException e1) {
            sbfMessage.append(e1.getMessage());

            return "";
        } catch (NoSuchPaddingException e2) {
            sbfMessage.append(e2.getMessage());

            return "";
        } catch (InvalidKeyException e3) {
            sbfMessage.append(e3.getMessage());

            return "";
        } catch (IllegalBlockSizeException e4) {
            sbfMessage.append(e4.getMessage());

            return "";
        } catch (BadPaddingException e5) {
            sbfMessage.append(e5.getMessage());

            return "";
        } catch (Exception e6) {
            sbfMessage.append(e6.getMessage());

            return "";
        }

        return base64Decode(base64Encode(bytReturn));
    }

    /**
     * 3DES 解密（密钥动态产生）
     * @param String 密文串
     * @param String 密钥源串
     * @return String 明文串
     * @throws Exception
     */
    public static String desDecrypt(String crypt, String keysrc)
        throws Exception {
        byte[] bytReturn = null;
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        javax.crypto.SecretKey key;
        int keysrcLength = keysrc.length();

        if (keysrcLength < 24) {
            for (int i = keysrcLength; i < 24; i++) {
                keysrc = keysrc + keysrc;
            }

            keysrc = keysrc.substring(0, 24);
        }

        key = genDESKey(keysrc.getBytes());
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
        bytReturn = cipher.doFinal(base64DecodeToBytes(crypt));

        return base64Decode(base64Encode(bytReturn));
    }

    /**
     * 3DES 解密（密钥动态产生）
     * @param String 密文串
     * @param String 密钥源串
     * @param StringBuffer 异常消息
     *
     * @return String 明文串
     */
    public static String desDecrypt(String crypt, String keysrc,
        StringBuffer sbfMessage) {
        byte[] bytReturn = null;
        String strReturn = "";

        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(
                    "DESede");
            javax.crypto.SecretKey key;
            int keysrcLength = keysrc.length();

            if (keysrcLength < 24) {
                for (int i = keysrcLength; i < 24; i++) {
                    keysrc = keysrc + keysrc;
                }

                keysrc = keysrc.substring(0, 24);
            }

            key = genDESKey(keysrc.getBytes());
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
            bytReturn = cipher.doFinal(base64DecodeToBytes(crypt));
            strReturn = base64Decode(base64Encode(bytReturn));
        } catch (NoSuchAlgorithmException e1) {
            sbfMessage.append(e1.getMessage());
        } catch (NoSuchPaddingException e2) {
            sbfMessage.append(e2.getMessage());
        } catch (InvalidKeyException e3) {
            sbfMessage.append(e3.getMessage());
        } catch (IllegalBlockSizeException e4) {
            sbfMessage.append(e4.getMessage());
        } catch (BadPaddingException e5) {
            sbfMessage.append(e5.getMessage());
        } catch (Exception e6) {
            sbfMessage.append(e6.getMessage());
        }

        return strReturn;
    }

    /**
     * 生成3DES密钥.
     * @param byte[]
     * @return SecretKey
     */
    public static SecretKey genDESKey(byte[] byteKey) {
        SecretKey k = null;
        k = new SecretKeySpec(byteKey, "DESede");

        return k;
    }

    /**
     * BASE64 编码.
     * @param String
     * @return String
     */
    public static String base64Encode(String src) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();

        return encoder.encode(src.getBytes());
    }

    /**
     * BASE64 编码(byte[]).
     * @param byte[]
     * @return String
     */
    public static String base64Encode(byte[] src) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();

        return encoder.encode(src);
    }

    /**
     * BASE64 解码.
     * @param String
     * @return String
     */
    public static String base64Decode(String src) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

        try {
            return new String(decoder.decodeBuffer(src));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * BASE64 解码(to byte[]).
     * @param String
     * @return String
     */
    public static byte[] base64DecodeToBytes(String src) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

        try {
            return decoder.decodeBuffer(src);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 对给定字符进行 URL 编码.
     * @param String
     * @return String
     */
    public static String urlEncode(String src) {
        try {
            src = java.net.URLEncoder.encode(src, "GBK");

            return src;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return src;
    }

    /**
     * 对给定字符进行 URL 解码
     * @param String 解码前的字符串
     * @return String 解码后的字符串
     */
    public String urlDecode(String value) {
        try {
            return java.net.URLDecoder.decode(value, "GBK");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    public static void main(String[] args) throws Exception{

        try {
            System.out.println(MD5("123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
