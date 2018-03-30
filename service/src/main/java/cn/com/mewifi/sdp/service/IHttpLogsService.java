package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.HttpLog;

import java.util.List;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-9-22 上午 10:49
 */
public interface IHttpLogsService {
    int insertHttpLog(HttpLog record);

    int insertHttpLogSelective(HttpLog record);
    List<HttpLog> selectLogsByCondition(Map<String,Object> queryParams);
}
