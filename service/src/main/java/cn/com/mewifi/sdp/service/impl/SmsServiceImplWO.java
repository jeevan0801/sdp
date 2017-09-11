package cn.com.mewifi.sdp.service.impl;

import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.core.util.HttpUtil;
import cn.com.mewifi.core.util.JSONUtil;
import cn.com.mewifi.core.util.MathUtil;
import cn.com.mewifi.core.util.third.MD5Util;
import cn.com.mewifi.sdp.service.AbstractSmsService;
import lombok.extern.slf4j.Slf4j;

/**
 * wo+ 短信发送
 * description:
 * author: wangjc
 * date: 2017/9/5 16:11
 */
@Service
@Slf4j
public class SmsServiceImplWO extends AbstractSmsService {// implements ISmsService {
    
    public JSONObject sendByTemplate(String mobileNo, String templateId, String tempLateParam) {
        JSONObject rsJson = new JSONObject();
        String url = "http://111.206.169.90:18042/templetSubmit/"; // wo+短信接口地址
        String loginName = "215";
        String passwd = "215";
        
        if (StringUtils.isEmpty(mobileNo)) {
            return rsJson;
        }
        
        // String paramStr = "{'code':'" + MathUtil.getRandomInt(4) + "'}";
        
        TreeMap<String, String> params = new TreeMap<>();
        params.put("SmsTempletID", templateId);
        params.put("LoginName", loginName);
        params.put("UserNumber", "86" + mobileNo);
        params.put("Parameter", tempLateParam);
        params.put("Timestamp", System.currentTimeMillis() + "");
        
        String sign = MD5Util.digestWO(params, passwd);
        
        params.put("Verify", sign);
        
        String paramsJsonString = JSONUtil.obj2JsonString(params);
        
        rsJson = HttpUtil.postJSONForJSON(url, paramsJsonString);
        log.info("rs={}", rsJson);
        return rsJson;
    }
//
//    // @Override
//    // public JSONObject sendAuthCode(String mobileNo) {
//    public JSONObject sendAuthCode(String mobileNo) {
//        JSONObject rsJson = new JSONObject();
//        String url = "http://111.206.169.90:18042/templetSubmit/"; // wo+短信接口地址
//        String templetId = "21501612271122482622779";
//        String loginName = "215";
//        String passwd = "215";
//
//        if (StringUtils.isEmpty(mobileNo)) {
//            return rsJson;
//        }
//
//        String paramStr = "{'code':'" + MathUtil.getRandomInt(4) + "'}";
//
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("SmsTempletID", templetId);
//        params.put("LoginName", loginName);
//        params.put("UserNumber", "86" + mobileNo);
//        params.put("Parameter", paramStr);
//        params.put("Timestamp", System.currentTimeMillis() + "");
//
//        String sign = MD5Util.digestWO(params, passwd);
//
//        params.put("Verify", sign);
//
//        String paramsJsonString = JSONUtil.obj2JsonString(params);
//
//        rsJson = HttpUtil.postJSONForJSON(url, paramsJsonString);
//        log.info("rs={}", rsJson);
//        return rsJson;
//    }
}
