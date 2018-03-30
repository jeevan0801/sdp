package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.SpInformation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-14.
 */
public interface ISpService {
    
    /****
     * 查询type类所有上游公司
     * @return 所有type类上游公司列表
     */
    List<? extends SpInformation> getSpInfos();
    
    /****
     * 查询type类所有上游公司
     * @param  type 类型
     * @return 所有type类上游公司列表
     */
    List<? extends SpInformation> getSpInfosByType(String type);
    
    /****
     * 条件查询上游公司
     * @param queryParams 查询条件
     * @return 满足条件上游公司列表
     */
    List<? extends SpInformation> getSpInfosByCondition(Map<String, Object> queryParams);
    
    /****
     * 通过上游公司code和type查询上游公司
     * @param code 上游公司code
     * @return 满足条件上游公司列表
     */
    List<? extends SpInformation> getSpInfoBySpInfoCode(String code);
    
    /****
     * 通过上游公司code和type查询上游公司
     * @param code 上游公司code
     * @param type 类型
     * @return 满足条件上游公司列表
     */
    List<? extends SpInformation> getSpInfoBySpInfoCodeAndType(String type, String code);
    
    /****
     * 通过上游公司id和type查询上游公司
     * @param type 类型
     * @param id 上游公司id
     * @return 满足条件上游公司列表
     */
    
    <T extends SpInformation> T getSpInfoByIdAndType(String type, BigDecimal id);
    
    /****
     * 通过上游公司id和type查询上游公司
     * @param id 上游公司id
     * @return 满足条件上游公司列表
     */
    
    <T extends SpInformation> T getSpInfoById(BigDecimal id);
    
    /****
     * 查询所有type类型可用的上游公司
     * @return 可用上游公司列表
     */
    List<? extends SpInformation> getAvailSpInfos();
    
    /****
     * 查询所有type类型可用的上游公司
     * @param  type 类型
     * @return 可用上游公司列表
     */
    List<? extends SpInformation> getAvailSpInfosByType(String type);

    /**
     * 新增公司
     * @param spInfo
     * @return
     */
    int insertSpInfo(SpInformation spInfo);

    /**
     * 新增公司
     * @param spInfo
     * @return
     */
    int insertSpInfoSelective(SpInformation spInfo);

    /**
     * 批量新增公司
     * @param spInfos
     * @return
     */
    int batchInsertSpInfo(List<SpInformation> spInfos);

    /***
     * 更新公司信息
     * @param spInfo
     */
    int updateSpInfoBySpInfoCode(SpInformation spInfo);

    /***
     * 更新公司信息
     * @param spInfo
     */
    int updateSpInfoBySelective(SpInformation spInfo);

    /***
     * 批量删除公司
     * @param codes
     * @return
     */
    int batchDeleteSpInfoBySpInfoCode(List<String> codes);
    /***
     * 删除公司
     * @param code
     * @return
     */
    int deleteSpInfoBySpInfoCode(String code);
}
