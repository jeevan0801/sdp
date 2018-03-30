package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.OrderLog;

import java.math.BigDecimal;

public interface IOrderLogService {
    int insertOrderLog(OrderLog record);
    int insertOrderLogSelective(OrderLog record);

    OrderLog selectOrderLogByOrderId(BigDecimal orderId);
}