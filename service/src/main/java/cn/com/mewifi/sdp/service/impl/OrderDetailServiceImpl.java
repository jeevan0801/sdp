package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.OrderDetail;
import cn.com.mewifi.sdp.dao.OrderDetailInfoDao;
import cn.com.mewifi.sdp.service.IOrderDetailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-09-27 19:19
 */
@Service
@Data
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailInfoDao orderDetailInfoDao;
    @Override
    public List<? extends OrderDetail> selectAllOrderDetails(String code) {
        return orderDetailInfoDao.selectAllOrderDetails(code);
    }

    @Override
    public List<? extends OrderDetail> selectOrdersByCondition(Map<String, Object> queryParams) {
        return orderDetailInfoDao.selectOrdersByCondition(queryParams);
    }

    @Override
    public int deleteOrderDetailByOrderDetailCode(String code) {
        return orderDetailInfoDao.deleteOrderDetailByOrderDetailCode(code);
    }

    @Override
    public int insertOrderDetail(OrderDetail record) {
        return orderDetailInfoDao.insertOrderDetail(record);
    }

    @Override
    public int insertOrderDetailSelective(OrderDetail record) {
        return orderDetailInfoDao.insertOrderDetailSelective(record);
    }

    @Override
    public <T extends OrderDetail> T selectOrderDetailByOrderDetailCode(String type,String code) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("type",type);
        queryParams.put("code",code);
        return orderDetailInfoDao.selectOrderDetailByOrderDetailCode(queryParams);
    }

    @Override
    public int updateOrderDetailByPrimaryOrderDetailCodeSelective(OrderDetail record) {
        return orderDetailInfoDao.updateOrderDetailByPrimaryOrderDetailCodeSelective(record);
    }

    @Override
    public int updateOrderDetailByOrderDetailCode(OrderDetail record) {
        return orderDetailInfoDao.updateOrderDetailByOrderDetailCode(record);
    }
}
