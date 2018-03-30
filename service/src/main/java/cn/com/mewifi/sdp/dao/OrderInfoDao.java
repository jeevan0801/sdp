package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderInfoDao {
    List<? extends Order> selectAllOrders(String type);

    List<? extends Order>  selectOrdersByCondition(Map<String,Object> queryParams);
    <T extends Order> T selectOrderByOrderNumber(Map<String,Object> queryParams);

    int insertOrderSelective(Order record);
    int insertOrder(Order record);



    int updateOrderByOrderNumberSelective(Order record);

    int updateOderByOrderNumber(Order record);
}