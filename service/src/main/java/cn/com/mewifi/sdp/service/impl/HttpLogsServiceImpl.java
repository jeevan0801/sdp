package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.HttpLog;
import cn.com.mewifi.sdp.dao.HttpLogInfoDao;
import cn.com.mewifi.sdp.service.IHttpLogsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-9-22 上午 10:51
 */
@Data
@Service
@Slf4j
public class HttpLogsServiceImpl implements IHttpLogsService {
    @Autowired
    private HttpLogInfoDao httpLogInfoDao;
    
    @Override
    public int insertHttpLog(HttpLog record) {
        int recordId = httpLogInfoDao.insertHttpLog(record);
        if (recordId > 0) {
            return 1;
        }
       return 0;
    }
    
    @Override
    public int insertHttpLogSelective(HttpLog record) {
        int recordId = httpLogInfoDao.insertHttpLogSelective(record);
        if (recordId > 0) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public List<HttpLog> selectLogsByCondition(Map<String, Object> queryParams) {
        List<HttpLog> logs = httpLogInfoDao.selectLogsByCondition(queryParams);
        return (logs== null || logs.size()==0) ? null : logs;
    }
}
