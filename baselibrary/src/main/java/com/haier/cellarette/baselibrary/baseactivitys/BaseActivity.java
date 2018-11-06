/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.haier.cellarette.baselibrary.baseactivitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.common.BaseViewHelper;
import com.haier.cellarette.baselibrary.smartbar.IBaseAction;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtil;
import com.haier.cellarette.baselibrary.usersdk.LoginUtil;
import com.haier.cellarette.baselibrary.zothers.butterknife.AnnotateUtils;


public abstract class BaseActivity extends AppCompatActivity implements IBaseAction {

    public static final String REQUEST_CODE = "request_code";

    private long mCurrentMs = System.currentTimeMillis();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        interceptCreateView();
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BaseAppManager.getInstance().add(this);
//        AnnotateUtils.injectViews(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.black));
        setContentView(getLayoutId());
        setup(savedInstanceState);

    }

    private void interceptCreateView() {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);
                if (view != null && view instanceof EditText) {
                    Log.d("***", "IME_FLAG_NO_EXTRACT_UI");
                    EditText et = (EditText) view;
                    et.setImeOptions(et.getImeOptions() | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    return et;
                }
                return view;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) { HookUtil.hookClick(this);}
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            MobEventHelper.onEvent(this, "effective_click");
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobEvent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobEvent.onPause(this);
    }

    protected abstract int getLayoutId();

    protected void setup(@Nullable Bundle savedInstanceState) {

    }

    protected final <T extends View> T f(@IdRes int resId) {
        return BaseViewHelper.f(this, resId);
    }

    public void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity));
    }

    public void startActivity(String action) {
        startActivity(new Intent(action));
    }

    public void startActivityForResult(Class<? extends Activity> activity, int requestCode) {
        startActivityForResult(new Intent(this, activity), requestCode);
    }

    public void startActivityForResult(String action, int requestCode) {
        startActivityForResult(new Intent(action), requestCode);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (intent.resolveActivity(getPackageManager()) == null) {
            Log.e("Activity", "No Activity found to handle intent " + intent);
            return;
        }

        if (requestCode != -1 && intent.getIntExtra(REQUEST_CODE, -1) == -1) {
            intent.putExtra(REQUEST_CODE, requestCode);
        }

        super.startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param activity
     */
    public void targetToIfLogin(final Class<? extends BaseActivity> activity) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivity(activity);
//            }
//        });
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param intent
     */
    public void targetToIfLogin(final Intent intent) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivity(intent);
//            }
//        });
    }

    public void targetToIfLogin(final String action) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivity(action);
//            }
//        });
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param intent
     */
    public void targetToForResultIfLogin(final Intent intent, final int requestCode) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(intent, requestCode);
//            }
//        });
    }

    public void targetToForResultIfLogin(final String action, final int requestCode) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(action, requestCode);
//            }
//        });
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (UserUtils.get().onActivityResult(requestCode, resultCode, data)) {
//            if (UserUtils.get().isUserLogin()) {
//                onUserLogined(UserUtils.get().userId());
//            }
//            return;
//        }
        if (LoginUtil.get().login_activity_result(requestCode, resultCode, data)) {
//            if (LoginUtil.get().isUserLogin()) {
//                onUserLogined(LoginUtil.get().userId());
//            } else {
//                onUserLoginCanceled();
//            }
            return;
        }
        onActResult(requestCode, resultCode, data);
    }

    protected void onUserLogined(String userId) {
    }

    protected void onActResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void finish() {
        hideSoftKeyboard();
//        ShowLoadingUtil.onDestory();
        super.finish();
        BaseAppManager.getInstance().remove(this);
//        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }

    @Override
    protected void onDestroy() {
        BaseAppManager.getInstance().remove(this);
//        ShowLoadingUtil.onDestory();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onHomePressed() {
//        finish();
//        while (!BaseAppManager.getInstance().getAll().isEmpty()) {
//            BaseAppManager.getInstance().top().finish();
//        }

//        Stack<Activity> all = BaseAppManager.getInstance().getAll();
//        for (Iterator<Activity> iterator = all.iterator(); iterator.hasNext();) {
//            iterator.next().finish();
//        }

        BaseAppManager.getInstance().clear();
        Intent intent = new Intent("hs.act.phone.index");
        startActivity(intent);
        finish();

//        Application app = BaseApp.get();
//        if (app instanceof AndroidApplication) {
//            ((AndroidApplication) app).onHomePressed();
//        }
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public Activity who() {
        return this;
    }

    public String getIdentifier() {
        return getClass().getName() + mCurrentMs;
    }
}
