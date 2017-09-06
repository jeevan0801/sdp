package cn.com.mewifi.core.util;

import java.util.*;

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
    
    /**
     * 从map中移除key
     * @param params map
     * @param removeKeys 需要移除的keys
     * @return map
     */
    public static Map<String, Object> paraFilte(Map<String, Object> params, List<String> removeKeys) {
        Map<String, Object> rs = new HashMap<>();
        if (params == null || params.size() <= 0) {
            return rs;
        }

        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (value == null || value.equals("") || removeKeys.contains(key)) {
                continue;
            }
            rs.put(key, value);
        }
        
        return rs;
    }
    
}
