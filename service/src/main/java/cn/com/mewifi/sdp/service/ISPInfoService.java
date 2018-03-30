package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.SpInformation;

import java.math.BigDecimal;
import java.util.List;

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
    <T extends SpInformation> T selectSpInfoById(BigDecimal spId);
    /**
     * 根据spid和type查询spinfo
     * @param spId
     * @param type
     * @return
     */
    <T extends SpInformation> T selectSpInfoByIdAndType(BigDecimal spId,String type);
    /**
     * 根据spCode查询spinfo
     * @param spCode
     * @return
     */
    <T extends SpInformation> T selectSpInfoBySpCode(String spCode);
    /**
     * 根据spCode和type查询spinfo
     * @param spCode
     * @param type
     * @return
     */
    <T extends SpInformation> T selectSpInfoBySpCodeAndType(String spCode,String type);

    /**
     * 查询所有spinfo
     * @return
     */
    List<? extends SpInformation> selectAllSpInfos();
    /**
     * 查询所有spinfo
     * @return
     */
    List<? extends SpInformation> selectAllSpInfosByType(String type);
}
