package cn.com.mewifi.sdp.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* SmsServiceImplDuanXinWang Tester. 
* 
* @author <Authors name> 
* @since <pre>九月 5, 2017</pre> 
* @version 1.0 
*/
public class SmsServiceImplDuanXinWangTest {
    SmsServiceImplDuanXinWang service = new SmsServiceImplDuanXinWang();
    
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
    * Method: sendAuthCode(String mobileNo) 
    * 
    */
    @Test
    public void testSendAuthCode()
        throws Exception {
        service.sendAuthCode("18070288774");
    }
    
}
