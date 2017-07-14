package com.hyf.cemap.bean.bi;

public class BICountPay {
    //<4w', '4w-6w', '6w-8w', '8w-10w', '10-12w','12-15w','15-20w','20-30w','>30w
    // <4w
    private Integer less4;
    
    // 4w-6w
    private Integer more4less6;

    // 6w-8w
    private Integer more6less8;
    
    //'8w-10w'
    private Integer more8less10;

    //'10-12w'
    private Integer more10less12;

    //'12-15w'
    private Integer more12less15;

    //'15-20w'
    private Integer more15less20;

    //'20-30w'
    private Integer more20less30;

    //'>30w
    private Integer more30;

    public Integer getLess4() {
        return less4;
    }

    public void setLess4(Integer less4) {
        this.less4 = less4;
    }

    public Integer getMore4less6() {
        return more4less6;
    }

    public void setMore4less6(Integer more4less6) {
        this.more4less6 = more4less6;
    }

    public Integer getMore6less8() {
        return more6less8;
    }

    public void setMore6less8(Integer more6less8) {
        this.more6less8 = more6less8;
    }

    public Integer getMore8less10() {
        return more8less10;
    }

    public void setMore8less10(Integer more8less10) {
        this.more8less10 = more8less10;
    }

    public Integer getMore10less12() {
        return more10less12;
    }

    public void setMore10less12(Integer more10less12) {
        this.more10less12 = more10less12;
    }

    public Integer getMore12less15() {
        return more12less15;
    }

    public void setMore12less15(Integer more12less15) {
        this.more12less15 = more12less15;
    }

    public Integer getMore15less20() {
        return more15less20;
    }

    public void setMore15less20(Integer more15less20) {
        this.more15less20 = more15less20;
    }

    public Integer getMore20less30() {
        return more20less30;
    }

    public void setMore20less30(Integer more20less30) {
        this.more20less30 = more20less30;
    }

    public Integer getMore30() {
        return more30;
    }

    public void setMore30(Integer more30) {
        this.more30 = more30;
    }
    
    
    
}
