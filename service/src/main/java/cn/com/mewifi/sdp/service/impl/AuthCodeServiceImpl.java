package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.core.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.sdp.service.AbstractSmsService;
import cn.com.mewifi.sdp.service.IAuthCodeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * description:
 * author: wangjc
 * date: 2017/9/11 11:55
 */
@Service
public class AuthCodeServiceImpl implements IAuthCodeService {
    
    @Autowired
    @Qualifier("smsServiceImplDuanXinWang")
    AbstractSmsService smsService;
    
    @Override
    public JSONObject sendAuthCode(String receiver) {
         String smsContentStart = "您申请的手机验证码为";
         String smsContentEnd = ",请输入后进行验证,谢谢!";

         String smsContent = smsContentStart + MathUtil.getRandomInt(4) + smsContentEnd;
        return smsService.sendByContent(receiver,smsContent);
    }
    
    @Override
    public JSONObject verifyAuthCode(String receiver, String authCode) {
        return null;
    }
}
