package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.BaseProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-12.
 */
public interface IBaseProductsService {
    /****
     * 查询type类所有产品
     * @param  type 类型
     * @return 所有type类产品列表
     */
    List<? extends BaseProduct> getProductsByType(String type);
    /****
     * 查询type类的数量
     * @param  type 类型
     * @return 所有type类产品列表
     */
    int getProductsSizeByType(String type);

    /****
     * 查询所有产品
     * @return 所有产品列表
     */
    List<? extends BaseProduct> getAllProducts();
    int getProductsSize();
    


    /****
     * 条件查询产品
     * @param queryParams 查询条件
     * @return 满足条件产品列表
     */
    List<? extends BaseProduct> getProductsByCondition(Map<String, Object> queryParams);
    int getProductsSizeByCondition(Map<String, Object> queryParams);
    /****
     * 通过产品code查询产品
     * @param code 产品code
     * @return 满足条件产品
     */
    <T extends BaseProduct> T getProductByProductCode(String code);

    /****
     * 通过产品code查询产品
     * @param codes 产品code
     * @return 满足条件产品列表
     */
    List<? extends BaseProduct>  getProductByProductCodes(List<String> codes);
    
    /****
     * 通过产品code和type查询产品
     * @param code 产品code
     * @param type 类型
     * @return 满足条件产品列表
     */
    <T extends BaseProduct> T getProductByProductCodeAndType(String type, String code);
    
    /****
     * 查询所有type类型可用的产品
     * @param  type 类型
     * @return 可用产品列表
     */
    List<? extends BaseProduct> getAvailableProductsByType(String type);
    int getAvailableProductsSizeByType(String type);
    /****
     * 查询所有可用的产品
     * @return 可用产品列表
     */
    List<? extends BaseProduct> getAvailableProducts();
    int getAvailableProductsSize();

    /***
     * 查询type类型首页产品
     * @param type 类型
     * @return type类型首页产品
     */
    List<Map<String, Object>> getIndexProductsByType(String type);
    int getIndexProductsSizeByType(String type);
    /***
     * 查询type类型首页产品
     * @param type 类型
     * @return type类型首页产品
     */
    List<Map<String, Object>> getIndexProductsByTypeAndCatalog(String type,BigDecimal catalogid);
   int getIndexProductsSizeByTypeAndCatalog(String type,BigDecimal catalogid);

    /***
     * 查询首页产品
     * @return 首页产品
     */
    List<Map<String, Object>> getIndexProducts();
   int getIndexProductsSize();

    /**
     * 新增一个baseProduct
     * @param baseProduct
     * @return
     */
    int insertBaseProduct(BaseProduct baseProduct);

    /**
     * 新增一个baseProduct
     * @param baseProduct
     * @return
     */
    int insertBaseProductSelective(BaseProduct baseProduct);

    /**
     * 批量新增产品
     * @param baseProducts
     * @return
     */
    int batchInsertBaseProducts(List<BaseProduct> baseProducts);

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

    /*****
     * 通过关键字去获得products,当分类id不为空时,进行联合查询
     * @param keyWord 关键字
     * @param catalogId 分类id
     * @return
     */
    List<? extends BaseProduct> selectProductsByKeyWordAndCatalogId(String keyWord,BigDecimal catalogId);
    int selectProductsSizeByKeyWordAndCatalogId(String keyWord,BigDecimal catalogId);

    // public int insert(Map<String,Object> inParams);
}
