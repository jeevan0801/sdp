package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.Order;
import cn.com.mewifi.sdp.dao.OrderInfoDao;
import cn.com.mewifi.sdp.service.IOrderInfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-09-27 19:37
 */
@Service
@Data
public class OrderServiceImpl implements IOrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Override
    public List<? extends Order> selectAllOrders(String type) {
        return orderInfoDao.selectAllOrders(type);
    }

    @Override
    public List<? extends Order> selectOrdersByCondition(Map<String, Object> queryParams) {
        return orderInfoDao.selectOrdersByCondition(queryParams);
    }

    @Override
    public <T extends Order> T selectOrderByOrderNumberAndType(String type,String orderNumber) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("type",type);
        queryParams.put("ordernumber",orderNumber);
        return orderInfoDao.selectOrderByOrderNumber(queryParams);
    }
    public <T extends Order> T selectOrderByOrderNumber(String orderNumber){
        return selectOrderByOrderNumberAndType(null,orderNumber);
    }

    @Override
    public int insertOrderSelective(Order record) {
        return orderInfoDao.insertOrderSelective(record);
    }

    @Override
    public int insertOrder(Order record) {
        return orderInfoDao.insertOrder(record);
    }

    @Override
    public int updateOrderByOrderNumberSelective(Order record) {
        return orderInfoDao.updateOrderByOrderNumberSelective(record);
    }

    @Override
    public int updateOderByOrderNumber(Order record) {
        return orderInfoDao.updateOderByOrderNumber(record);
    }
}
