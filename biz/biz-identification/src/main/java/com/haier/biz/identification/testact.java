//package com.haier.biz.identification;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Environment;
//
//import com.haier.biz.identification.bean.WineDetial;
//import com.haier.biz.identification.bean.WineItemDetail;
//import com.haier.biz.identification.presenter.WineDetialInfoPresenter;
//import com.haier.biz.identification.presenter.WineListInfoPresenter;
//import com.haier.biz.identification.view.WineDetialInfoView;
//import com.haier.biz.identification.view.WineListInfoView;
//import com.haier.cellarette.libretrofit.RetrofitNetNew;
//
//import java.io.File;
//
//public class testact extends Activity implements WineListInfoView,WineDetialInfoView {
//
//    private WineDetialInfoPresenter mp1 = new WineDetialInfoPresenter();
//    private WineListInfoPresenter mp = new WineListInfoPresenter();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mp1.onCreate(this);
//        mp.onCreate(this);
//        String sdcardPath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "geek11/file/607052.jpg";//
//        File appDir = new File(sdcardPath2);
//        RetrofitNetNew.config();
//        mp.getWineListInfo(appDir);
//        WineDetial.WineListBean model = new WineDetial.WineListBean();
//        model.setWine_id("");
//        model.setSign("");
//        mp1.getWineDetialInfo(model);
//    }
//
//    @Override
//    public void getListSuccess(WineDetial wineDetial) {
//        String a = "";
//
//    }
//
//    @Override
//    public void getListFailure(String msg) {
//        String b = "";
//    }
//
//    @Override
//    public String getIdentifier() {
//        return null;
//    }
//
//    @Override
//    public void getDetialSuccess(WineItemDetail wineItemDetail) {
//        String a = "";
//    }
//
//    @Override
//    public void getDetialFailure(String msg) {
//        String b = "";
//    }
//}
