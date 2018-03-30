package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.OrderLog;
import cn.com.mewifi.sdp.dao.OrderLogDao;
import cn.com.mewifi.sdp.service.IOrderLogService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * description:
 * author: Administrator
 * date: 2017-09-27 19:45
 */
@Service
@Data
public class OrderLogServiceImpl implements IOrderLogService {
    @Autowired
    private OrderLogDao orderLogDao;
    @Override
    public int insertOrderLog(OrderLog record) {
        return orderLogDao.insertOrderLog(record);
    }

    @Override
    public int insertOrderLogSelective(OrderLog record) {
        return orderLogDao.insertOrderLogSelective(record);
    }



    @Override
    public OrderLog selectOrderLogByOrderId(BigDecimal orderId) {
        return orderLogDao.selectOrderLogByOrderId(orderId);
    }
}
