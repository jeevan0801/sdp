package cn.com.mewifi.sdp.handler;

import cn.com.mewifi.sdp.bo.Order;
import cn.com.mewifi.sdp.bo.OrderLog;
import cn.com.mewifi.sdp.bo.PayLog;
import cn.com.mewifi.sdp.service.IOrderInfoService;
import cn.com.mewifi.sdp.service.IOrderLogService;
import cn.com.mewifi.sdp.service.IPayLogService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description:
 * author: Administrator
 * date: 2017-09-30 10:31
 */
@Component
@Data
@Slf4j
public class PayLogHandler {
    @Autowired
    /***支付日志**/
    private IPayLogService payLogService;
    @Autowired
    /****订单日志***/
    private IOrderLogService orderLogService;
    /***订单****/
    @Autowired
    private IOrderInfoService orderInfoService;

    public void insertPayLog(String orderNumber,String isPay,String spName,String status){
        //1.通过ordernumber去获取order
        Order order = orderInfoService.selectOrderByOrderNumber(orderNumber);
        OrderLog orderLog = null;
        if(order!=null) {
            //2.orderid去获取orderlogid,
            orderLog = orderLogService.selectOrderLogByOrderId(order.getId());
        } else {
            //todo:返回还是什么都不做
        }
        PayLog payLog = new PayLog();
        if(orderLog!=null) {
            payLog.setOrderlogid(orderLog.getId());
            payLog.setClientname(orderLog.getClientname());
            payLog.setAccount(orderLog.getAccount());
        }

        payLog.setCreatetime(new Date());
        payLog.setIspay(isPay);
        payLog.setSpname(spName);
        payLog.setStatus(status);
        payLog.setAccount(orderLog.getAccount());

        payLogService.insertPayLog(payLog);

    }
}
