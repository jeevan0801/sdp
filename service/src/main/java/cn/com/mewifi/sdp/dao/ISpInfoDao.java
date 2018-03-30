package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.SpInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * description:
 * author: wangjc
 * date: 2017/9/7 18:36
 * 上游公司接口
 */
@Mapper
public interface ISpInfoDao {
    /****
     * 通过上游公司code查询上游公司
     * @param queryParams code,type
     * @return
     */
    <T extends SpInformation>  T selectSpInfoBySpInfoCode(Map<String, String> queryParams);
    
    /***
     * 条件查询上游公司
     * @param queryParams 查询条件
     * @return 满足上游公司条件的集合
     */
    List<? extends SpInformation> selectSpInfosByCondition(Map<String, Object> queryParams);
    
    /***
     * 查询type类的所有上游公司
     * @param type 类型
     * @return 所有上游公司的集合
     */
    List<? extends SpInformation> selectAllSpInfos(String type);
    
    /****
     * 查询所有可用的上游公司
     * @param type 类型
     * @return 可用上游公司列表
     */
    List<? extends SpInformation> selectAvailAbleSpInfos(String type);
    
    /****
     * 根据id和type查询上游公司
     * @param queryParams id type
     * @return 可用上游公司
     */
    <T extends SpInformation> T selectSpInfoById(Map<String, Object> queryParams);
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
     * @param list
     * @return
     */
    int batchInsertSpInfos(List<SpInformation> list);

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
     * @param list
     * @return
     */
    int batchDeleteSpInfosBySpInfoCodes(List<String> list);
    /***
     * 删除公司
     * @param code
     * @return
     */
    int deleteSpInfoBySpInfoCode(String code);
}
