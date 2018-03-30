package cn.com.mewifi.sdp.util.third;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * description: 第三方提供的工具类
 *
 * author: wangjc
 *
 * date: 2017/8/14 16:19
 */
public class ThirdUtil {
    
    /**
     * 天津鑫业之游提供的md5加密方法
     * 
     * @param s 待加密字符串
     * @param encoding 字符集. "UTF-8"
     * @param algorithm "MD5"
     * @param outputUpperCase 是否大写: false
     * @return
     */
    public static final String md5ForXyzy(String s, String encoding, String algorithm, boolean outputUpperCase) {
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
    
    /**
     * wo+提供的加密方法
     * @param tm
     * @param key
     * @return
     */
    public static String md5ForWO(TreeMap<String, String> tm, String key) {
        StringBuffer buf = new StringBuffer();
        for (Map.Entry<String, String> en : tm.entrySet()) {
            String name = en.getKey();
            String value = en.getValue();
            if (value != null && value.length() > 0 && !"null".equals(value)) {
                buf.append(name).append('=').append(value).append('&');
            }
        }
        String bufString = buf.toString();
        
        String verifyReq = getKeyedDigestGBK(bufString.substring(0, bufString.length() - 1), key);
        
        return verifyReq;
    }
    
    /** wo+提供的加密方法
     * @param strSrc
     * @param key
     * @return
     */
    private static String getKeyedDigestGBK(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("GBK"));
            
            String result = "";
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF8"));
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
            
            return result.toUpperCase();
            
        }
        catch (NoSuchAlgorithmException e) {
            
            e.printStackTrace();
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
