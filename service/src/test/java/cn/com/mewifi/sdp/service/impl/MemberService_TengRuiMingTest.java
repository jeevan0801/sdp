package test.cn.com.mewifi.sdp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.com.mewifi.core.util.MD5Util;
import cn.com.mewifi.sdp.service.impl.MemberServiceTengRuiMing;

/**
 * MemberService_TengRuiMing Tester.
 * 
 * @author <Authors name>
 * @since
 * 
 *        <pre>
 * 八月 31, 2017
 *        </pre>
 * 
 * @version 1.0
 */
public class MemberService_TengRuiMingTest {
    
    MemberServiceTengRuiMing service = new MemberServiceTengRuiMing();
    
    private String mid = "91019";
    
    private String key = "3392cd912df17526da7e06fb7161a176";
    
    @Before
    public void before()
        throws Exception {
        
    }
    
    @After
    public void after()
        throws Exception {
    }
    
    /**
     * 
     * Method: order()
     * 
     */
    @Test
    public void testOrder()
        throws Exception {
        // TODO: Test goes here...
        
        String url = "http://101.200.72.49:8080/qq-coins/qqApi/zhichong";
        
        String action = "order";
        int num = 1;
        String qq = "461812899";
        String ip = "171.34.73.121";
        String type = "0";
        String orderid = "20170831135732001";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action", action);
        params.put("m", mid);
        params.put("orderid", orderid);
        params.put("num", num);
        params.put("qq", qq);
        params.put("ip", ip);
        params.put("type", type);
        params.put("s", MD5Util.sign(action + mid + orderid + num + qq + ip + type + key));
        
        service.order(url, params);
    }
    
    /**
     * 
     * Method: queryBalance()
     * 
     */
    @Test
    public void testQueryBalance()
        throws Exception {
        // TODO: Test goes here...
        service.queryBalance();
    }
    
    /**
     * 
     * Method: queryOrder()
     * 
     */
    @Test
    public void testQueryOrder()
        throws Exception {
        // TODO: Test goes here...
    }
    
}
