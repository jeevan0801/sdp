package cn.com.mewifi.core.util;

import org.springframework.util.StringUtils;

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
     * @param removeEmpty 是否去掉空值
     * @return 按参数首字母排序后的字符串, 使用&连接
     */
    public static String getSortedString(Map<String, Object> params, boolean removeEmpty) {
        
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        
        String prestr = "";
        
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String)params.get(key);

            if(StringUtils.isEmpty(value) && removeEmpty) {
                continue;
            }

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

    /**
     * 默认的加密规则
     * 加密规则:
     * 1. 集合M中非空参数按照参数名从小到大排序
     * 2. 形成键值对,用&分割
     * 3. 加上key
     * 4. 转换大小写
     * @param params 参数集合
     * @param key   秘钥
     * @param toUpperCase 是否要大写
     * @return
     */
    public static String getMD5StrByDefault(Map<String, Object> params, String key, boolean toUpperCase, boolean needName) {
        String paramStr = SignUtil.getSortedString(params,true);
        return MD5Util.sign(paramStr,key,"utf-8",toUpperCase,needName);
    }

    /**默认的校验规则
     * @param params
     * @param key
     * @param toUperCase
     * @param needName
     * @return
     */
    public static boolean verifyMd5StrByDefault(Map<String,Object> params, String key, boolean toUperCase, boolean needName) {
        List<String> removedKey = new ArrayList<String>();
        removedKey.add("sign_type");
        removedKey.add("sign");
        if(StringUtils.isEmpty(params.get("sign"))) {
            return false;
        }
        String signText = (String)params.get("sign");
        String signType = (String)params.get("sign_type");

        if("MD5".equalsIgnoreCase(signType)) {
            // 待验证的集合中去掉sign_type和sign, 加密, 然后比较加密后的字符串
            Map<String,Object> paramsRemovedKey = paraFilte(params,removedKey);

            String mySignText = getMD5StrByDefault(paramsRemovedKey,key,toUperCase,needName);
            if(signText.equals(mySignText)) {
                return true;
            }
        }
        return false;
    }
    
}
