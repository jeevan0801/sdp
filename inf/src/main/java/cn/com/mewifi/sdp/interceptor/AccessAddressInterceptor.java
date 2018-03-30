package cn.com.mewifi.sdp.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-9-15.
 */
@Component
public class AccessAddressInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
        throws Exception {
        /*
         * //请求地址数组 String[] addresses = {"192.168.1.161:8090","localhost:8090"}; //1.得到请求地址 String ip =
         * httpServletRequest.getRemoteAddr(); int port = httpServletRequest.getServerPort(); //2.判断请求地址是否在数组中 boolean
         * isAccess = false; for(String str:addresses){ if(str.equals(ip+":"+port)){ isAccess = true; break; } }
         * 
         * //3.给允许的地址设置header
         */
        //Todo 从白名单获得地址,设置isAccess
        boolean isAccess = true;
        if (isAccess) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        }
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
        ModelAndView modelAndView)
        throws Exception {
        
    }
    
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Object o, Exception e)
        throws Exception {
        
    }
}
