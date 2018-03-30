package cn.com.mewifi.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 * author: Administrator
 * date: 2017-09-25 15:50
 */
public class DateUtil {
    /***
     * 得到格式化时间字符串
     * @param rule 字符串格式
     * @return
     */
    public static String getDateString(String rule){
        if(rule == null || rule.length()==0){
            rule = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(rule);
        return sdf.format(new Date());
    }

    public static String getDefaultDateString(){
        return getDateString("yyyy-MM-dd HH:mm:ss");
    }
}
