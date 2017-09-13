package cn.com.mewifi.sdp.service;

import org.springframework.stereotype.Service;

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
}
