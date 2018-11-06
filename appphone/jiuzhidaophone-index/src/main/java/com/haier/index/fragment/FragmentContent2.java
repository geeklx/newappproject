package com.haier.index.fragment;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haier.biz.identification.bean.WineDetial;
import com.haier.biz.identification.presenter.WineListInfoPresenter;
import com.haier.biz.identification.view.WineListInfoView;
import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.loading.OnkeyBackDestoryLoadingListener;
import com.haier.cellarette.baselibrary.loading.ShowLoadingUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.index.R;
import com.haier.index.zxing.StoragePath;
import com.haier.index.zxing.core.QRCodeView;
import com.haier.index.zxing.core.ZXingView;

import java.io.File;
import java.io.Serializable;

import static android.content.Context.VIBRATOR_SERVICE;

public class FragmentContent2 extends BaseFragment implements QRCodeView.Delegate, View.OnClickListener, WineListInfoView, Camera.PictureCallback, OnkeyBackDestoryLoadingListener {

    private QRCodeView mQRCodeView;
    private TextView scan_barcode;
    private TextView scan_wine;

    private String tablayoutId;
    private Context mContext;
    private WineListInfoPresenter wineListInfoPresenter = new WineListInfoPresenter();
    private Handler mHandler = new Handler();
    private ImageView zxing_iv;
    private int tag = 1;
    private Camera camera;
    private boolean mHidden;

    private ImageView iv1;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qr_scan;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);

        ShowLoadingUtil.onDestory();
        initData(rootView);
        wineListInfoPresenter.onCreate(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        mHidden = hidden;
        super.onHiddenChanged(hidden);
        if (hidden) {
            mQRCodeView.stopCamera();
            wineGone();
        } else {
            restartCamera();
            if (tag == 1) {
            } else if (tag == 2) {
                takePic();
            }
        }
    }

    private void restartCamera() {
        mQRCodeView.stopCamera();
        boolean isHava = mQRCodeView.startCamera();
        mQRCodeView.startSpot();
        mQRCodeView.showScanRect();

        if (!isHava) {
            Toasty.error(getActivity(), "摄像头不可开启。").show();
            return;
        }
    }

    /**
     * 第一次进来加载bufen
     *
     * @param rootView
     */
    public void initData(View rootView) {
        mQRCodeView = (ZXingView) rootView.findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        scan_barcode = rootView.findViewById(R.id.scan_barcode);
        scan_wine = rootView.findViewById(R.id.scan_wine);
        zxing_iv = rootView.findViewById(R.id.zxing_iv);

        scan_wine.setOnClickListener(this);
        scan_barcode.setOnClickListener(this);
    }


    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            return;
        }
        Toasty.normal(getActivity(), "下拉刷新啦").show();
    }

    //震动
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toasty.success(getActivity(), result).show();
//        vibrate();
        mQRCodeView.startSpot();
        scan_wine.setEnabled(true);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        scan_wine.setEnabled(true);
    }

    @Override
    public void onDestroyView() {
        wineListInfoPresenter.onDestory();
        mQRCodeView.onDestroy();
        wineGone();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.scan_barcode) {
            restartCamera();
            BarCode();
            tag = 1;
            wineGone();
            scan_wine.setEnabled(true);
        } else if (i == R.id.scan_wine) {
            restartCamera();
            QrCode();
            tag = 2;
            takePic();
            scan_wine.setEnabled(false);
        }
    }

    //----------------------------扫描识酒
    @Override
    public void getListSuccess(WineDetial wineDetial) {
        Intent intent = new Intent();
        if ("0".equals(wineDetial.getStatus())) {//返回酒品详情，跳转到酒品详情
            if (wineDetial.getWine_list().size() < 1) {
                intent.setAction("hs.act.phone.WineDetailActivity");
                intent.putExtra("list", (Serializable) wineDetial.getWine_list());
            } else {
                intent.setAction("hs.act.phone.WineListActivity");
                intent.putExtra("list", (Serializable) wineDetial.getWine_list());
            }
            startActivity(intent);
        } else {
            Toasty.error(getActivity(), "加载失败，请重试。").show();
            ReStartTakePic();
        }

        dismiss();
    }

    @Override
    public void getListFailure(String s) {
        Toasty.error(getActivity(), "加载失败，请重试。").show();
        ReStartTakePic();
        dismiss();
    }

    //----------------------------拍照
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        if (StoragePath.createStoragePath()) {
            File file = StoragePath.savePicture(data);//转化为file文件
            zxing_iv.setVisibility(View.VISIBLE);
            zxing_iv.setImageURI(Uri.fromFile(file));
            //发送网络请求
            wineListInfoPresenter.getWineListInfo(file);
            ShowLoadingUtil.showLoading(getActivity(), "", 30000,this);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            a();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        restartCamera();
        if (tag == 1) {
        } else if (tag == 2 && !mHidden) {
            takePic();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mQRCodeView.stopCamera();
        wineGone();
    }

    private void a() {
        camera.takePicture(null, null, this);
    }

    int time = 7000;

    private void takePic() {
        camera = mQRCodeView.startSpotWine();
        mHandler.postDelayed(runnable, time);
        Toasty.info(getActivity(), time / 1000 + "秒后将确认扫描。", Toast.LENGTH_LONG).show();
    }

    private void wineGone() {
        if (mHandler != null)
            mHandler.removeCallbacks(runnable);
        zxing_iv.setVisibility(View.GONE);
    }

    private void dismiss() {
        StoragePath.deletePictures();
        zxing_iv.setVisibility(View.GONE);
        ShowLoadingUtil.dismiss();
    }

    private void QrCode() {
        scan_barcode.setBackground(getResources().getDrawable(R.drawable.selector_btn_storke_orang));
        scan_wine.setBackground(getResources().getDrawable(R.drawable.selector_btn_orange));
        scan_barcode.setTextColor(getResources().getColor(R.color.gold_color));
        scan_wine.setTextColor(getResources().getColor(R.color.black));
    }

    private void BarCode() {
        scan_barcode.setBackground(getResources().getDrawable(R.drawable.selector_btn_orange));
        scan_wine.setBackground(getResources().getDrawable(R.drawable.selector_btn_storke_orang));
        scan_barcode.setTextColor(getResources().getColor(R.color.black));
        scan_wine.setTextColor(getResources().getColor(R.color.gold_color));
    }

    @Override
    public void onKeyBack() {
        dismiss();
        wineGone();
        wineListInfoPresenter.cancleWineListInfo();
        ReStartTakePic();
    }

    public void ReStartTakePic(){
        restartCamera();
        takePic();
    }


}
