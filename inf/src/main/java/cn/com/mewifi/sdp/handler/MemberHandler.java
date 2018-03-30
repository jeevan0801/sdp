package cn.com.mewifi.sdp.handler;

import cn.com.mewifi.sdp.bo.*;
import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.constant.Result;
import cn.com.mewifi.sdp.service.*;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:会员权益handler
 * author: Administrator
 * date: 2017-09-29 19:09
 */
@Component
@Data
@Slf4j
public class MemberHandler {
    /****
     * 提交订单
     * @return resultVO
     */
    @Autowired
    /****订单日志***/
    private IOrderLogService orderLogService;
    /***订单****/
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    /***订单详情操作类**/
    private IOrderDetailService orderDetailService;
    
    @Autowired
    /***上游产品与zzwx产品对应表**/
    private ISpProductService spProductService;
    
    @Autowired
    /***zzwx的baseProduct**/
    private IBaseProductsService baseProductsService;
    
    @Autowired
    /****下游的product***/
    private IClientProductsService clientProductsService;
    

    @Autowired
    /***上游公司**/
    private ISPInfoService spInfoService;
    
    @Autowired
   private IPubService iPubService;
    @Autowired
    private IOrderRecordService orderRecordService;
    
    @Autowired
    // @Qualifier("memberServiceImplTengRuiMing")
    @Qualifier("memberServiceImplXinYeZhiYou")
    private IMemberService memberService;
    
    public ResultVO flowOrderMember(String orderId, String account, String paymentId, String goodsId) {
        //todo:1.存在,不生成新订单记录
        //todo:2.生成新订单记录,对zzwx的baseProduct表中的stock(库存)进行相减
        ResultVO rs = null;
        Map<String,Object> qryMap = new HashMap<>();
        qryMap.put("ordernumber",orderId);
        qryMap.put("clientno",paymentId);
        //qryMap.put("baseproductcode",goodsId);
        //qryMap.put("account",account);

        List<OrderNoRecord> orderNoRecords = orderRecordService.selectOrderRecordByCondition(qryMap);
        if(orderNoRecords!=null && orderNoRecords.size()>0){

            rs = ResultVOUtil.error(Result.pub_action_doing);
            return rs;
        }
        OrderNoRecord orderNoRecord = new OrderNoRecord();
        orderNoRecord.setCreatetime(new Date());
        orderNoRecord.setClientno(paymentId);
        orderNoRecord.setBaseproductcode(goodsId);
        orderNoRecord.setMobile(account);
        orderNoRecord.setOrdernumber(orderId);
        orderNoRecord.setType(PubConstant.SdpType.SDP_TYPE_MEMBER.getType());
        orderRecordService.insertOrderNo(orderNoRecord);

        // 1.通goodsId去spProduct表中得到上游公司产品信息(有效,价格最低者)
        List<? extends SpProduct> spProducts =
            spProductService.selectSpProductByBaseProductCodeAndLowCost(null, goodsId);
        // 2.spcode得到上游公司信息
        if (spProducts == null || spProducts.size() == 0) {
            return null;
        }
        SpProduct spProduct = spProducts.get(0);
        SpInformation spInformation =
            spInfoService.selectSpInfoBySpCodeAndType(spProduct.getSpcode(), spProduct.getType());
        // todo:3.下游信息(通过用户登录用户得到)
        // todo:4.得到zzwx给下游配的价格(baseproductCode 和 clientCode)
        
        // 订单记录ordername,ordernumber;Date createtime updatetime;status;resultdesc;totalprice;BigDecimal total;
        // paymethod;orderdesc;String type;
        Order order = new Order();
        // todo:order.setOrdername();
        order.setOrdernumber(orderId);
        order.setCreatetime(new Date());
        order.setUpdatetime(new Date());
        // todo:下游的价格order.setTotalprice();
        // 会员权益订购目前只有一次
        order.setTotal(new BigDecimal(1));
        order.setType(PubConstant.SdpType.SDP_TYPE_MEMBER.getType());
        
        // 订单详情
        // ordernumber;detailno;baseproductcode;orderdetailname;price;cost;status;resultdesc;createtime;
        // updatedate;clientid;clientname;spid;spname;account;code;type;
        OrderDetail detail = new OrderDetail();
        detail.setOrdernumber(orderId);
        detail.setDetailno(iPubService.getSerialNo(PubConstant.SerialNo.MEMBER_ORDERNO.getModelName(),
                PubConstant.SerialNo.MEMBER_ORDERNO.getLength(),
                PubConstant.PreFlag.HAVE.getIsHas()));
        detail.setBaseproductcode(spProduct.getBaseproductcode());
        //price,cost,orderdetailname,clientid,clientname,account
        detail.setCreatetime(new Date());
        detail.setUpdatedate(new Date());
        detail.setSpid(spInformation.getId());
        detail.setSpname(spInformation.getSpname());
        detail.setType(PubConstant.SdpType.SDP_TYPE_MEMBER.getType());

        // 5.封装参数进行请求
        String url = spInformation.getBaseurl();
        Map<String, Object> param = new HashMap<>();
        param.put("partner", spInformation.getAccountid());
        param.put("mobile", paymentId);
        param.put("order", orderId);
        param.put("account", account);
        param.put("goods", goodsId);
        //JSONObject rsJson = memberService.order(url, param);
        //todo:对rsJson的status进行判断,order及orderId封装status和resultDesc
        orderInfoService.insertOrder(order);
        orderDetailService.insertOrderDetail(detail);

        // 订单日志createtime;orderid;orderdetailid;clientname;account;spname;baseproductcode;price;paydesc;type;code;
        OrderLog orderLog = new OrderLog();
        orderLog.setCreatetime(new Date());
        orderLog.setOrderid(order.getId());
        orderLog.setOrderdetailid(detail.getId());
        orderLog.setSpname(spInformation.getSpname());
        orderLog.setBaseproductcode(spProduct.getBaseproductcode());
        orderLog.setType(PubConstant.SdpType.SDP_TYPE_MEMBER.getType());
        orderLog.setAccount(account);
        //todo:需补齐参数
        orderLogService.insertOrderLog(orderLog);

       // ResultVO rs = ResultVOUtil.success(rsJson);
        return null;
        
    }
}
