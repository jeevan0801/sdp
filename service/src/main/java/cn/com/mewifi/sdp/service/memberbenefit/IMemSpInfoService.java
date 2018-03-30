package cn.com.mewifi.sdp.service.memberbenefit;

import cn.com.mewifi.sdp.bo.memberbenefit.MemSpInformation;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017-9-14.
 */
public interface IMemSpInfoService {
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
     * @param memSpInformations
     * @return
     */
    int batchInsertMemSpInfos(List<MemSpInformation> memSpInformations);

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
     * @param ids
     * @return
     */
    int batchDeleteMemSpInfosBySpInfoIds(List<BigDecimal> ids);
    /***
     * 删除
     * @param id
     * @return
     */
    int deleteMemSpInfoBySpInfoId(BigDecimal id);
}
