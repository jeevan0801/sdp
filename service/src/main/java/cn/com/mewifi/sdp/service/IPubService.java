package cn.com.mewifi.sdp.service;

/**
 * description:
 * author: wangjc
 * date: 2017/9/12 15:11
 */
public interface IPubService {

    /**
     * 获取序列号
     * @param modelName 模块名
     * @param length    序列号长度
     * @param preFlag   是否需要将模块名作为前缀
     * @return
     */
    String getSerialNo(String modelName, int length, String preFlag);
}
