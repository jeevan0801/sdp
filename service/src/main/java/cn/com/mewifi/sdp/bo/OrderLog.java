package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderLog implements Serializable {
    private BigDecimal id;

    private Date createtime;

    private BigDecimal orderid;

    private BigDecimal orderdetailid;

    private String clientname;

    private String account;

    private String spname;

    private String baseproductcode;

    private BigDecimal price;

    private String paydesc;

    private String type;
    private String code;

    private static final long serialVersionUID = 1L;


}