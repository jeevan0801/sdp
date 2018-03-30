package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.Catalog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/*****
 * 目录操作接口
 */
@Mapper
public interface CatalogInfoDao {
    /****
     * 通过目录code查询目录
     * @param queryParams code,type
     * @return
     */
    List<? extends Catalog> selectCatalogByCatalogCode(Map<String, String> queryParams);
    
    /***
     * 条件查询目录
     * @param queryParams 查询条件
     * @return 满足目录条件的集合
     */
    List<? extends Catalog> selectCatalogsByCondition(Map<String, Object> queryParams);
    
    /***
     * 查询type类的所有目录
     * @param type 类型
     * @return 所有目录的集合
     */
    List<? extends Catalog> selectAllCatalogs(String type);
    
    /****
     * 查询所有可用的目录
     * @param type 类型
     * @return 可用目录列表
     */
    List<? extends Catalog> selectAvailAbleCatalogs(String type);
    <T extends Catalog> T selectCatalogByCatalogIdAndType(Map<String,Object> queryParams);
    
}