package cn.com.mewifi.sdp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.core.util.HttpUtil;
import cn.com.mewifi.core.util.MD5Util;
import cn.com.mewifi.sdp.service.IMemberService;

/**
 * description: 腾瑞明提供的qq币充值接口
 *
 * author: wangjc
 *
 * date: 2017/8/31 13:49
 */
@Service
public class MemberServiceTengRuiMing implements IMemberService {
    
    private String mid = "91019";
    
    private String key = "3392cd912df17526da7e06fb7161a176";
    
    /**
     * 直冲下单接口 调用此接口为qq号充值，同时扣减商户对应金额
     */
    @Override
    public JSONObject order(String url, Map<String, Object> params) {
        
        JSONObject rs = HttpUtil.getForJSON(url, params);
        return rs;
    }
    
    /**
     * 查询余额
     */
    public void queryBalance() {
        String url = "http://101.200.72.49:8080/qq-coins/qqApi/zhichong";
        
        String action = "account";
        String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action", action);
        params.put("m", mid);
        params.put("date", date);
        params.put("s", MD5Util.sign(action + mid + date + key));
        
        JSONObject rs = HttpUtil.getForJSON(url, params);
    }
    
    /**
     * 查询订单
     */
    public void queryOrder() {
        String url = "http://101.200.72.49:8080/qq-coins/qqApi/zhichong";
        
        String action = "query";
        String orderid = "";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action", action);
        params.put("orderid", orderid);
        params.put("s", MD5Util.sign(action + mid + orderid + key));
        
        JSONObject rs = HttpUtil.getForJSON(url, params);
    }
    
}
