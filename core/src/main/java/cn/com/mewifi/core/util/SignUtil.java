package cn.com.mewifi.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * description: 签名工具类
 *
 * author: wangjc
 *
 * date: 2017/8/14 10:18
 */
public class SignUtil {
    
    /**
     * @param params 需要排序的map
     * @return 按参数首字母排序后的字符串, 使用&连接
     */
    public static String getSortedString(Map<String, Object> params) {
        
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        
        String prestr = "";
        
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String)params.get(key);
            
            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            }
            else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        
        return prestr;
    }
    
}
