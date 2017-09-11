package cn.com.mewifi.sdp.service;

import com.alibaba.fastjson.JSONObject;

/**
 * description:
 * author: wangjc
 * date: 2017/9/5 16:11
 */
public abstract class AbstractSmsService {

    /**
     * 按内容发送短信
     * @param mobileNo 手机号码
     * @param content  发送内容
     * @return
     */
    public JSONObject sendByContent(String mobileNo, String content) {
        throw new IllegalArgumentException();
    }

    /**
     * 按模板发送短信
     * @param mobileNo 手机号码
     * @param templateId 模板id
     * @param tempLateParam 模板参数
     * @return
     */
    public JSONObject sendByTemplate(String mobileNo, String templateId, String tempLateParam) {
        throw new IllegalArgumentException();
    }
    
}
