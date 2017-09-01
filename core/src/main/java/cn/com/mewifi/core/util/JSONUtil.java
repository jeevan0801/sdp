package cn.com.mewifi.core.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * description:
 *
 * author: wangjc
 *
 * date: 2017/8/29 15:39
 */
public class JSONUtil {
    
    /**
     * json字符串转map
     * 
     * @param json
     * @return
     */
    public static Map<String, Object> jsonString2Map(String json) {
        return JSON.parseObject(json, Map.class);
    }
    
    /**
     * json字符串转List
     * 
     * @param json
     * @return
     */
    public static List jsonString2List(String json) {
        return JSON.parseObject(json, List.class);
    }
    
    /**
     * 对象转json字符串
     * 
     * @param obj
     * @return
     */
    public static String obj2JsonString(Object obj) {
        return JSON.toJSONString(obj);
    }
}
