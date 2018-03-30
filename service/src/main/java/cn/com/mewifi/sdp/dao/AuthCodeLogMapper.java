package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.db.AuthCodeLog;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 验证码发送dao
 */
public interface AuthCodeLogMapper {
    /**
     * @param record
     * @return
     */
    int insert(AuthCodeLog record);
    
    /**
     * 过去24小时累计发送的量
     * @return
     */
    int countOfLastDay(String receiver);
    
    /**
     * 过去一个间隔时间内发送的量
     * @param receiver
     * @param interval
     * @return
     */
    int countOfLastInterval(@Param("receiver") String receiver, @Param("interval") int interval);
    
    /**
     * 最近一小时内最新发送记录
     * @param receiver
     * @return
     */
    AuthCodeLog getLastInfo(@Param("receiver") String receiver, @Param("clientId") String clientId);
    
    /**
     * 根据主键修改状态
     * @param id
     * @return
     */
    int updateStatusById(BigDecimal id);
    
}