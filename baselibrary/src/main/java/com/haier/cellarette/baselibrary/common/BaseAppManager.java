package com.haier.cellarette.baselibrary.common;

import android.app.Activity;

import java.util.Stack;

public class BaseAppManager {

    private Stack<Activity> mActivities = new Stack<>();
    private static BaseAppManager sInstance;

    public static BaseAppManager getInstance() {
        if (sInstance == null) {
            sInstance = new BaseAppManager();
        }
        return sInstance;
    }

    private BaseAppManager() {
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void add(Activity activity) {
        mActivities.add(activity);
    }

    /**
     * 移除当前activity
     */
    public void pop() {
        if (mActivities.isEmpty()) return;

        Activity a = mActivities.lastElement();
        remove(a);
    }

    /**
     * 移除activity
     *
     * @param activity
     */
    public void remove(Activity activity) {
        if (activity != null) {
            mActivities.remove(activity);
        }
    }

    /**
     * 移除指定activity
     *
     * @param activity
     */
    public void finish(Class<?> activity) {
        if (mActivities.isEmpty()) return;
        for (Activity a : mActivities) {
            if (a.getClass().equals(activity)) {
                a.finish();
                remove(a);
                return;
            }
        }
    }

    /**
     * 获取指定activity
     *
     * @param klass
     * @return
     */
    public Activity get(Class<?> klass) {
        for (Activity a : mActivities) {
            if (a.getClass().equals(klass)) return a;
        }

        return null;
    }

    /**
     * 获取所有activity
     */
    public Stack<Activity> getAll() {
        return mActivities;
    }

    /**
     * 获取栈顶的activity
     *
     * @return
     */
    public Activity top() {
        if (mActivities.isEmpty()) {
            return null;
        }
        return mActivities.peek();
    }

    public void clear() {
        mActivities.clear();
    }

    public  void finishActivities() {
        for (int i = 0; i < mActivities.size(); i++) {
            if (null != mActivities.get(i)) {
                mActivities.get(i).finish();
                i--;
            }
        }
        mActivities.clear();
    }

    /**
     * 关闭app
     */
    public synchronized  void closeApp() {
        finishActivities();
        // android.os.Process.killProcess(android.os.Process.myPid());
        // System.exit(0);
    }
}
