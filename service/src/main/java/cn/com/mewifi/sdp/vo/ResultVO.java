package cn.com.mewifi.sdp.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 返回结果类;
 * code/msg表示调用接口成功或失败; data中的才是接口返回的内容
 * description: author: wangjc
 * date: 2017/8/31 16:13
 * @param <T> 数据对象
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    
    private String code;
    
    private String msg;
    
    private T data;
}
