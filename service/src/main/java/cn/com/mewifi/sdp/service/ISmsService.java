package cn.com.mewifi.sdp.service;

import com.alibaba.fastjson.JSONObject;

/**
 * description:
 * author: wangjc
 * date: 2017/9/5 16:11
 */
public interface ISmsService {
    /**
     * 短信验证码发送
     * @param mobileNo 手机号码
     * @return
     */
    JSONObject sendAuthCode(String mobileNo);
}
