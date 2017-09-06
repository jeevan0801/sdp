package cn.com.mewifi.core.util;

import java.util.Random;

/**
 * description:
 * author: wangjc
 * date: 2017/9/5 16:35
 */
public class MathUtil {
    /**
     * 生成指定长度的随机字符串
     * @param length 长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机数
     * @param length
     * @return 随机数字
     */
    public static String getRandomInt(int length) { //length表示生成字符串的长度
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }
}
