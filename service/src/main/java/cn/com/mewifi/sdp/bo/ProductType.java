package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * description:产品类型表
 * author: Administrator
 * date: 2017-09-26 11:46
 */
@Data
public class ProductType {
    /****主键id***/
    private BigDecimal id;
    /**描述**/
    private String desc;
    /***大小**/
    private BigDecimal typeSize;
    /***单位**/
    private String unit;
    /***创建时间**/
    private Date createTime;
    /***状态是否有效 1.有效**/
    private String status;

}
