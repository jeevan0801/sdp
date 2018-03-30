package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderDetail implements Serializable {
    /****主键***/
    private BigDecimal id;
    /****OrderNumber***/
    protected String ordernumber;
    /****detail流水号***/
    protected String detailno;
    /****产品code***/
    protected String baseproductcode;
    /****detail名称***/
    protected String orderdetailname;
    /****价格***/
    protected BigDecimal price;
    /****花费***/
    protected BigDecimal cost;
    /****状态***/
    protected String status;
    /****状态描述***/
    protected String resultdesc;
    /****创建时间***/
    protected Date createtime;
    /****修改时间***/
    protected Date updatedate;
    /****下游公司id***/
    protected BigDecimal clientid;
    /****下游公司名称***/
    protected String clientname;
    /****上游公司id***/
    protected BigDecimal spid;
    /****上游公司名称***/
    protected String spname;
    /****充值的帐户***/
    protected String account;
    /****detail code***/
    protected String code;
    /****类型***/
    protected String type;

    private static final long serialVersionUID = 1L;

}