package test.cn.com.mewifi.sdp.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.com.mewifi.sdp.Main;
import cn.com.mewifi.sdp.service.impl.PayServiceImplWeiXin;

/** 
* PayServiceImplWeiXin Tester. 
* 
* @author <Authors name> 
* @since <pre>九月 13, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class PayServiceImplWeiXinTest {
    
    PayServiceImplWeiXin service = new PayServiceImplWeiXin();
    
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
    * Method: getPayUrl(String productId, String fee) 
    * 
    */
    @Test
    public void testGetPayUrl()
        throws Exception {
        String productId = "P001";
        String fee = "100";
        service.getPayUrl(productId, fee);
    }
    
    /** 
    * 
    * Method: buildParamMap(String productId, String fee) 
    * 
    */
    @Test
    public void testBuildParamMap()
        throws Exception {
        
        /*
         * try { Method method = PayServiceImplWeiXin.getClass().getMethod("buildParamMap", String.class, String.class);
         * method.setAccessible(true); method.invoke(<Object>, <Parameters>); } catch(NoSuchMethodException e) { }
         * catch(IllegalAccessException e) { } catch(InvocationTargetException e) { }
         */
    }
    
}
