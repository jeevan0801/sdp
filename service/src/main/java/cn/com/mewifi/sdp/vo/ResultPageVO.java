package cn.com.mewifi.sdp.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * description:
 * author: Administrator
 * date: 2017-09-29 13:02
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
//todo:待修改:改为继承?
public class ResultPageVO<T> {
    private String code;

    private String msg;
    private Integer totalPage;
    private Integer totalCount;
    private T data;

}
