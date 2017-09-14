package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * description:
 * author: wangjc
 * date: 2017/9/5 09:56
 */
@Service
public interface IPayService {
    /**
     * 返回需要跳转的供支付的h5页面url
     * @param productId 商品id
     * @param fee 支付金额
     * @return
     */
    String getPayUrl(String productId, String fee);

    /**
     * 支付完成后跳转到页面, 并携带参数
     * @param params
     */
    ResultVO returnPage(Map<String,Object> params);

    /**
     * 支付完成后, 后台通知
     * @param params
     */
    ResultVO notify(Map<String,Object> params);

}
