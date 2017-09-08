package cn.com.mewifi.sdp.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/** 
* PayServiceImplAli Tester. 
* 
* @author <Authors name> 
* @since <pre>九月 5, 2017</pre> 
* @version 1.0 
*/
@Slf4j
@SpringBootTest(classes = PayServiceImplAliTest.class, properties = "config/pay.ali.properties")
@RunWith(SpringRunner.class)
public class PayServiceImplAliTest {
    
    PayServiceImplAli service = new PayServiceImplAli();
    
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
    * Method: getPayUrl(String orderId, String fee) 
    * 
    */
    @Test
    public void testGetPayUrl()
        throws Exception {
        String orderId = "20170905105908001";
        String fee = "0.01";
        
        String url = service.getPayUrl(orderId, fee);
        log.info(url);
    }
    
}
