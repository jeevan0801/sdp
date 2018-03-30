package cn.com.mewifi.sdp.service.memberbenefit.impl;

import cn.com.mewifi.sdp.bo.memberbenefit.MemSpInformation;
import cn.com.mewifi.sdp.dao.memberbenefit.MemSpInfoDao;
import cn.com.mewifi.sdp.service.memberbenefit.IMemSpInfoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * description:
 * author: Administrator
 * date: 2017-09-25 15:41
 */
@Data
@Slf4j
@Service
public class MemSpInfoServiceImpl implements IMemSpInfoService {
    @Autowired
    private MemSpInfoDao memSpInfoDao;
    @Override
    public int insertMemSpInfo(MemSpInformation memSpInformation) {
        int result = memSpInfoDao.insertMemSpInfo(memSpInformation);
        return result > 0 ? result : 0;
    }

    @Override
    public int insertMemSpInfoSelective(MemSpInformation memSpInformation) {
        int result = memSpInfoDao.insertMemSpInfoSelective(memSpInformation);
        return result > 0 ? result : 0;
    }

    @Override
    public int batchInsertMemSpInfos(List<MemSpInformation> memSpInformations) {
        int result = memSpInfoDao.batchInsertMemSpInfos(memSpInformations);
        return result > 0 ? result : 0;
    }

    @Override
    public int updateMemSpInfoById(MemSpInformation memSpInformation) {
        int result = memSpInfoDao.updateMemSpInfoById(memSpInformation);
        return result > 0 ? result : 0;
    }

    @Override
    public int updateMemSpInfoBySelective(MemSpInformation memSpInformation) {
        int result = memSpInfoDao.updateMemSpInfoBySelective(memSpInformation);
        return result > 0 ? result : 0;
    }

    @Override
    public int batchDeleteMemSpInfosBySpInfoIds(List<BigDecimal> ids) {
        int result = memSpInfoDao.batchDeleteMemSpInfosBySpInfoIds(ids);
        return result > 0 ? result : 0;
    }

    @Override
    public int deleteMemSpInfoBySpInfoId(BigDecimal id) {
        int result = memSpInfoDao.deleteMemSpInfoBySpInfoId(id);
        return result > 0 ? result : 0;
    }
}
