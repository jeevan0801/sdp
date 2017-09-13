package cn.com.mewifi.sdp.config;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cn.com.mewifi.sdp.constant.Result;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常时返回的json数据
 * description:
 * author: wangjc
 * date: 2017/9/13 15:12
 */
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // 默认的
    // {
    // "timestamp": 1505292355443,
    // "status": 500,
    // "error": "Internal Server Error",
    // "exception": "java.lang.IllegalArgumentException",
    // "message": "Illegal pattern component: i",
    // "path": "/member/pay"
    // }

    /**
     * 自定义异常时返回的json
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> jsonErrorHandler(HttpServletRequest request, Exception e) {
        
        String httpStatus = this.getStatus(request).toString();
        // 添加默认的异常数据
        Map<String, String> errorInfo = new HashMap<>();
        errorInfo.put("timestamp", DateFormatUtils.format(new Date(), "yyyyMMddhh24mmss"));
        errorInfo.put("exception", e.toString());
        errorInfo.put("status", httpStatus);
        errorInfo.put("path", request.getRequestURI());
        
        ResultVO rv = ResultVOUtil.error(Result.pub_exception, errorInfo);
        
        // 添加头信息
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        List<String> list = new ArrayList<>();
        list.add(httpStatus);
        list.add(e.toString());
        params.put("exception", list);
        
        return new ResponseEntity(rv, params, this.getStatus(request));
        
    }
    
    /**
     * 从request中获取httpStatus
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
