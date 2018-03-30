package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/****
 * 产品父类
 */
@Data
public class BaseProduct implements Serializable {
    
    /******产品id******/
    private BigDecimal id;
    
    /******上游公司id******/
    protected BigDecimal spId;
    
    /******产品名称******/
    protected String productName;
    
    /******产品面值******/
    protected BigDecimal price;
    
    /******产品花费价格******/
    protected BigDecimal cost;
    
    /******目录id******/
    protected BigDecimal catalogId;
    
    /******产品详情******/
    protected String productDesc;
    
    /******产品状态1可用,0不可用******/
    protected String status;
    
    /******创建时间******/
    protected String createTime;
    
    /******交易大小******/
    protected BigDecimal tradeSize;
    
    /******交易单位******/
    protected String unit;
    
    /******产品code******/
    protected String code;
    
    /******产品所属类型******/
    protected String type;
    /***产品库存***/
    protected BigDecimal stock;
    
    protected static final long serialVersionUID = 1L;
    
}