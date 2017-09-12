package cn.com.mewifi.sdp.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.core.util.MathUtil;
import cn.com.mewifi.sdp.bo.db.AuthCodeLog;
import cn.com.mewifi.sdp.config.SMSConfig;
import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.constant.Result;
import cn.com.mewifi.sdp.dao.AuthCodeLogMapper;
import cn.com.mewifi.sdp.service.AbstractSmsService;
import cn.com.mewifi.sdp.service.IAuthCodeService;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 * author: wangjc
 * date: 2017/9/11 11:55
 */
@Service
@Slf4j
public class AuthCodeServiceImpl implements IAuthCodeService {
    
    @Autowired
    @Qualifier("smsServiceImplDuanXinWang")
    private AbstractSmsService smsService;
    
    @Autowired
    private SMSConfig smsConfig;
    
    @Autowired
    private AuthCodeLogMapper authCodeLogMapper;
    
    @Override
    public ResultVO sendAuthCode(String receiver, String clientId) {
        // 短信网后台注册的短信模板内容
        String smsContentStart = "您申请的手机验证码为";
        String smsContentEnd = ",请输入后进行验证,谢谢!";
        
        ResultVO rs = this.isPass(receiver);
        if (rs.getCode() != Result.pub_success.getCode()) {
            return rs;
        }
        
        String authCode = MathUtil.getRandomInt(Integer.valueOf(smsConfig.getLengthOfAuthCode()));
        String smsContent = smsContentStart + authCode + smsContentEnd;
        JSONObject jsonObject = smsService.sendByContent(receiver, smsContent);
        
        AuthCodeLog authCodeLog = new AuthCodeLog();
        authCodeLog.setReceiver(receiver);
        authCodeLog.setAuthtype(PubConstant.MessageType.SMS.getCode());
        authCodeLog.setAuthcode(authCode);
        authCodeLog.setIschecked(PubConstant.Bool.NO.getCode());
        authCodeLog.setSender(clientId);
        authCodeLog.setMessageid(BigDecimal.valueOf(smsService.getLogId()));// 短信发送日志主键
        authCodeLogMapper.insert(authCodeLog);

        rs.setData(jsonObject);
        return rs;
    }
    
    @Override
    public ResultVO verifyAuthCode(String receiver, String authCode, String clientId) {
        // 最近的一条发送记录
        AuthCodeLog authCodeLogLast = authCodeLogMapper.getLastInfo(receiver, clientId);
        
        ResultVO rs = ResultVOUtil.error(Result.pub_authCode_noPass);
        if(authCodeLogLast != null) {
            if (!StringUtils.isEmpty(authCode) && !StringUtils.isEmpty(authCodeLogLast.getAuthcode())) {
                if (authCode.equals(authCodeLogLast.getAuthcode())) {
                    // 回写状态
                    int update = authCodeLogMapper.updateStatusById(authCodeLogLast.getId());
                    if (update>0) {
                        rs = ResultVOUtil.success();
                    }

                }
            }
        }
        return rs;
    }
    
    /**
     * 判断短语发送频率
     * @return
     */
    public ResultVO isPass(String receiver) {
        int maxIntervalOf = Integer.valueOf(smsConfig.getMaxInterval());
        int maxCountPerDay = Integer.valueOf(smsConfig.getMaxCountPerDay());
        int countOfLastDay = authCodeLogMapper.countOfLastDay(receiver);
        int countOfLastInterval = authCodeLogMapper.countOfLastInterval(receiver, maxIntervalOf);
        
        ResultVO rs = ResultVOUtil.success();
        
        // 超过限制
        if (countOfLastDay >= maxCountPerDay || countOfLastInterval >= 1) {
            rs = ResultVOUtil.error(Result.pub_authCode_outOfLimit);
            return rs;
        }
        
        return rs;
    }
    
}
