package cn.com.mewifi.sdp.util;

import cn.com.mewifi.sdp.vo.ResultVO;

/**
 * description: 返回对象工具类, 方便添加返回内容
 * author: wangjc
 * date: 2017/9/5 14:38
 */
public class ResultVOUtil {
    /**
     * 成功
     * @param object data对象
     * @return
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        return resultVO;
    }

    /**
     * 成功 data为空
     * @return
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 失败
     * @param code 结果标识
     * @param msg 结果描述
     * @return
     */
    public static ResultVO error(String code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
