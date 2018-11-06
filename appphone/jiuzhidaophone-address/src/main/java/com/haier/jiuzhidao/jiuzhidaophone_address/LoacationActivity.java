//package com.haier.jiuzhidao.jiuzhidaophone_address;
//
//import android.animation.ObjectAnimator;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Matrix;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.amap.api.location.AMapLocation;
////import com.amap.api.maps2d.AMap;
////import com.amap.api.maps2d.CameraUpdateFactory;
////import com.amap.api.maps2d.MapView;
////import com.amap.api.maps2d.MapsInitializer;
////import com.amap.api.maps2d.UiSettings;
////import com.amap.api.maps2d.model.BitmapDescriptorFactory;
////import com.amap.api.maps2d.model.CameraPosition;
////import com.amap.api.maps2d.model.LatLng;
////import com.amap.api.maps2d.model.Marker;
////import com.amap.api.maps2d.model.MarkerOptions;
////import com.amap.api.services.core.LatLonPoint;
////import com.amap.api.services.geocoder.GeocodeResult;
////import com.amap.api.services.geocoder.GeocodeSearch;
////import com.amap.api.services.geocoder.RegeocodeQuery;
////import com.amap.api.services.geocoder.RegeocodeResult;
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.CameraUpdateFactory;
//import com.amap.api.maps.MapView;
//import com.amap.api.maps.MapsInitializer;
//import com.amap.api.maps.UiSettings;
//import com.amap.api.maps.model.BitmapDescriptorFactory;
//import com.amap.api.maps.model.CameraPosition;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.Marker;
//import com.amap.api.maps.model.MarkerOptions;
//import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
//import com.haier.cellarette.baselibrary.toasts.ToastUtil;
//import com.haier.jiuzhidao.jiuzhidaophone_address.utils.AmapLocationUtil;
//
///**
// * Created by BJColor on 2018/1/22.
// */
//
//public class LoacationActivity extends BaseActivity implements AMap.OnCameraChangeListener, GeocodeSearch.OnGeocodeSearchListener, View.OnClickListener {
//
//    private MapView mapView;
//    private AMap mMap;
//    private Marker marker;
//    private GeocodeSearch geocoderSearch;
//    private Button btnMyLoc;
//    private String address;
//    private double latitude;
//    private double longitude;
//    private UiSettings uiSettings;
//    private RegeocodeQuery query;
//    private String adCode;
//    private AmapLocationUtil locationUtil;
//    private TextView tvLocationAddress;
//    private EditText etLocationAddress;
//    private Button btnOk;
//    private ImageView ivBack;
//    private TextView tvTitle;
//    private LatLng latLng;
//    private ObjectAnimator objectAnimator;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_location;
//    }
//
//    @Override
//    protected void setup(@Nullable Bundle savedInstanceState) {
//
//        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
////        显示全世界地图
//        MapsInitializer.loadWorldGridMap(true);
//        FindId();
//        init(savedInstanceState);
//        setClick();
////         初始化定位我的位置
//        initLocation();
//    }
//
//    private void FindId() {
//        ivBack = findViewById(R.id.iv_back);
//        tvTitle = findViewById(R.id.tv_title);
//        tvTitle.setText(getResources().getString(R.string.okAddress));
//        mapView = findViewById(R.id.mMapView);
//        btnMyLoc = findViewById(R.id.btn_amp_address);
//        btnOk = findViewById(R.id.bt_ok);
//        tvLocationAddress = findViewById(R.id.tv_location_address);
//        etLocationAddress = findViewById(R.id.et_location_address);
//
//        ivBack.setOnClickListener(this);
//        btnMyLoc.setOnClickListener(this);
//        btnOk.setOnClickListener(this);
//    }
//
//    private void init(Bundle savedInstanceState) {
//        mapView.onCreate(savedInstanceState); // 此方法必须重写
//        if (mMap == null) {
//            mMap = mapView.getMap();
//            //如果不是2d需要这么设置 才能显示大小
////            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
//            mMap.getUiSettings().setZoomControlsEnabled(false);
//            uiSettings = mMap.getUiSettings();
//            marker = mMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
////                    .draggable(true)  //拖拽暂时无用
//                    .icon(BitmapDescriptorFactory.fromBitmap(changeBitmapSize())));
////       mark标志固定在屏幕中心
//            marker.setPositionByPixels(mapView.getWidth() / 2,
//                    mapView.getHeight() / 2);
//        }
//    }
//
//
//    private void setClick() {
//        btnMyLoc.setOnClickListener(this);
//        //        逆编码获取地址
//        geocoderSearch = new GeocodeSearch(this);
//        geocoderSearch.setOnGeocodeSearchListener(this);
//        //        地图改变监听
//        mMap.setOnCameraChangeListener(this);
//    }
//
//    /**
//     * 初始化位置(我的位置)
//     */
//    private void initLocation() {
//        locationUtil = new AmapLocationUtil(this);
//        locationUtil.setLocationListener(new AmapLocationUtil.LocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation amapLocation) {
//                if (amapLocation.getErrorCode() == 0) {
//                    latitude = amapLocation.getLatitude();
//                    longitude = amapLocation.getLatitude();
//                    address = amapLocation.getAddress();
//                    adCode = amapLocation.getAdCode();
//                    latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
//
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                            latLng, 18));
////                  设置mark点
//                    marker.setPosition(latLng);
//                }
//            }
//        });
//    }
//
//
//    /**
//     * setOnGeocodeSearchListener  回调
//     *
//     * @param result
//     * @param rCode
//     */
//    @Override
//    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
//        if (rCode == 1000) {
//            if (result != null && result.getRegeocodeAddress() != null
//                    && result.getRegeocodeAddress().getFormatAddress() != null &&
//                    !result.getRegeocodeAddress().getFormatAddress().equals("")
//                    ) {
//                address = result.getRegeocodeAddress().getFormatAddress() + "附近";
//                adCode = result.getRegeocodeAddress().getAdCode();
//                setMark(latitude, longitude, address);
//            } else {
////                ToastUtil.showToastCenter("定位错误");
//            }
//        } else {
//            ToastUtil.showToastCenter("定位错误");
//        }
//    }
//
//    /**
//     * 地理编码查询回调
//     */
//    @Override
//    public void onGeocodeSearched(GeocodeResult result, int rCode) {
//        Log.e("1", "");
//    }
//
//
//    private void setMark(double latitude, double longitude, String address) {
////                设置中心点
//        CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude, longitude)
//                , 15, 30, 0));
////                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
////                        AMapUtil.convertToLatLng(latLonPoint), 15));
////        设置标题
//        marker.setTitle("定位的位置:");
////                  设置内容
//        marker.setSnippet(address);
////        自动显示弹框
//        marker.showInfoWindow();
//        tvLocationAddress.setText(address);
//        etLocationAddress.setText(address);
////        ToastUtil.showToastCenter(address);
//    }
//
//    /**
//     * 响应逆地理编码
//     */
//    public void getAddres(final LatLonPoint latLonPoint) {
//        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
//        if (null == query) {
//            query = new RegeocodeQuery(latLonPoint, 200,
//                    GeocodeSearch.AMAP);
//        } else {
//            query.setPoint(latLonPoint);
//        }
//        geocoderSearch.getFromLocationAsyn(query);
//    }
//
//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
//        if (i == R.id.bt_ok) {
//            Intent intent = new Intent();
//            String doorNum = etLocationAddress.getEditableText().toString().trim();
//            intent.putExtra("address", address);
//            intent.putExtra("lat", latitude);
//            intent.putExtra("lng", longitude);
//            intent.putExtra("code", adCode);
//            intent.putExtra("doorNum", doorNum);
//            setResult(12306, intent);
//            finish();
//        } else if (i == R.id.btn_amp_address) {
//            initLocation();
//            rotate(btnMyLoc);
//        } else if (i == R.id.iv_back) {
//            finish();
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(objectAnimator!=null){
//            objectAnimator.cancel();
//        }
//        mapView.onDestroy();
//    }
//
//    private Bitmap changeBitmapSize() {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.address_group);
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        //设置想要的大小
//        int newWidth = 50;
//        int newHeight = 70;
//
//        //计算压缩的比率
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//
//        //获取想要缩放的matrix
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//
//        //获取新的bitmap
//        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//        bitmap.getWidth();
//        bitmap.getHeight();
//        return bitmap;
//    }
//
//
//    /**
//     * @param position
//     */
//    @Override
//    public void onCameraChange(CameraPosition position) {
//        marker.setPosition(new LatLng(position.target.latitude, position.target.longitude));
//    }
//
//    @Override
//    public void onCameraChangeFinish(CameraPosition postion) {
//        latitude = postion.target.latitude;
//        longitude = postion.target.longitude;
//
//        getAddres(new LatLonPoint(postion.target.latitude, postion.target.longitude));
//    }
//
//
//    public void rotate(View view) {
//        objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
//
//        objectAnimator.setRepeatCount(1);
//
//        objectAnimator.setDuration(1000);
//
//        objectAnimator.start();
//    }
//
//
//}
