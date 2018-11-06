package com.haier.jiuzhidao.biz_mz.bean;

import java.io.Serializable;

public class GetCodeBean implements Serializable {
    private int code;
    private MyResult datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MyResult getDatas() {
        return datas;
    }

    public void setDatas(MyResult datas) {
        this.datas = datas;
    }
}
