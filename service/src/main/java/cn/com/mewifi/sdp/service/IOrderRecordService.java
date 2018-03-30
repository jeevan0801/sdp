package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.OrderNoRecord;

import java.util.List;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-09-30 17:00
 */
public interface IOrderRecordService {
    int insertOrderNo(OrderNoRecord orderNoRecord);
    int insertOrderNoSelective(OrderNoRecord orderNoRecord);
    List<OrderNoRecord> selectOrderRecordByCondition(Map<String,Object> queryParams);
}
