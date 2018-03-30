package cn.com.mewifi.sdp.bo.memberbenefit;

import cn.com.mewifi.sdp.bo.BaseProduct;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MemBaseProduct extends BaseProduct implements Serializable {
    /***id 和baseProductId值保持一致**/
    private BigDecimal memId;

    /***产品背景图**/
    private String picUrl;

    /***产品单价**/
    private BigDecimal unitPrice;

    /***排序条件**/
    private String sortId;

    /***首页是否展示***/

    private String isIndex;
    
}