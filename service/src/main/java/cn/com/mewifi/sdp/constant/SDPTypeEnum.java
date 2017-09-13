package cn.com.mewifi.sdp.constant;

/**
 * 上游公司类型
 * description:
 * author: wangjc
 * date: 2017/9/11 13:47
 */
public enum SDPTypeEnum {
    FLOWDATA("FDP"), SMS("SMS"), BILL("BILL"), MEMBER("MEM");
    
    private String spType;
    
    SDPTypeEnum(String spType) {
        this.spType = spType;
    }
    
    public String getSpType() {
        return spType;
    }
    
    public void setSpType(String spType) {
        this.spType = spType;
    }
    
    @Override
    public String toString() {
        return spType;
    }
}