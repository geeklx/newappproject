package com.haier.index.zxing;//package com.haier.zxing;
//
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//
//import com.example.shining.baselibrary.toasts.ToastUtil;
//import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
//import com.haier.cellarette.baselibrary.toasts2.Toasty;
//import com.haier.zxing.core.QRCodeView;
//import com.haier.zxing.core.ZXingView;
//
//
///**
// * Author:sunnyhack
// * E-mail:sunnyhack@live.com
// * Date:on 18/4/24.
// * Description:
// */
//
//public class QRCodeScanActivity extends BaseActivity implements QRCodeView.Delegate {
//    private static final String TAG = QRCodeScanActivity.class.getSimpleName();
//    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
//
//    private QRCodeView mQRCodeView;
//    private TextView scan_barcode;
//    private TextView scan_qrcode;
//
//    @Override
//    protected void setup(@Nullable Bundle savedInstanceState) {
//        super.setup(savedInstanceState);
//        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
//        mQRCodeView.setDelegate(this);
//        scan_barcode = (TextView) findViewById(R.id.scan_barcode);
//        scan_qrcode = (TextView) findViewById(R.id.scan_qrcode);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_qr_scan;
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        boolean isHava = mQRCodeView.startCamera();
//        if (!isHava) {
//            Toasty.error(this,"摄像头不可开启。").show();
//            finish();
//        }
//
//        mQRCodeView.startCamera();
//        mQRCodeView.showScanRect();
//
//
//        mQRCodeView.startSpot();
//        BarCode();//初始化时条形码
//    }
//
//    //震动 酒柜 没什么用！~
//    private void vibrate() {
//        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        vibrator.vibrate(200);
//    }
//
//    @Override
//    public void onScanQRCodeSuccess(String result) {
//        Log.i(TAG, "result:" + result);
//        Toasty.success(this,result).show();
//        vibrate();
//        mQRCodeView.startSpot();
//    }
//
//    @Override
//    public void onScanQRCodeOpenCameraError() {
//        Log.e(TAG, "打开相机出错");
//    }
//
//    public void onClick(View v) {
//        int i = v.getId();
//        if (i == R.id.scan_barcode) {
//            BarCode();
//        } else if (i == R.id.scan_qrcode) {
//            QrCode();
//        }
//    }
//
//
//    //二维码
//    private void QrCode() {
//        mQRCodeView.changeToScanQRCodeStyle();
//        scan_barcode.setBackground(getResources().getDrawable(R.drawable.selector_btn_storke_orang));
//        scan_qrcode.setBackground(getResources().getDrawable(R.drawable.selector_btn_orange));
//        scan_barcode.setTextColor(getResources().getColor(R.color.gold_color));
//        scan_qrcode.setTextColor(getResources().getColor(R.color.black));
//    }
//
//    //条形码
//    private void BarCode() {
//        mQRCodeView.changeToScanBarcodeStyle();
//        scan_barcode.setBackground(getResources().getDrawable(R.drawable.selector_btn_orange));
//        scan_qrcode.setBackground(getResources().getDrawable(R.drawable.selector_btn_storke_orang));
//        scan_barcode.setTextColor(getResources().getColor(R.color.black));
//        scan_qrcode.setTextColor(getResources().getColor(R.color.gold_color));
//    }
//
//    @Override
//    protected void onStop() {
//        mQRCodeView.stopCamera();
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        mQRCodeView.onDestroy();
//        super.onDestroy();
//    }
//
////    @Override
////    protected void onActResult(int requestCode, int resultCode, Intent data) {
////        super.onActResult(requestCode, resultCode, data);
////        mQRCodeView.showScanRect();
////
////        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
////
////            new AsyncTask<Void, Void, String>() {
////                @Override
////                protected String doInBackground(Void... params) {
////                    return QRCodeDecoder.syncDecodeQRCode("");
////                }
////
////                @Override
////                protected void onPostExecute(String result) {
////                    if (TextUtils.isEmpty(result)) {
////                        Toast.makeText(QRCodeScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
////                    } else {
////                        Toast.makeText(QRCodeScanActivity.this, result, Toast.LENGTH_SHORT).show();
////                    }
////                }
////            }.execute();
////        }
////
////    }
//
//
//}
