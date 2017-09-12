package cn.com.mewifi.sdp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.sdp.bo.db.SPInfo;
import cn.com.mewifi.sdp.service.IAuthCodeService;
import cn.com.mewifi.sdp.service.IMemberService;
import cn.com.mewifi.sdp.service.IPayService;
import cn.com.mewifi.sdp.service.ISPInfoService;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import io.swagger.annotations.Api;
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
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
@Api(value = "会员权益接口")
public class MemberController {

    // 客户端id
    //TODO: client暂时写死zzwx
    private String clientId = "zzwx";
    
    @Autowired
    // @Qualifier("memberServiceImplTengRuiMing")
    @Qualifier("memberServiceImplXinYeZhiYou")
    private IMemberService memberService;
    
    @Autowired
    private IPayService payService;
    
    @Autowired
    // private SPConfigProperties spConfigProperties;
    private ISPInfoService spinfoService;
    
    @Autowired
    // @Qualifier("smsServiceImplDuanXinWang")
    // @Qualifier("smsServiceImplWO")
    private IAuthCodeService authCodeService;
    
    /**
     * 提交订单
     * @param orderId
     * @param account
     * @param paymentId
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "订单提交", notes = "用于提交会员权益订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单流水号,保证唯一", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "account", value = "需要充值的会员ID", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "paymentId", value = "付费ID,一般指手机号码", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "goodsId", value = "产品id", required = true, dataType = "String", paramType = "query")})
    @PostMapping(value = "/order/")
    public ResultVO order(@RequestParam("orderId") String orderId, @RequestParam("account") String account,
        @RequestParam("paymentId") String paymentId, @RequestParam("goodsId") String goodsId) {
        // List<SPInfo> spInfoList = spConfigProperties.getSpInfoList();
        List<SPInfo> spInfoList = spinfoService.selectAll();
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
        
        JSONObject rsJson = memberService.order(url, param);
        ResultVO rs = ResultVOUtil.success(rsJson);
        return rs;
    }
    
    /**
     * 支付接口
     * @param orderId
     * @param fee
     * @return
     */
    @ApiOperation(value = "支付接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "支付流水号", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "fee", value = "支付金额", required = true, dataType = "String", paramType = "query")})
    @PostMapping(value = "/pay")
    public ResultVO getPayUrl(@RequestParam("orderId") String orderId, @RequestParam("fee") String fee) {
        String url = payService.getPayUrl(orderId, fee);
        ResultVO rs = ResultVOUtil.success(url);
        return rs;
    }
    
    /**
     * 短信验证码
     * @param mobileNo
     * @return
     */
    @ApiOperation(value = "短信验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "mobileNo", value = "手机号码", required = true, dataType = "String", paramType = "query")})
    @PostMapping(value = "/smsAuth")
    public ResultVO smsAuth(@RequestParam("mobileNo") String mobileNo) {
        return authCodeService.sendAuthCode(mobileNo,clientId);
    }

    /**
     * 短信码校验
     * @param mobileNo 手机号码
     * @param authCode 待校验的验证码
     * @return
     */
    @ApiOperation(value = "短信码校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobileNo", value = "手机号码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "authCode", value = "待校验的验证码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/smsAuthCodeCheck")
    public ResultVO smsAuthCodeCheck(@RequestParam("mobileNo") String mobileNo, @RequestParam("authCode") String authCode) {
        return authCodeService.verifyAuthCode(mobileNo,authCode,clientId);
    }
    
}
