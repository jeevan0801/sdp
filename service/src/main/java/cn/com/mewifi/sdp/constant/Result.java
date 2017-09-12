package cn.com.mewifi.sdp.constant;

import lombok.Getter;

/**
 * 返回结果描述
 * description:
 * author: wangjc
 * date: 2017/9/11 17:59
 */
public enum Result {

    pub_success("1","success"),
    pub_action_todo("0","todo"),
    pub_action_doing("9","doing"),
    pub_action_finish("1","finish"),

    // 报错提示信息 模块名_动作_提示
    pub_sms_sendFail("-1003","短信发送失败"),
    pub_authCode_outOfLimit("-1001","短信验证码获取过于频繁,请稍后再试"),
    pub_authCode_noPass("-1002","短信验证码验证不通过,请重新输入");

    Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    private String code;

    @Getter
    private String msg;


    @Override
    public String toString() {
        return super.toString();
    }
}
