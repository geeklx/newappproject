package com.example.shining.libglin.glin;


import com.example.shining.libglin.glin.call.Call;

public class Context {
    private Call<?> call;
    private Result<?> result;
    private RawResult rawResult;

    public Context() {}

    public Context(Call<?> call) {
        this.call = call;
    }

    public Call<?> getCall() {
        return call;
    }

    public void setCall(Call<?> call) {
        this.call = call;
    }

    public Result<?> getResult() {
        return result;
    }

    public void setResult(Result<?> result) {
        this.result = result;
    }

    public RawResult getRawResult() {
        return rawResult;
    }

    public void setRawResult(RawResult rawResult) {
        this.rawResult = rawResult;
    }
}
