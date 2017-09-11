package cn.com.mewifi.core.util;

import cn.com.mewifi.core.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* MathUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>九月 6, 2017</pre> 
* @version 1.0 
*/
@Slf4j
public class MathUtilTest {

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
    * Method: getRandomString(int length) 
    * 
    */
    @Test
    public void testGetRandomString()
        throws Exception {

    }
    
    /** 
    * 
    * Method: getRandomInt(int length) 
    * 
    */
    @Test
    public void testGetRandomInt()
        throws Exception {
        log.info(MathUtil.getRandomInt(4));
    }
    
}
