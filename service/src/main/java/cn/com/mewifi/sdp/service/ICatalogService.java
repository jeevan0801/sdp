package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.Catalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-12.
 */
public interface ICatalogService {
    /****
     * 查询type类所有产品
     * @param  type 类型
     * @return 所有type类产品列表
     */
    List<? extends Catalog> getCatalogListByType(String type);
    
    /****
     * 查询所有产品
     * @return 所有产品列表
     */
    List<? extends Catalog> getCatalogList();
    
    /****
     * 条件查询产品
     * @param queryParams 查询条件
     * @return 满足条件产品列表
     */
    
    List<? extends Catalog> getCatalogsByCondition(Map<String, Object> queryParams);
    
    /****
     * 通过产品code和type查询产品
     * @param code 产品code
     * @param type 类型
     * @return 满足条件产品列表
     */
    List<? extends Catalog> getCatalogByCatalogCodeAndType(String type, String code);
    
    /****
     * 通过产品code查询产品
     * @param code 产品code
     * @return 满足条件产品列表
     */
    
    List<? extends Catalog> getCatalogByCatalogCode(String code);
    
    /****
     * 查询所有type类型可用的目录
     * @param  type 类型
     * @return 可用目录列表
     */
    List<? extends Catalog> getAvailCatalogsByType(String type);
    
    /****
     * 查询所有可用的产品
     * @return 可用产品列表
     */
    List<? extends Catalog> getAvailCatalogs();
    // public int insert(Map<String,Object> inParams);
    <T extends Catalog> T selectCatalogByCatalogIdAndType(BigDecimal id,String type);
}
