package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.PayLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayLogDao {

    int insertPayLog(PayLog record);

    int insertPayLogSelective(PayLog record);


    int updatePayLogByPayLogCodeSelective(PayLog record);

    int updatePayLogByPayLogCode(PayLog record);
}