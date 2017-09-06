package cn.com.mewifi.sdp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.core.util.HttpUtil;
import cn.com.mewifi.core.util.JSONUtil;
import cn.com.mewifi.core.util.MathUtil;
import cn.com.mewifi.sdp.service.ISmsService;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信网短信发送
 * description:
 * author: wangjc
 * date: 2017/9/5 17:43
 */
@Service
@Slf4j
public class SmsServiceImplDuanXinWang implements ISmsService {
    @Override
    public JSONObject sendAuthCode(String mobileNo) {
        String url = "http://web.duanxinwang.cc/asmx/smsservice.aspx?";
        String account = "dxwznwx007";
        String passwd = "A117AAA9B1262FD3E59ED25E9CB5";
        String caller = "10690681226522641989";
        String signText = "掌中无限";
        String smsContentStart = "您申请的手机验证码为";
        String smsContentEnd = ",请输入后进行验证,谢谢!";
        
        String smsContent = smsContentStart + MathUtil.getRandomInt(4) + smsContentEnd;
        
        Map<String, Object> params = new HashMap<>();
        params.put("name", account);
        params.put("pwd", passwd);
        params.put("mobile", mobileNo);
        params.put("content", smsContent);
        params.put("sign", signText);
        params.put("stime", ""); // 可为空，为空为及时发送
        params.put("type", "pt");
        params.put("extno", "");
        
        url += HttpUtil.getUrlFromMap(params);
        String rs = HttpUtil.postForString(url, null);
        
        return this.convertRs(rs);
    }

    /**
     * 结果转换. 返回的是字符串, 转为统一的JSON格式
     * @param rsString
     * @return
     */
    private JSONObject convertRs(String rsString) {
        JSONObject rs = new JSONObject();
        if (StringUtils.isEmpty(rsString)) {
            return rs;
        }
        
        String[] rsArray = rsString.split(",");
        Map<String, Object> map = new HashMap<>();
        map.put("code", rsArray[0]);
        map.put("sendid", rsArray[1]);
        map.put("invalidcount", rsArray[2]);
        map.put("successcount", rsArray[3]);
        map.put("blackcount", rsArray[4]);
        map.put("msg", rsArray[5]);
        
        rs = JSONObject.parseObject(JSONUtil.obj2JsonString(map));
        return rs;
    }
}
