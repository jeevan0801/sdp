package cn.com.mewifi.core.util.third;

import java.security.MessageDigest;

/**
 * description: 第三方提供的工具类
 *
 * author: wangjc
 *
 * date: 2017/8/14 16:19
 */
public class MD5Util {
    
    /**
     * 天津鑫业之游提供的md5加密方法
     * 
     * @param s 待加密字符串
     * @param encoding 字符集. "UTF-8"
     * @param algorithm "MD5"
     * @param outputUpperCase 是否大写: false
     * @return
     */
    public static final String digestXyzy(String s, String encoding, String algorithm, boolean outputUpperCase) {
        final char[] hex = "0123456789ABCDEF".toCharArray();
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.reset();
            digest.update(s.getBytes(encoding));
            byte[] bb = digest.digest();
            StringBuilder out = new StringBuilder();
            for (byte b : bb) {
                out.append(hex[(b & 0xF0) >>> 4]).append(hex[b & 0x0F]);
            }
            
            String result = out.toString();
            return outputUpperCase ? result : result.toLowerCase();
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
