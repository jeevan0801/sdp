package cn.com.mewifi.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

/**
 * description: http方法请求的工具类
 *
 * author: wangjc
 *
 * date: 2017/8/14 08:29
 */
@Slf4j
public class HttpUtil {
    
    private static OkHttpClient CLIENT;
    
    /**
     * 只有一个实例
     * 
     * @return OkHttpClient
     */
    private static OkHttpClient getInstance() {
        if (CLIENT == null) {
            CLIENT = new OkHttpClient();
        }
        return CLIENT;
    }
    
    /**
     * get() 返回字符串
     * 
     * @param url get请求的url
     * @param params
     * @return String
     */
    public static String getForString(String url, Map<String, Object> params) {
        
        String rs = "";
        
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        
        StringBuffer tempParams = new StringBuffer();
        
        try {
            int pos = 0;
            for (String key : params.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                
                tempParams
                    .append(String.format("%s=%s", key, URLEncoder.encode((params.get(key)).toString(), "utf-8")));
                pos++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        url = url + "?" + tempParams.toString();
        Request request = new Request.Builder().url(url).build();
        Call call = HttpUtil.getInstance().newCall(request);
        Response response = null;
        try {
            response = call.execute();
            rs = response.body().string();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        log.info("get(): url = {}, rs = {}", url, rs);
        return rs;
    }
    
    /**
     * post() 表单提交 返回字符串
     * 
     * @param url
     * @param params
     * @return String
     */
    public static String postForString(String url, Map<String, String> params) {
        String rs = "";
        
        if (params == null) {
            return "";
        }
        
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        
        RequestBody formBody = builder.build();
        
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call call = HttpUtil.getInstance().newCall(request);
        Response response = null;
        try {
            response = call.execute();
            rs = response.body().string();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        log.info("post(): url = {}, params = {}, rs = {}", url, params, rs);
        return rs;
    }
    
    /**
     * 已经拼接好的url, 不需要再传参数map
     * 
     * @param url
     * @return
     */
    public static JSONObject getForJSON(String url) {
        
        String resultStr = getForString(url, null);
        return JSONObject.parseObject(resultStr);
    }
    
    /**
     * get() 返回json对象
     * 
     * @param url
     * @return JSONObject
     */
    public static JSONObject getForJSON(String url, Map<String, Object> params) {
        
        String resultStr = getForString(url, params);
        return JSONObject.parseObject(resultStr);
    }
    
    /**
     * post() 返回json对象
     * 
     * @param url
     * @param params
     * @return JSONObject
     */
    public static JSONObject postForJSON(String url, Map<String, String> params) {
        String resultString = postForString(url, params);
        return JSONObject.parseObject(resultString);
    }
    
    /**
     * post() json格式 返回字符串
     * 
     * @param url url
     * @param jsonString json字符串
     * @return String
     */
    public static String postJSONForString(String url, String jsonString) {
        final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        String rs = "";
        
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        
        RequestBody requestBody = RequestBody.create(jsonType, jsonString);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = HttpUtil.getInstance().newCall(request);
        Response response = null;
        try {
            response = call.execute();
            rs = response.body().string();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        log.info("postJSONForString(): url = {}, params = {}, rs = {}", url, jsonString, rs);
        return rs;
    }
    
    /**
     * post() json格式
     * 
     * @param url url
     * @param jsonString json格式字符串
     * @return JSONObject
     */
    public static JSONObject postJSONForJSON(String url, String jsonString) {
        String resultString = postJSONForString(url, jsonString);
        return JSONObject.parseObject(resultString);
    }
    
    /**
     * 获取post json过来的数据
     * 
     * @param request httpServletRequest对象
     * @return 字符串
     */
    public static String receivePost(HttpServletRequest request) {
        String res = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            res = URLDecoder.decode(sb.toString(), "utf-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return res;
    }
    
}
