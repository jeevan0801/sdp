package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.db.SPInfo;
import cn.com.mewifi.sdp.dao.SPInfoDao;
import cn.com.mewifi.sdp.service.ISPInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * author: wangjc
 * date: 2017/9/7 19:23
 */
@Service
public class SPInfoServiceImpl implements ISPInfoService {

    @Autowired
    private SPInfoDao spInfoDao;

    @Override
    public SPInfo selectById(String spId) {
        return spInfoDao.selectById(spId);
    }

    @Override
    public List<SPInfo> selectAll() {
        return spInfoDao.selectAll();
    }
}
