package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.Order;

import java.util.List;
import java.util.Map;

public interface IOrderInfoService {
    List<? extends Order> selectAllOrders(String type);

    List<? extends Order>  selectOrdersByCondition(Map<String, Object> queryParams);
    <T extends Order> T selectOrderByOrderNumberAndType(String type,String code);
    <T extends Order> T selectOrderByOrderNumber(String code);

    int insertOrderSelective(Order record);
    int insertOrder(Order record);



    int updateOrderByOrderNumberSelective(Order record);

    int updateOderByOrderNumber(Order record);
}