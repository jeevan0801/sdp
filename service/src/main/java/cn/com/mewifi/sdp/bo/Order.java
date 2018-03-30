package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class Order implements Serializable {
    /****主键***/
    private BigDecimal id;
    /****订单名***/
    protected String ordername;
    /*****订单流水号******/
    protected String ordernumber;
    /*****创建时间******/
    protected Date createtime;
    /*****更新时间******/
    protected Date updatetime;
    /*****订单状态******/
    protected String status;
    /*****订单状态描述******/
    protected String resultdesc;
    /*****订单总价******/
    protected BigDecimal totalprice;
    /*****订单总量******/
    protected BigDecimal total;
    /*****订单支付方法******/
    protected String paymethod;
    /*****订单描述******/
    protected String orderdesc;

    /*****类型******/
    protected String type;


    private static final long serialVersionUID = 1L;


}