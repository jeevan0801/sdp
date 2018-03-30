package cn.com.mewifi.sdp.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/****
 * 目录父类
 */
@Data
public class Catalog implements Serializable {
    /******目录id******/
    protected BigDecimal id;
    
    /******目录名称******/
    protected String catalogname;
    
    /******目录详情*****/
    protected String catalogdesc;
    
    /******目录code******/
    protected String code;
    
    /******父级目录code******/
    protected String supercode;
    
    /******目录状态1可用,0不可用******/
    protected String status;
    
    /******目录创建时间******/
    protected String createtime;
    
    /******排序方式******/
    protected String sortid;
    
    /******类型*****/
    protected String type;
    
    /******目录logo图******/
    protected String logo;
    
    private static final long serialVersionUID = 1L;
    
}