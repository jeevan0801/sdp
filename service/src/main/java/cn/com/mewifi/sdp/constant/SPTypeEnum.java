package cn.com.mewifi.sdp.constant;

/**
 * 上游公司类型
 * description:
 * author: wangjc
 * date: 2017/9/11 13:47
 */
public enum SPTypeEnum {
    FLOWDATA("F"), SMS("S"), BILL("B"), MEMBER("M");
    
    private String spType;
    
    private SPTypeEnum(String spType) {
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