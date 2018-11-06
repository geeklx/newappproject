package com.haier.cellarette.baselibrary.loading;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.haier.cellarette.baselibrary.common.BaseAppManager;


public class ShowLoadingUtil {
    public static SimpleLoadingDialog simpleLoadingDialog;//loading样式2
    private static long AUTO_DISMISS_TIME = 5000;
    private static Handler disHandler = new Handler();
    private static Thread disThread = new Thread(new Runnable() {
        @Override
        public void run() {
            AUTO_DISMISS_TIME = 5000;
            dismiss();
        }
    });

    /**
     * @description: 设置时间
     */
    public static void showLoading(final Context context, final String progressDesc, long time, final OnkeyBackDestoryLoadingListener onKeyBack) {
        AUTO_DISMISS_TIME = time;
        if (context instanceof Activity) {
            ((Activity) context).getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    if (context.equals(BaseAppManager.getInstance().top())) {
                        if (!((Activity) context).isFinishing()) {
                            //show dialog
                            show(context, progressDesc, onKeyBack);
                        }
                    }
                }
            });
        } else {
            show(context, progressDesc, onKeyBack);
        }
    }


    /**
     * @description: 显示ProgressDialog
     */
    public static void showLoading(final Context context, final String progressDesc, final OnkeyBackDestoryLoadingListener onKeyBack) {
        if (context instanceof Activity) {
            ((Activity) context).getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    if (context.equals(BaseAppManager.getInstance().top())) {
                        if (!((Activity) context).isFinishing()) {
                            //show dialog
                            show(context, progressDesc, onKeyBack);
                        }

                    }
                }
            });
        } else {
            show(context, progressDesc, onKeyBack);
        }
    }

    private static void show(Context context, String progressDesc, OnkeyBackDestoryLoadingListener onKeyBack) {
        if (progressDesc != null) {
            if (simpleLoadingDialog == null) {
                if (null != onKeyBack) {
                    simpleLoadingDialog = new SimpleLoadingDialog(context, onKeyBack);
                } else {
                    simpleLoadingDialog = new SimpleLoadingDialog(context);
                }
                //TODO 控制loading下面的字
                simpleLoadingDialog.setLoadingText(progressDesc);
                simpleLoadingDialog.setCanceledOnTouchOutside(false);

                simpleLoadingDialog.show();
            } else {
                simpleLoadingDialog.show();
            }
            disHandler.postDelayed(disThread, AUTO_DISMISS_TIME);
        } else {
            disHandler.postDelayed(disThread, AUTO_DISMISS_TIME);
        }
    }

    /***
     * @return true取消成功 false 加载框不存在
     * @description: 取消加载框
     */
    public static boolean dismiss() {
        if (simpleLoadingDialog != null && simpleLoadingDialog.getDialog().isShowing()) {
            onDestory();
            simpleLoadingDialog = null;
            disHandler.removeCallbacks(disThread);
            return true;
        } else {
            disHandler.removeCallbacks(disThread);
            return false;
        }
    }


    public static void onDestory() {
        if (simpleLoadingDialog != null) {
            try {
                simpleLoadingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                simpleLoadingDialog = null;
            }
        }

/*
        if (shapeLoadingDialog != null) {
//            if(shapeLoadingDialog.getDialog().isShowing()){
            try {
                shapeLoadingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            }
            shapeLoadingDialog = null;
        }
*/
    }

}