package cn.com.mewifi.sdp.dao.memberbenefit;

import cn.com.mewifi.sdp.bo.memberbenefit.MemSpInformation;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017-9-14.
 */
@Mapper
public interface MemSpInfoDao {
    /**
     * 新增产品
     * @param memSpInformation
     * @return
     */
    int insertMemSpInfo(MemSpInformation memSpInformation);

    /**
     * 新增产品
     * @param memSpInformation
     * @return
     */
    int insertMemSpInfoSelective(MemSpInformation memSpInformation);

    /**
     * 批量新增产品
     * @param list
     * @return
     */
    int batchInsertMemSpInfos(List<MemSpInformation> list);

    /***
     * 更新产品
     * @param memSpInformation
     */
    int updateMemSpInfoById(MemSpInformation memSpInformation);

    /***
     * 更新产品
     * @param memSpInformation
     */
    int updateMemSpInfoBySelective(MemSpInformation memSpInformation);

    /***
     * 批量删除
     * @param list
     * @return
     */
    int batchDeleteMemSpInfosBySpInfoIds(List<BigDecimal> list);
    /***
     * 删除
     * @param id
     * @return
     */
    int deleteMemSpInfoBySpInfoId(BigDecimal id);
}
