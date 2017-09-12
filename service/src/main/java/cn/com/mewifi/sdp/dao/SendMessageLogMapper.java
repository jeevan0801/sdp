package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.db.SendMessageLog;

/**
 * 消息发送日志.包括短信/邮件等
 */
public interface SendMessageLogMapper {
    /**
     * @param record
     * @return
     */
    Integer insert(SendMessageLog record);

}