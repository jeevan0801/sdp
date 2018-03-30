package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.OrderLog;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface OrderLogDao {
    int insertOrderLog(OrderLog record);
    
    int insertOrderLogSelective(OrderLog record);
    

    
    OrderLog selectOrderLogByOrderId(BigDecimal orderId);
}