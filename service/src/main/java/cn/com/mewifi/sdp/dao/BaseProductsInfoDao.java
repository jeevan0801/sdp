package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.BaseProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-12.
 */
@Mapper
public interface BaseProductsInfoDao {
    /***
     * 查询type类的所有产品
     * @param type 类型
     * @return 所有产品的集合
     */
    List<? extends BaseProduct> selectAllProducts(String type);
    int selectAllProductsSize(String type);

    /***
     * 条件查询产品
     * @param queryParams 查询条件
     * @return 满足产品条件的集合
     */
    List<? extends BaseProduct> selectProductsByCondition(Map<String, Object> queryParams);
    int selectProductsSizeByCondition(Map<String, Object> queryParams);

    /****
     * 通过产品code查询产品
     * @param queryParams code,type
     * @return
     */
    <T extends BaseProduct> T selectProductByProductCode(Map<String, String> queryParams);
    
    /****
     * 通过产品code查询产品
     * @param  codes,type
     * @return
     */
    List<? extends BaseProduct> selectProductsByProductCodes(List<String> codes);

    /****
     * 查询所有可用的产品
     * @param type 类型
     * @return 可用产品列表
     */
    List<? extends BaseProduct> selectAvailableProducts(String type);
    int selectAvailableProductsSize(String type);

    /***
     * 查询type类首页的产品
     * @param  type 类型
     * @return 集合
     */
    List<Map<String, Object>> selectIndexProducts(String type);
   int selectIndexProductsSize(String type);

    List<Map<String, Object>> selectIndexProductsByCatalogId(Map<String, Object> queryMaps);
    int selectIndexProductsSizeByCatalogId(Map<String, Object> queryMaps);

    /**
     * 新增产品
     * @param baseProduct
     * @return
     */
    int insertBaseProduct(BaseProduct baseProduct);
    
    /**
     * 新增产品
     * @param baseProduct
     * @return
     */
    int insertBaseProductSelective(BaseProduct baseProduct);
    
    /**
     * 批量新增产品
     * @param list
     * @return
     */
    int batchInsertBaseProducts(List<BaseProduct> list);
    
    /***
     * 更新产品
     * @param baseProduct
     */
    int updateBaseProductByCode(BaseProduct baseProduct);
    
    /***
     * 更新产品
     * @param baseProduct
     */
    int updateBaseProductBySelective(BaseProduct baseProduct);

    /***
     * 批量删除
     * @param codes
     * @return
     */
    int batchDeleteBaseProductsByProductCodes(List<String> codes);
    /***
     * 删除
     * @param code
     * @return
     */
    int deleteBaseProductByProductCode(String code);
    List<? extends BaseProduct> selectProductsByKeyWordAndCatalogId(Map<String,Object> keyWord);
    int selectProductsSizeByKeyWordAndCatalogId(Map<String,Object> keyWord);

    
}
