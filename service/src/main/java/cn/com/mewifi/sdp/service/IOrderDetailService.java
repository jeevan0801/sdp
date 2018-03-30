package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.OrderDetail;

import java.util.List;
import java.util.Map;

public interface IOrderDetailService {
    List<? extends OrderDetail> selectAllOrderDetails(String code);
    List<? extends OrderDetail> selectOrdersByCondition(Map<String, Object> queryParams);
    int deleteOrderDetailByOrderDetailCode(String code);

    int insertOrderDetail(OrderDetail record);

    int insertOrderDetailSelective(OrderDetail record);

    <T  extends OrderDetail> T selectOrderDetailByOrderDetailCode(String type,String code);

    int updateOrderDetailByPrimaryOrderDetailCodeSelective(OrderDetail record);

    int updateOrderDetailByOrderDetailCode(OrderDetail record);
}