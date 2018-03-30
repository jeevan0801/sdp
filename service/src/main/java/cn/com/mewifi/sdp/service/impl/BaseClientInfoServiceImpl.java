package cn.com.mewifi.sdp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mewifi.sdp.bo.ClientInformation;
import cn.com.mewifi.sdp.dao.ClientInfoDao;
import cn.com.mewifi.sdp.service.IClientInfoService;
import lombok.Data;

/**
 * Created by Administrator on 2017-9-14.
 */
@Data
@Service
public class BaseClientInfoServiceImpl implements IClientInfoService {
    @Autowired
    private ClientInfoDao clientInfoDao;
    
    @Override
    public List<? extends ClientInformation> getClientInfoList(String type) {
        List<? extends ClientInformation> list = clientInfoDao.selectAllClients(type);
        
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends ClientInformation> getClientInfoList() {
        return getClientInfoList(null);
    }
    
    @Override
    public List<? extends ClientInformation> getClientInfoListByCondition(Map<String, Object> queryParams) {
        /*
         * if(null!=queryParams){ if(null== queryParams.get("type")){ queryParams.put("type",type); } } else {
         * queryParams = new HashMap<>(); queryParams.put("type",type); }
         */
        List<? extends ClientInformation> list = clientInfoDao.selectClientsByCondition(queryParams);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends ClientInformation> getByClientInfoCode(String type, String code) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("code", code);
        queryMap.put("type", type);
        List<? extends ClientInformation> list = clientInfoDao.selectClientByClientInfoCode(queryMap);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends ClientInformation> getByClientInfoCode(String code) {
        return getByClientInfoCode(null, code);
    }
    
    @Override
    public List<? extends ClientInformation> getAvailClientInfo(String type) {
        List<? extends ClientInformation> list = clientInfoDao.selectAvailAbleClients(type);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends ClientInformation> getAvailClientInfo() {
        return getAvailClientInfo(null);
    }
}
