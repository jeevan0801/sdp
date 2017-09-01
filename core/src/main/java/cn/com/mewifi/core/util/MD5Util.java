package cn.com.mewifi.core.util;

import java.io.UnsupportedEncodingException;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * description:
 *
 * author: wangjc
 *
 * date: 2017/8/14 10:19
 */
public class MD5Util {

    /**
     * 默认以utf-8加密
     * @param text
     * @return
     */
    public static String sign(String text) {
        return sign(text, "utf-8");
    }
    
    /**
     * @param text 需要签名的字符串
     * @param input_charset 字符集
     * @return 签名结果, 16机制字符串
     */
    public static String sign(String text, String input_charset) {
        byte[] bytes = new byte[0];
        try {
            bytes = text.getBytes(input_charset);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DigestUtils.md5DigestAsHex(bytes);
    }
    
    /**
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 字符集
     * @return 签名结果, 16机制字符串
     */
    public static String sign(String text, String key, String input_charset) {
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(key)) {
            return "";
        }
        text = text + key;
        return sign(text, input_charset);
    }
    
    /**
     * @param text 需要加密的字符串
     * @param sign 加密后的字符串
     * @param key 密钥
     * @param input_charset 字符集
     * @return boolean 签名是否通过
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = sign(text, key, input_charset);
        return mysign.equals(sign);
    }
    
}
