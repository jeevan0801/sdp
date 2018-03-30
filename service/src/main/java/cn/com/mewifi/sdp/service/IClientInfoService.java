package cn.com.mewifi.sdp.service;

import java.util.List;
import java.util.Map;

import cn.com.mewifi.sdp.bo.ClientInformation;

/**
 * Created by Administrator on 2017-9-14.
 */
public interface IClientInfoService {
    /****
     * 查询type类所有下游客户
     * @param  type 类型
     * @return 所有type类下游客户列表
     */
    List<? extends ClientInformation> getClientInfoList(String type);
    
    /****
     * 查询所有下游客户
     * @return 所有下游客户列表
     */
    List<? extends ClientInformation> getClientInfoList();
    
    /****
     * 条件查询下游客户
     * @param queryParams 查询条件
     * @return 满足条件下游客户列表
     */
    
    List<? extends ClientInformation> getClientInfoListByCondition(Map<String, Object> queryParams);
    
    /****
     * 通过下游客户code和type查询下游客户
     * @param code 下游客户code
     * @param type 类型
     * @return 满足条件下游客户列表
     */
    List<? extends ClientInformation> getByClientInfoCode(String type, String code);
    
    /****
     * 通过下游客户code查询下游客户
     * @param code 下游客户code
     * @return 满足条件下游客户列表
     */
    List<? extends ClientInformation> getByClientInfoCode(String code);
    
    /****
     * 查询所有type类型可用的下游客户
     * @return 可用下游客户列表
     */
    
    List<? extends ClientInformation> getAvailClientInfo();
    
    /****
     * 查询所有type类型可用的下游客户
     * @param type 类型
     * @return 可用下游客户列表
     */
    
    List<? extends ClientInformation> getAvailClientInfo(String type);
    
}
