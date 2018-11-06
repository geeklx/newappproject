package com.haier.cellarette.libmvp.mvp;


/**
 * presenter基类 <br />
 */

public class Presenter<T extends IView> {
    /** 保存view*/
    private T mView;
    private long mCurrentMs = System.currentTimeMillis();

    /**
     * 在view创建的时候调用
     * @param view
     */
    public void onCreate(T view) {
        mView = view;
    }

    /**
     * 在view销毁的时候调用
     */
    public void onDestory() {
        /*Net.get().cancel(getIdentifier());*/
        mView = null;
    }

    /**
     * 获取view
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
