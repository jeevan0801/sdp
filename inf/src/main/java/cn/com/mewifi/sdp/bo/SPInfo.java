package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.util.Map;

/**
 * description: 上游公司信息
 * author: wangjc
 * date: 2017/8/31 19:24
 */
@Data
public class SPInfo {

    private String baseUrl; //接口基础url
    private String spName; //sp名称
    private String spId;    // sp id
    private String key; // sp分配给我们的秘钥
    private String accountId; // sp分配的账号
    private Map<String,Object> otherParams; //根据协议需要送的其他参数, 一般是固定的
}
