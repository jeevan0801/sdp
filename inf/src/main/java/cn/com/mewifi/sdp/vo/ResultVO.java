package cn.com.mewifi.sdp.vo;

import lombok.Data;

/**
 * description: author: wangjc date: 2017/8/31 16:13
 * 
 * @param <T> 数据对象
 */

@Data
public class ResultVO<T> {
    
    private String code;
    
    private String msg;
    
    private T data;
}
