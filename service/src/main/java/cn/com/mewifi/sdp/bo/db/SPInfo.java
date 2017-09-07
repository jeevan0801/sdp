package cn.com.mewifi.sdp.bo.db;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * description:
 * author: wangjc
 * date: 2017/9/7 18:57
 */
@Data
public class SPInfo implements Serializable {
    private String baseUrl; // 接口基础url
    
    private String spName; // sp名称
    
    private String spId; // sp id
    
    private String key; // sp分配给我们的秘钥
    
    private String accountId; // sp分配的账号
    
    private Map<String, Object> otherParams; // 根据协议需要送的其他参数, 一般是固定的
}
