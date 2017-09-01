package cn.com.mewifi.core.util;

import java.util.List;

/**
 * description:
 *
 * author: wangjc
 *
 * date: 2017/8/29 16:24
 */
public class StringUtil {
    /**
     * 将list转为以,分割的字符串, 并去掉首位[]及空格
     * @param list
     * @return
     */
    public static String list2String(List<String> list) {
        String rs = "";
        if (!list.isEmpty()) {
            rs = list.toString();
            rs = rs.substring(1, rs.length() - 1); // 去掉首位的[]
            rs = rs.replaceAll(" ", "");// 去掉空格
        }
        return rs;
    }
}
