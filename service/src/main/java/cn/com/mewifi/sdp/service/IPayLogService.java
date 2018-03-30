package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.PayLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPayLogService {

    int insertPayLog(PayLog record);

    int insertPayLogSelective(PayLog record);


    int updatePayLogByPayLogCodeSelective(PayLog record);

    int updatePayLogByPayLogCode(PayLog record);
}