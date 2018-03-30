package cn.com.mewifi.core.util;

import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * description:
 * author: Administrator
 * date: 2017-9-22 上午 11:19
 */
public class LoggerUtils {
    /*****
     * 通过请求获得ip
     * @param request http请求
     * @return
     */
    public static String getRequestIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    /****
     * 获得请求的方法
     * @param handler interceptor的object
     * @return 方法名
     */
    public static String getRequestMethod(Object handler){
        if(handler == null){
            return null;
        }
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Method method = handlerMethod.getMethod();
            if(method == null){
                return null;
            }
            return method.getName();
        }
       return null;
    }
}
