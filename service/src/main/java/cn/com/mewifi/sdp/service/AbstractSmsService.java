package cn.com.mewifi.sdp.service;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * description:
 * author: wangjc
 * date: 2017/9/5 16:11
 */
public abstract class AbstractSmsService {
    
    /**
     * 短信发送渠道
     */
    @Getter
    @Setter
    private String channelCode;
    
    /**
     * 日志记录主键, 用于上次业务记录日志时关联
     */
    @Getter
    @Setter
    private Integer logId;
    
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
