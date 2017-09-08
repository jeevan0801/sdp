package cn.com.mewifi.sdp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.mewifi.sdp.bo.db.SPInfo;

/**
 * description:
 * author: wangjc
 * date: 2017/9/7 18:36
 */
@Mapper
public interface SPInfoDao {

    /**
     * 按spId查询spinfo
     * @param spId
     * @return
     */
    SPInfo selectById(@Param("spId") String spId);

    /**
     * 查询所有spinfo
     * @return
     */
    List<SPInfo> selectAll();
}
