package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderDetailInfoDao {
    List<? extends OrderDetail> selectAllOrderDetails(String code);
    List<? extends OrderDetail> selectOrdersByCondition(Map<String,Object> queryParams);
    int deleteOrderDetailByOrderDetailCode(String code);

    int insertOrderDetail(OrderDetail record);

    int insertOrderDetailSelective(OrderDetail record);

    <T  extends OrderDetail> T selectOrderDetailByOrderDetailCode(Map<String,Object> queryParams);

    int updateOrderDetailByPrimaryOrderDetailCodeSelective(OrderDetail record);

    int updateOrderDetailByOrderDetailCode(OrderDetail record);
}