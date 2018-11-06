package com.haier.cellarette.biz_demo1.bean;

import java.io.Serializable;

public class DemoNewModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String reason;
    private int error_code;
    private DemoNewResultModel result;

    public DemoNewModel() {
    }

    public DemoNewModel(String reason, int error_code, DemoNewResultModel result) {
        this.reason = reason;
        this.error_code = error_code;
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public DemoNewResultModel getResult() {
        return result;
    }

    public void setResult(DemoNewResultModel result) {
        this.result = result;
    }
}
