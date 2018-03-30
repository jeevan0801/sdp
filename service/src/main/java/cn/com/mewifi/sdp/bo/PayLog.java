package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class PayLog implements Serializable {
    private BigDecimal id;

    private BigDecimal orderlogid;

    private String clientname;

    private String account;

    private Date createtime;

    private String ispay;

    private String paycode;

    private String spname;

    private String type;

    private String status;

    private String resultdesc;

    private static final long serialVersionUID = 1L;



}