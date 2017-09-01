package cn.com.mewifi.sdp.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * description: author: wangjc date: 2017/8/31 13:48
 */
public interface IMemberService {
    
    /**
     * 订购
     * 
     * @param url 订购地址
     * @param params 订购参数
     * @return Json
     */
    JSONObject order(String url, Map<String, Object> params);
    
    // JSONObject unsubscirte();
}
