package cn.com.mewifi.sdp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import cn.com.mewifi.core.util.JSONUtil;
import cn.com.mewifi.sdp.service.IPayService;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * description: 支付接口
 * author: wangjc
 * date: 2017/9/14 16:09
 */
@RestController
@Slf4j
@RequestMapping(value = "/pay")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class PayController {
    
    @Autowired
    // @Qualifier("payServiceImplWeiXin")
    @Qualifier("payServiceImplAli")
    private IPayService payService;
    
    /**
     * 支付宝支付成功后页面通知 Get返回
     * @return
     */
    @RequestMapping(value = "/callBack/Ali/returnPage")
    public ResultVO returnPage(@RequestParam Map<String, Object> allRequestParams) {
        ResultVO rs = payService.returnPage(allRequestParams);
        Map<String, Object> dataMap = JSONUtil.jsonString2Map((String)rs.getData());
        Map<String, Object> rsDataMap = new HashMap<>();
        rsDataMap.put("trade_status", dataMap.get("trade_status"));
        rsDataMap.put("trade_no", dataMap.get("trade_no"));
        // 重写返回的内容, 避免信息泄露
        rs = ResultVOUtil.success(rsDataMap);
        return rs;
    }
    
    /**
     * 支付宝支付成功后服务器通知 POST返回
     * @param allRequestParams
     * @return
     */
    @RequestMapping(value = "/callBack/Ali/notify")
    public ResultVO notify(@RequestParam Map<String, Object> allRequestParams, HttpServletResponse response)
        throws IOException {
        ResultVO rs = payService.notify(allRequestParams);
        Map<String, Object> dataMap = JSONUtil.jsonString2Map((String)rs.getData());
        Map<String, Object> rsDataMap = new HashMap<>();
        rsDataMap.put("trade_status", dataMap.get("trade_status"));
        rsDataMap.put("trade_no", dataMap.get("trade_no"));
        
        // 重写返回的内容, 避免信息泄露
        rs = ResultVOUtil.success(rsDataMap);
        // 给服务端返回success,否则会一直收到通知
        response.getOutputStream().println("success");
        return rs;
    }
    
    /**
     * 支付接口
     * @param orderId     支付流水号
     * @param productCode 商品编码
     * @return
     */
    @ApiOperation(value = "支付接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "支付流水号", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "productCode", value = "商品编码", required = true, dataType = "String", paramType = "query")})
    @PostMapping(value = "/pay")
    public ResultVO getPayUrl(@RequestParam("orderId") String orderId, @RequestParam("productCode") String productCode) {
        String url = payService.getPayUrl(orderId,productCode);
        ResultVO rs = ResultVOUtil.success(url);
        return rs;
    }
}
