package cn.com.mewifi.sdp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.bo.SPInfo;
import cn.com.mewifi.sdp.config.SPConfigProperties;
import cn.com.mewifi.sdp.service.IMemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * description: 会员权益接口
 * author: wangjc
 * date: 2017/8/31 15:16
 */
@RestController
@Slf4j
@RequestMapping(value = "/member")
public class MemberController {
    
    @Autowired
    @Qualifier("memberServiceTengRuiMing")
    private IMemberService iMemberService;
    
    @Autowired
    private SPConfigProperties spConfigProperties;
    
    /**
     * @return
     */
    @ApiOperation(value = "订单提交", notes = "用于提交会员权益订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderId", value = "订单流水号,保证唯一", required = true, dataType = "String"),
        @ApiImplicitParam(name = "account", value = "需要充值的会员ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "paymentId", value = "付费ID,一般指手机号码", required = true, dataType = "String"),
        @ApiImplicitParam(name = "goodsId", value = "产品id", required = true, dataType = "String"),})
    @PostMapping(value = "/order")
    public JSONObject order(@RequestParam("orderId") String orderId, @RequestParam("account") String account,
        @RequestParam("paymentId") String paymentId, @RequestParam("goodsId") String goodsId) {
        
        List<SPInfo> spInfoList = spConfigProperties.getSpInfoList();
        for (SPInfo sp : spInfoList) {
            log.info(sp.toString());
        }
        
        SPInfo sp = spInfoList.get(0);
        String url = sp.getBaseUrl();
        Map<String, Object> param = new HashMap<>();
        param.put("partner", sp.getAccountId());
        param.put("mobile", paymentId);
        param.put("order", orderId);
        param.put("account", account);
        param.put("goods", goodsId);
        param.put("channel", "bjxyzy");
        
        JSONObject rs = iMemberService.order(url, param);
        return rs;
    }
    
}
