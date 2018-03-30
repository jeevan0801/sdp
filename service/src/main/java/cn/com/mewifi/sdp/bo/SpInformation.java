package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SpInformation implements Serializable {
    /******上游公司id******/
    protected BigDecimal id;
    
    /******上游公司的url******/
    protected String baseurl;
    
    /******上游公司名称******/
    protected String spname;
    
    /******上游公司给zzwx的key******/
    protected String key;
    
    /******上游公司给zzwx的开户id******/
    protected String accountid;
    
    /******上游公司类型******/
    protected String type;
    
    /******上游公司code******/
    protected String code;
    
    /******上游公司状态******/
    protected String status;
    
    /******上游公司创建时间******/
    protected String createtime;
    
    /******排序方式******/
    protected String sortid;

    /***公司详情***/
    protected String spdesc;
    private static final long serialVersionUID = 1L;
    
}