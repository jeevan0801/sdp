package cn.com.mewifi.sdp.service.memberbenefit;

import cn.com.mewifi.sdp.bo.memberbenefit.MemBaseProduct;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017-9-14.
 */
public interface IMemBaseProductsService {
    /**
     * 新增一个memBaseProudct
     * @param memBaseProudct
     * @return
     */
    int insertMemBaseProduct(MemBaseProduct memBaseProudct);

    /**
     * 新增一个memBaseProudct
     * @param memBaseProudct
     * @return
     */
    int insertMemBaseProductSelective(MemBaseProduct memBaseProudct);

    /**
     * 批量新增产品
     * @param memBaseProducts
     * @return
     */
    int batchInsertMemBaseProducts(List<MemBaseProduct> memBaseProducts);

    /***
     * 更新产品
     * @param memBaseProudct
     */
    int updateMemBaseProductById(MemBaseProduct memBaseProudct);

    /***
     * 更新产品
     * @param memBaseProudct
     */
    int updateMemBaseProductBySelective(MemBaseProduct memBaseProudct);

    /***
     * 批量删除
     * @param memId
     * @return
     */
    int deleteMemBaseProductByProductId(BigDecimal memId);
    /***
     * 批量删除
     * @param memIds
     * @return
     */
    int batchDeleteMemBaseProductsByProductIds(List<BigDecimal> memIds);
    
}
