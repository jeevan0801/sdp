package cn.com.mewifi.sdp.bo.memberbenefit;

import cn.com.mewifi.sdp.bo.SpInformation;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017-9-14.
 */
@Data
public class MemSpInformation extends SpInformation {
    /******上游公司id******/
    private BigDecimal id;
    
    /******上游公司logo图******/
    private String smallLogo;
    
    private String bigLogo;
}
