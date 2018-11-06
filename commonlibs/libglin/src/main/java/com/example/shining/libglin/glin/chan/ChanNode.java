package com.example.shining.libglin.glin.chan;


import com.example.shining.libglin.glin.Context;

public abstract class ChanNode {
    private ChanNode mNext;
    private Context mContext;

    private boolean beforeCall;

    public final void beforeCall(boolean invoke) {
        beforeCall = invoke;
    }

    public final boolean isBeforeCall() {
        return beforeCall;
    }

    public final void exec(Context ctx) {
        mContext = ctx;
        run(ctx);
    }

    public final ChanNode nextChanNode() {
        return mNext;
    }

    public final void nextChanNode(ChanNode chanNode) {
        mNext = chanNode;
    }

    protected final void next() {
        if (mNext != null) {
            mNext.exec(mContext);
            return;
        }

        if (beforeCall) { mContext.getCall().exec();}
    }

    protected final void cancel() {
        mNext = null;
        if (beforeCall) { mContext.getCall().cancel();}
    }

    public abstract void run(Context ctx);
}
