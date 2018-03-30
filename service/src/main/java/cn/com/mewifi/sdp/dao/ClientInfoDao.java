package cn.com.mewifi.sdp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.com.mewifi.sdp.bo.ClientInformation;

/*****
 * 下游操作接口
 */
@Mapper
public interface ClientInfoDao {
    
    /****
     * 通过下游code查询下游
     * @param queryParams code,type
     * @return
     */
    List<? extends ClientInformation> selectClientByClientInfoCode(Map<String, String> queryParams);
    
    /***
     * 条件查询下游
     * @param queryParams 查询条件
     * @return 满足下游条件的集合
     */
    List<? extends ClientInformation> selectClientsByCondition(Map<String, Object> queryParams);
    
    /***
     * 查询type类的所有下游
     * @param type 类型
     * @return 所有下游的集合
     */
    List<? extends ClientInformation> selectAllClients(String type);
    
    /****
     * 查询所有可用的下游
     * @param type 类型
     * @return 可用下游列表
     */
    List<? extends ClientInformation> selectAvailAbleClients(String type);
    
}