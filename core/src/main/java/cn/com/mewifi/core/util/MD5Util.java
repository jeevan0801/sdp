package cn.com.mewifi.core.util;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * description:
 *
 * author: wangjc
 *
 * date: 2017/8/14 10:19
 */
@Slf4j
public class MD5Util {
    
    /**
     * 默认以utf-8加密
     * @param text
     * @return
     */
    public static String sign(String text, boolean toUpperCase) {
        return sign(text, "utf-8", toUpperCase);
    }
    
    /**
     * @param text 需要签名的字符串
     * @param input_charset 字符集
     * @param toUpperCase   是否转为大写
     * @return 签名结果, 16机制字符串
     */
    public static String sign(String text, String input_charset, boolean toUpperCase) {
        byte[] bytes = new byte[0];
        try {
            bytes = text.getBytes(input_charset);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return toUpperCase ? DigestUtils.md5DigestAsHex(bytes).toUpperCase()
            : DigestUtils.md5DigestAsHex(bytes).toLowerCase();
    }
    
    /**
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 字符集
     * @param toUpperCase   是否需要大写
     * @param needName  秘钥加到最后是否需要"&key="
     * @return 签名结果, 16机制字符串
     */
    public static String sign(String text, String key, String input_charset, boolean toUpperCase, boolean needName) {
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(key)) {
            return "";
        }
        
        if (needName) {
            text = text + "&key=" + key;
        }
        else {
            text = text + key;
        }
        log.debug("md5加密原文:{}",text);
        String rs = sign(text, input_charset, toUpperCase);
        log.debug("md5加密密文:{}",rs);
        return rs;
    }
    
    /**
     * @param text 需要加密的字符串
     * @param sign 加密后的字符串
     * @param key 密钥
     * @param input_charset 字符集
     * @param needName      秘钥加到最后是否需要"&key="
     * @return boolean 签名是否通过
     */
    public static boolean verify(String text, String sign, String key, String input_charset, boolean needName) {
        if(needName) {
            text = text + "&key=" + key;
        }else {
            text = text + key;
        }

        String mysign = sign(text, key, input_charset, false,needName);
        return mysign.equalsIgnoreCase(sign);
    }
    
}
