package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.OrderNoRecord;

import java.util.List;
import java.util.Map;

public interface OrderNoRecordDao {

    int insertOrderNo(OrderNoRecord record);

    int insertOrderNoSelective(OrderNoRecord record);

    List<OrderNoRecord> selectOrderRecordByCondition(Map<String,Object> queryParams);

}