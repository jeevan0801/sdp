package cn.com.mewifi.sdp.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * description:
 * author: wangjc
 * date: 2017/9/12 13:53
 */
@Mapper
public interface SerialNoGenMapper {

    /**
     * 调用存储过程生成序列号
     * @param params 存储过程入参/出参. 最终生成的序列号从map中获取
     */
    void genSerialNo(Map<String, Object> params);
}
