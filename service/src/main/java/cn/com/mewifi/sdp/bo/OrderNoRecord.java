package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderNoRecord implements Serializable {
    private BigDecimal id;

    private String clientno;

    private String ordernumber;

    private String mobile;

    private String baseproductcode;

    private Date createtime;

    private Date updatetime;

    private String type;

    private String stauts;

    private String resultdesc;

    private static final long serialVersionUID = 1L;


}