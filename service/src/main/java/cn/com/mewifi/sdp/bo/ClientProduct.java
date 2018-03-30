package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/****
 * 客户端产品父类
 */
@Data
public class ClientProduct implements Serializable {
    /******产品id******/
    protected BigDecimal id;
    
    /******对应BaseProduct的code******/
    protected String baseproductcode;
    
    /******产品名称******/
    protected String productname;
    protected String code;
    /******产品面值******/
    protected BigDecimal price;
    
    /******产品花费******/
    protected BigDecimal cost;
    
    /******目录id******/
    protected BigDecimal catalogid;
    
    /******产品详情******/
    protected String productdesc;
    
    /******产品状态1可用,0不可用******/
    protected String status;
    
    /******产品创建时间******/
    protected String createtime;
    
    /******产品大小******/
    protected BigDecimal tradesize;
    
    /******产品大小单位******/
    protected String unit;

    /******排序方式******/
    protected String sortid;
    
    /******类型******/
    protected String type;
    /***下游客户名称**/
    protected String clientname;
    /***下游客户code**/
    protected String clientcode;

    protected static final long serialVersionUID = 1L;
    
}