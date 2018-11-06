package com.haier.cellarette.jiuzhidao_demo.glinbufen.glinmvp;

import com.example.shining.libglin.net.Net;

/**
 * presenter基类 <br />
 */

public class GlinPresenter<T extends GlinIView> {
    /**
     * 保存view
     */
    private T mView;
    private long mCurrentMs = System.currentTimeMillis();

    /**
     * 在view创建的时候调用
     *
     * @param view
     */
    public void onCreate(T view) {
        mView = view;
    }

    /**
     * 在view销毁的时候调用
     */
    public void onDestory() {
        Net.cancel(getIdentifier());
        mView = null;
    }

    /**
     * 获取view
     *
     * @return
     */
    protected T getView() {
        if (hasView()) {
            return mView;
        }

        return null;
    }

    /**
     * 判断view时候为空
     *
     * @return
     */
    protected boolean hasView() {
        return mView != null;
    }

    public String getIdentifier() {
        if (!hasView() || getView().getIdentifier() == null) {
            return getClass().getName() + mCurrentMs;
        }
        return getView().getIdentifier();
    }
}
