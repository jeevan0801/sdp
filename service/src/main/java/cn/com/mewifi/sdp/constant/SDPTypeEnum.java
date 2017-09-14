package cn.com.mewifi.sdp.constant;

import lombok.Getter;

/**
 * 上游公司类型
 * description:
 * author: wangjc
 * date: 2017/9/11 13:47
 */
public enum SDPTypeEnum {
    FLOWDATA("FDP","流量分发平台"),
    SMS("SMS","短信分发平台"),
    BILL("BILL","话费分发平台"),
    MEMBER("MEM","会员权益分发平台"),
    MEM_ORDER("MEM_ORDER","会员权益平台-订单")



    ;

    @Getter
    private String spType;

    @Getter
    private String desc;
    
    SDPTypeEnum(String spType,String desc) {
        this.spType = spType;
        this.desc = desc;
    }

    
    @Override
    public String toString() {
        return spType;
    }
}