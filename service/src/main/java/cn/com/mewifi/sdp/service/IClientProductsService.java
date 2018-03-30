package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.ClientProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-15.
 */
public interface IClientProductsService {
    /****
     * 查询type类所有下游客户产品
     * @return 所有type类下游客户产品列表
     */
    List<? extends ClientProduct> selectAllClientProducts();
    
    /****
     * 查询type类所有下游客户产品
     * @param  type 类型
     * @return 所有type类下游客户产品列表
     */
    List<? extends ClientProduct> selectAllClientProductsByType(String type);
    
    /****
     * 查询所有type类型可用的下游客户产品
     * @return 可用下游客户产品列表
     */
    List<? extends ClientProduct> selectAvailableClientProducts();
    
    /****
     * 查询所有type类型可用的下游客户产品
     * @param  type 类型
     * @return 可用下游客户产品列表
     */
    List<? extends ClientProduct> selectAvailableClientProductsByType(String type);
    
    /****
     * 条件查询下游客户产品
     * @param queryParams 查询条件
     * @return 满足条件下游客户产品列表
     */
    List<? extends ClientProduct> selectProductByCondition(Map<String, Object> queryParams);
    
    /****
     * 通过下游客户产品code和type查询下游客户产品
     * @param code 下游客户产品code
     * @return 满足条件下游客户产品列表
     */
    List<? extends ClientProduct> selectClientProudctByProductCode(String code);
    
    /****
     * 通过下游客户产品code和type查询下游客户产品
     * @param code 下游客户产品code
     * @param type 类型
     * @return 满足条件下游客户产品列表
     */
    List<? extends ClientProduct> selectClientProductByProductCodeAndType(String type, String code);
    
    /****
     * 通过下游客户产品id和type查询下游客户产品
     * @param type 类型
     * @param id 客户产品id
     * @return 满足条件下游客户产品列表
     */
    
    <T extends ClientProduct> T selectClientProductByProductIdAndType(String type, BigDecimal id);
    
    /****
     * 通过下游客户产品id和type查询下游客户产品
     * @param id 客户产品id
     * @return 满足条件下游客户产品列表
     */
    
    <T extends ClientProduct> T selectClientProductByProductId(BigDecimal id);
    <T extends ClientProduct> T selectClientProductByBaseProductCodeAndClientCode(String baseProductCode,String clientCode);
}
