package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/***
 * 请求日志
 */
@Data
public class HttpLog implements Serializable {
    /****日志id***/
    private BigDecimal id;
    /****用户code****/
    private String accountcode;
    /***类型****/
    private String type;
    /***请求ip***/
    private String clientip;
    /***请求url***/
    private String url;
    /***请求方法 ***/
    private String method;
    /***请求参数****/
    private String requestparams;
    /***创建时间**/
    private Date cretattime;
    /***花费时间***/
    private double costtime;
    /***返回码***/
    private String httpcode;
    /****返回结果***/
    private String result;
    
    private static final long serialVersionUID = 1L;
    

}