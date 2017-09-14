package cn.com.mewifi.sdp.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.com.mewifi.core.util.HttpUtil;
import cn.com.mewifi.core.util.MD5Util;
import cn.com.mewifi.core.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mewifi.sdp.dao.SerialNoGenMapper;
import cn.com.mewifi.sdp.service.IPubService;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 * author: wangjc
 * date: 2017/9/12 15:11
 */
@Service
@Slf4j
public class PubServiceImpl implements IPubService {
    
    @Autowired
    private SerialNoGenMapper serialNoGenMapper;

    @Override
    public String getSerialNo(String modelName, int length, String preFlag) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("i_modelName", modelName);
        params.put("i_length", length);
        params.put("i_preflag", preFlag);
        
        serialNoGenMapper.genSerialNo(params);
        log.info("serialNoGenMapper.params={}", params);
        String serialNo = (String)params.get("o_serialNo");
        
        return serialNo;
    }

}
