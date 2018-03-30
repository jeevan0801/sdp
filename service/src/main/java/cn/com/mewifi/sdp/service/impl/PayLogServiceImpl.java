package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.PayLog;
import cn.com.mewifi.sdp.dao.PayLogDao;
import cn.com.mewifi.sdp.service.IPayLogService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * author: Administrator
 * date: 2017-09-27 19:46
 */
@Service
@Data
public class PayLogServiceImpl implements IPayLogService {
    @Autowired
    private PayLogDao payLogDao;
    @Override
    public int insertPayLog(PayLog record) {
        return payLogDao.insertPayLog(record);
    }

    @Override
    public int insertPayLogSelective(PayLog record) {
        return payLogDao.insertPayLogSelective(record);
    }

    @Override
    public int updatePayLogByPayLogCodeSelective(PayLog record) {
        return payLogDao.updatePayLogByPayLogCodeSelective(record);
    }

    @Override
    public int updatePayLogByPayLogCode(PayLog record) {
        return payLogDao.updatePayLogByPayLogCode(record);
    }
}
