package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.SpInformation;
import cn.com.mewifi.sdp.dao.ISpInfoDao;
import cn.com.mewifi.sdp.service.ISpService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-14.
 */
@Data
@Service
@Slf4j
public class BaseSpInfoServiceImpl implements ISpService {
    @Autowired
    private ISpInfoDao spInfoDao;
    
    @Override
    public List<? extends SpInformation> getSpInfosByType(String type) {
        List<? extends SpInformation> list = spInfoDao.selectAllSpInfos(type);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends SpInformation> getSpInfos() {
        return getSpInfosByType(null);
    }
    
    @Override
    public List<? extends SpInformation> getSpInfosByCondition(Map<String, Object> queryParams) {
        /*
         * if(null!=queryParams){ if(null== queryParams.get("type")){ queryParams.put("type",type); } } else {
         * queryParams = new HashMap<>(); queryParams.put("type",type); }
         */
        List<? extends SpInformation> list = spInfoDao.selectSpInfosByCondition(queryParams);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends SpInformation> getSpInfoBySpInfoCodeAndType(String type, String code) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("code", code);
        queryMap.put("type", type);
        List<? extends SpInformation> list = spInfoDao.selectSpInfoBySpInfoCode(queryMap);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public <T extends SpInformation> T getSpInfoByIdAndType(String type, BigDecimal id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("spId", id);
        queryParams.put("type", type);
        return spInfoDao.selectSpInfoById(queryParams);
    }
    
    @Override
    public <T extends SpInformation> T getSpInfoById(BigDecimal id) {
        return getSpInfoByIdAndType(null, id);
    }
    
    @Override
    public List<? extends SpInformation> getSpInfoBySpInfoCode(String code) {
        return getSpInfoBySpInfoCodeAndType(null, code);
    }
    
    @Override
    public List<? extends SpInformation> getAvailSpInfosByType(String type) {
        List<? extends SpInformation> list = spInfoDao.selectAvailAbleSpInfos(type);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public int insertSpInfo(SpInformation spInfo) {
        int result = spInfoDao.insertSpInfo(spInfo);
        return result > 0 ? result : 0;
    }
    
    @Override
    public int insertSpInfoSelective(SpInformation spInfo) {
        int result = spInfoDao.insertSpInfoSelective(spInfo);
        return result > 0 ? result : 0;
    }
    
    @Override
    public int batchInsertSpInfo(List<SpInformation> spInfos) {
        int result = spInfoDao.batchInsertSpInfos(spInfos);
        return result > 0 ? result : 0;
    }
    
    @Override
    public int updateSpInfoBySpInfoCode(SpInformation spInfo) {
        int result = spInfoDao.updateSpInfoBySpInfoCode(spInfo);
        return result > 0 ? result : 0;
    }
    
    @Override
    public int updateSpInfoBySelective(SpInformation spInfo) {
        int result = spInfoDao.updateSpInfoBySelective(spInfo);
        return result > 0 ? result : 0;
    }
    
    @Override
    public int batchDeleteSpInfoBySpInfoCode(List<String> codes) {
        int result = spInfoDao.batchDeleteSpInfosBySpInfoCodes(codes);
        return result > 0 ? result : 0;
    }
    
    @Override
    public int deleteSpInfoBySpInfoCode(String code) {
        int result = spInfoDao.deleteSpInfoBySpInfoCode(code);
        return result > 0 ? result : 0;
    }
    
    @Override
    public List<? extends SpInformation> getAvailSpInfos() {
        return getAvailSpInfosByType(null);
    }
}
