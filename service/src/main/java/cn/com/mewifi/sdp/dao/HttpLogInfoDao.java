package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.HttpLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface HttpLogInfoDao {


    int insertHttpLog(HttpLog record);

    int insertHttpLogSelective(HttpLog record);
    List<HttpLog> selectLogsByCondition(Map<String,Object> queryParams);



}