package cn.com.mewifi.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import cn.com.mewifi.sdp.handler.MemberHandler;
import cn.com.mewifi.sdp.service.*;
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
    // TODO: client暂时写死zzwx
    private String clientId = "zzwx";
    @Autowired
    private MemberHandler memberHandler;

    @Autowired
    // @Qualifier("memberServiceImplTengRuiMing")
    @Qualifier("memberServiceImplXinYeZhiYou")
    private IMemberService memberService;
    
    @Autowired
    // private SPConfigProperties spConfigProperties;
    private ISPInfoService spinfoService;
    
    @Autowired
    // @Qualifier("smsServiceImplDuanXinWang")
    // @Qualifier("smsServiceImplWO")
    private IAuthCodeService authCodeService;
    @Autowired
    /****订单日志***/
    private IOrderLogService orderLogService;
    @Autowired
    /***订单详情操作类**/
    private IOrderDetailService orderDetailService;
    @Autowired
    /***上游产品与zzwx产品对应表**/
    private ISpProductService spProductService;
    @Autowired
    /***支付日志**/
    private IPayLogService payLogService;


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
    @PostMapping(value = "/order")
    public ResultVO order(@RequestParam("orderId") String orderId, @RequestParam("account") String account,
        @RequestParam("paymentId") String paymentId, @RequestParam("goodsId") String goodsId) {
        // List<SPInfo> spInfoList = spConfigProperties.getSpInfoList();


        memberHandler.flowOrderMember(orderId,account,paymentId,goodsId);
       /* List<SPInfo> spInfoList = spinfoService.selectAllSpInfos();
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
        return rs;*/
        return null;
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
        return authCodeService.sendAuthCode(mobileNo, clientId);
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
        @ApiImplicitParam(name = "authCode", value = "待校验的验证码", required = true, dataType = "String", paramType = "query")})
    @PostMapping(value = "/smsAuthCodeCheck")
    public ResultVO smsAuthCodeCheck(@RequestParam("mobileNo") String mobileNo,
        @RequestParam("authCode") String authCode) {
        return authCodeService.verifyAuthCode(mobileNo, authCode, clientId);
    }
}
