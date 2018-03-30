package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.SpInformation;
import cn.com.mewifi.sdp.dao.ISpInfoDao;
import cn.com.mewifi.sdp.service.ISPInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * author: wangjc
 * date: 2017/9/7 19:23
 */
@Service
public class SPInfoServiceImpl implements ISPInfoService {
    
    @Autowired
    private ISpInfoDao spInfoDao;

    @Override
    public <T extends SpInformation> T selectSpInfoById(BigDecimal spId) {
        return selectSpInfoByIdAndType(spId, null);
    }

    @Override
    public <T extends SpInformation> T selectSpInfoBySpCode(String spCode) {
        return selectSpInfoBySpCodeAndType(spCode,null);
    }

    @Override
    public <T extends SpInformation> T selectSpInfoByIdAndType(BigDecimal spId, String type) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("id",spId);
        queryParams.put("type",type);
        return spInfoDao.selectSpInfoById(queryParams);
    }

    @Override
    public <T extends SpInformation> T selectSpInfoBySpCodeAndType(String spCode, String type) {
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("code",spCode);
        queryParams.put("type",type);
        return spInfoDao.selectSpInfoBySpInfoCode(queryParams);
    }

    @Override
         public List<? extends SpInformation> selectAllSpInfos() {
        return selectAllSpInfosByType(null);
    }
    @Override
    public List<? extends SpInformation> selectAllSpInfosByType(String type) {
        return spInfoDao.selectAllSpInfos(type);
    }
}
