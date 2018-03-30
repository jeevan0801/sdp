package cn.com.mewifi.sdp.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ClientInformation implements Serializable {
    /******客户id******/
    protected BigDecimal id;
    
    /******客户名称******/
    protected String clientname;
    
    /******客户key用于加密******/
    protected String key;
    
    /******客户 accountid******/
    protected String accountid;
    
    /******客户类型*****/
    protected String type;
    
    /******客户code*****/
    protected String code;
    
    /******客户状态******/
    protected String status;
    
    /******排序方式******/
    protected String sortid;
    
    protected Date createtime; // 创建时间
    
    protected Date expiretime; // 失效时间
    
    /******客户的能调用ip的白名单******/
    protected String serverips;
    
    private static final long serialVersionUID = 1L;
    
}