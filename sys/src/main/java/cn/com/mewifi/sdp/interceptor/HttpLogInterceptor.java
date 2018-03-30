package cn.com.mewifi.sdp.interceptor;

import cn.com.mewifi.core.util.LoggerUtils;
import cn.com.mewifi.sdp.bo.HttpLog;
import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.service.IHttpLogsService;
import cn.com.mewifi.sdp.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * description:
 * author: Administrator
 * date: 2017-9-22 上午 10:57
 */
@Component
public class HttpLogInterceptor implements HandlerInterceptor {
    private String HTTP_LOG = "httpLog";
    @Autowired
    private IHttpLogsService httpLogsService;
    
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
        throws Exception {
        // 1.新建log对象,写入参数
        // accountcode,clientip,url,method,requestparams,cretattime
        HttpLog log = new HttpLog();
        log.setAccountcode(httpServletRequest.getParameter("code"));
        log.setClientip(LoggerUtils.getRequestIp(httpServletRequest));
        log.setUrl(httpServletRequest.getRequestURI());
        log.setMethod(LoggerUtils.getRequestMethod(o));
        log.setRequestparams(JSONObject.toJSONString(httpServletRequest.getParameterMap(),
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteMapNullValue));
        log.setCretattime(new Date());
        // 2.将日志对象存入request作用域
        httpServletRequest.setAttribute(HTTP_LOG, log);
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
        // costtime,httpcode,type,result
        // int httpCode = httpServletResponse.getStatus();
        HttpLog log = (HttpLog)httpServletRequest.getAttribute(HTTP_LOG);
        double costTime = Double.valueOf((new Date().getTime() - log.getCretattime().getTime()) / 1000.0);
        log.setCosttime(costTime);
        log.setType(String.valueOf(httpServletRequest.getAttribute(PubConstant.TYPE)));
        ResultVO resultVO = (ResultVO)httpServletRequest.getAttribute(PubConstant.RESULTVO);
        if (null != resultVO) {

            log.setResult(String.valueOf(resultVO.getData()));
            log.setHttpcode(String.valueOf(resultVO.getCode()));
        }
        httpLogsService.insertHttpLog(log);

    }
}
