package cn.com.mewifi.sdp.dao.memberbenefit;

import cn.com.mewifi.sdp.bo.memberbenefit.MemBaseProduct;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface MemBaseProductInfoDao {
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
     * @param list
     * @return
     */
    int batchInsertMemBaseProducts(List<MemBaseProduct> list);
    
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
     * @param list
     * @return
     */
    int batchDeleteMemBaseProductsByProductIds(List<BigDecimal> list);

}