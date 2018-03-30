package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.OrderNoRecord;
import cn.com.mewifi.sdp.dao.OrderNoRecordDao;
import cn.com.mewifi.sdp.service.IOrderRecordService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-09-30 17:03
 */
@Service
@Data
public class OrderRecordServiceImpl implements IOrderRecordService {
    @Autowired
    private OrderNoRecordDao orderNoRecordDao;

    @Override
    public int insertOrderNo(OrderNoRecord orderNoRecord) {
        return orderNoRecordDao.insertOrderNo(orderNoRecord);
    }

    @Override
    public int insertOrderNoSelective(OrderNoRecord orderNoRecord) {
        return orderNoRecordDao.insertOrderNoSelective(orderNoRecord);
    }

    @Override
    public List<OrderNoRecord> selectOrderRecordByCondition(Map<String, Object> queryParams) {
        return orderNoRecordDao.selectOrderRecordByCondition(queryParams);
    }
}
