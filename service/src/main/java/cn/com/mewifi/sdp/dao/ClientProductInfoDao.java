package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.ClientProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-14.
 * 下游产品操作接口
 */
@Mapper
public interface ClientProductInfoDao {
    /****
     * 通过下游产品code查询下游产品
     * @param queryParams code,type
     * @return
     */
    List<? extends ClientProduct> selectClientProductByClientProductCode(Map<String, String> queryParams);
    
    /***
     * 条件查询下游产品
     * @param queryParams 查询条件
     * @return 满足下游产品条件的集合
     */
    List<? extends ClientProduct> selectClientProductsByCondition(Map<String, Object> queryParams);
    
    /***
     * 查询type类的所有下游产品
     * @param type 类型
     * @return 所有下游产品的集合
     */
    List<? extends ClientProduct> selectAllClientProducts(String type);
    
    /****
     * 查询所有可用的下游产品
     * @param type 类型
     * @return 可用下游产品列表
     */
    List<? extends ClientProduct> selectAvailAbleClientProducts(String type);
    
    /****
     * 根据id和type查询下游产品
     * @param queryParams id type
     * @return 可用下游产品
     */
    <T extends ClientProduct> T selectClientProductById(Map<String, Object> queryParams);
    /****
     * 根据id和type查询下游产品
     * @param queryParams baseproductcode clientcode
     * @return 可用下游产品
     */
    <T extends ClientProduct> T selectClientProductByBaseProductCodeAndClientCode(Map<String, Object> queryParams);
}
