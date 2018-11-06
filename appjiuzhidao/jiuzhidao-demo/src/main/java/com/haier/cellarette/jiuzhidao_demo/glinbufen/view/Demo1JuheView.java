package com.haier.cellarette.jiuzhidao_demo.glinbufen.view;


import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinjuhe.DemoJuheFileModel;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinjuhe.DemoJuheModel;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.glinmvp.GlinIView;

public interface Demo1JuheView extends GlinIView {
    void onGetDemo1JuheSuccess(DemoJuheModel data);

    void onGetDemo1JuheEmpty(DemoJuheModel data);

    void onGetDemo1JuheFailed(String msg);


    void onGetDemo1JuheFlieSuccess(DemoJuheFileModel data);

    void onGetDemo1JuheFlieEmpty(DemoJuheFileModel data);

    void onGetDemo1JuheFlieFailed(String msg);

    void onGetDemo1JuheRetrofitFlieSuccess(DemoJuheFileModel data);

    void onGetDemo1JuheRetrofitFlieEmpty(DemoJuheFileModel data);

    void onGetDemo1JuheRetrofitFlieFailed(String msg);

}
