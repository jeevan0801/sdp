import cn.com.mewifi.core.util.BeanUtil;
import cn.com.mewifi.sdp.bo.BaseProduct;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-09-25 19:30
 */
public class Test {
    public static void main(String[] args) {
        Map<String,Object> beanMap = new HashMap<>();
        beanMap.put("productName", "00");
        beanMap.put("spName", "01");
        beanMap.put("price", new BigDecimal(10));
        beanMap.put("cost", new BigDecimal(9));
        beanMap.put("type", "mem");
        beanMap.put("catalogId", new BigDecimal(5));
        beanMap.put("productType", "000");
        BaseProduct baseProduct = new BaseProduct();
        try {
            baseProduct = (BaseProduct)BeanUtil.mapToBean(beanMap,BaseProduct.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(baseProduct);

    }
}
