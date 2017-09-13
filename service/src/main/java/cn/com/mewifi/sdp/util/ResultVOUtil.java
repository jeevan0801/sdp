package cn.com.mewifi.sdp.util;

import cn.com.mewifi.sdp.constant.Result;
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
        resultVO.setCode(Result.pub_success.getCode());
        resultVO.setMsg(Result.pub_success.getMsg());
        return resultVO;
    }

    public static ResultVO success(Result rs) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        resultVO.setCode(rs.getCode());
        resultVO.setMsg(rs.getMsg());
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

    /** 失败
     * @param rs enum
     * @return
     */
    public static ResultVO error(Result rs) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(rs.getCode());
        resultVO.setMsg(rs.getMsg());
        return resultVO;
    }

    /** 失败
     * @param rs enum
     * @param data
     * @return
     */
    public static ResultVO error(Result rs,Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(rs.getCode());
        resultVO.setMsg(rs.getMsg());
        resultVO.setData(data);
        return resultVO;
    }
}
