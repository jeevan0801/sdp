package cn.com.mewifi.sdp.service;

import java.util.List;

import cn.com.mewifi.sdp.bo.db.SPInfo;

/**
 * sdp上游信息
 * description:
 * author: wangjc
 * date: 2017/9/7 19:22
 */
public interface ISPInfoService {
    /**
     * 根据spid查询spinfo
     * @param spId
     * @return
     */
    SPInfo selectById(String spId);

    /**
     * 查询所有spinfo
     * @return
     */
    List<SPInfo> selectAll();
}
