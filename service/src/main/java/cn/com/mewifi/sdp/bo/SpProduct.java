package cn.com.mewifi.sdp.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
/****
 *
 * 上游产品对应接口
 */

public class SpProduct implements Serializable {
    private BigDecimal id;
    /***上游产品code**/
    protected String code;
    /***zzwx产品code**/
    protected String baseproductcode;
    /***上游code**/
    protected String spcode;
    /***上游产品名**/
    protected String productname;
    /***创建时间**/
    protected Date createtime;
    /***状态**/
    protected String status;
    /***价格**/
    protected BigDecimal price;
    /***zzwx花费**/
    protected BigDecimal cost;
    /***该公司该产品的订单量**/
    protected BigDecimal orderamount;
    /***该公司该产品的成功订单量**/
    protected BigDecimal successamount;
    /***该公司该产品的失败订单量**/
    protected BigDecimal failamount;
    /***该公司该产品的成功率**/
    protected BigDecimal successrate;
    /***排序方式**/
    protected String sortid;
    /***类型**/
    protected String type;

    private static final long serialVersionUID = 1L;


}