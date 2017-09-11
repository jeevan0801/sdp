package cn.com.mewifi.sdp.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 验证码service, 包括短信, 邮件等
 * description:
 * author: wangjc
 * date: 2017/9/11 11:26
 */
public interface IAuthCodeService {

    /**
     * 发送验证码
     * @param receiver
     * @return
     */
    JSONObject sendAuthCode(String receiver);
    
    /**
     * 校验短信验证码是否正确
     * @param receiver 接收者
     * @param authCode 待验证的验证码
     * @return
     */
    JSONObject verifyAuthCode(String receiver, String authCode);
}
