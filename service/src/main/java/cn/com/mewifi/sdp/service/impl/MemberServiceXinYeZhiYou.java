package cn.com.mewifi.sdp.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.core.util.HttpUtil;
import cn.com.mewifi.core.util.SignUtil;
import cn.com.mewifi.core.util.third.MD5Util;
import cn.com.mewifi.sdp.service.IMemberService;

/**
 * description: 天津鑫业之游公司提供的爱奇艺会员周包接口
 *
 * author: wangjc
 *
 * date: 2017/8/31 14:58
 */
@Service
public class MemberServiceXinYeZhiYou implements IMemberService {
    private final String key = "bjxyzy2017";
    
    @Override
    public JSONObject order(String url, Map<String, Object> params) {
        // 所有参数键值对按照参数名字母序排序，使用'&'连接后
        String sortedStr = SignUtil.getSortedString(params);
        // 后面连接双方约定的密钥(secret)字符串，最后取MD5摘要(小写)
        String signStr = MD5Util.digestXyzy(sortedStr + key, "UTF-8", "MD5", false);
        url = url + "?" + sortedStr + "&sign=" + signStr;
        JSONObject result = HttpUtil.getForJSON(url);
        return result;
    }
}
