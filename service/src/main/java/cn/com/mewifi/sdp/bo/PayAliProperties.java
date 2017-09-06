package cn.com.mewifi.sdp.bo;

import lombok.Data;

/**
 * description:
 * author: wangjc
 * date: 2017/9/5 10:03
 */
@Data
public class PayAliProperties {
    private String payName;
    
    private String service;
    
    private String returnUrl;
    
    private String partner;
    
    private String inputCharset;
    
    private String notifyUrl;
    
    private String subject;
    
    private String paymentType;
    
    private String sellerId;
}
