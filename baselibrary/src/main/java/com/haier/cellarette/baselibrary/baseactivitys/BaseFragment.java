package com.haier.cellarette.baselibrary.baseactivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.BaseViewHelper;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtil;


public abstract class BaseFragment extends Fragment {

    private long mCurrentMs = System.currentTimeMillis();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        setup(rootView, savedInstanceState);

        return rootView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        HookUtil.hookClick(this);
//        AnnotateUtils.injectViews(getActivity());
        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(BaseApp.get(), R.color.black));
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobEvent.onEventStart(getActivity(), EventIdConst.PAGE_LOAD_DURATION, TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobEvent.onEventEnd(getActivity(), EventIdConst.PAGE_LOAD_DURATION, TAG);
    }


    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {

    }

    protected <T extends View> T f(View rootView, @IdRes int resId) {
        return BaseViewHelper.f(rootView, resId);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    public void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(getActivity(), activity));
    }

    public void startActivity(String action) {
        Intent intent = new Intent(action);
        if (intent.resolveActivity(BaseApp.get().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void startActivityForResult(Class<? extends Activity> activity, int requestCode) {
        startActivityForResult(new Intent(getActivity(), activity), requestCode);
    }

    public void startActivityForResult(String action, int requestCode) {
        Intent intent = new Intent(action);
        if (intent.resolveActivity(BaseApp.get().getPackageManager()) != null) {
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param activity
     */
    public void targetToIfLogin(final Class<? extends BaseActivity> activity) {
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
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
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
//            @Override
//            public void run() {
//                startActivity(intent);
//            }
//        });
    }

    public void targetToIfLogin(final String action) {
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
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
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(intent, requestCode);
//            }
//        });
    }

    public void targetToForResultIfLogin(final String action, final int requestCode) {
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(action, requestCode);
//            }
//        });
    }

    @Override
    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (UserUtils.get().onActivityResult(requestCode, resultCode, data)) {
//            onUserLogined(UserUtils.get().userId());
//            return;
//        }

        onActResult(requestCode, resultCode, data);
    }

    public void onUserLogined(String userId) {
    }

    public void onActResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onDestroy() {
//        ShowLoadingUtil.onDestory();
        super.onDestroy();
    }

    public String getIdentifier() {
        return getClass().getName() + mCurrentMs;
    }
}
