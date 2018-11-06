package com.haier.cellarette.jiuzhidao_demo.glinbufen.presenter;

import android.util.Log;

import com.example.shining.libglin.glin.Callback;
import com.example.shining.libglin.glin.Result;
import com.example.shining.libglin.juhenet.JuheNet;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.api.ApiDemo1;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinjuhe.DemoJuheFileModel;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinjuhe.DemoJuheModel;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.glinmvp.GlinPresenter;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.view.Demo1JuheView;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;


public class Demo1JuhePresenter extends GlinPresenter<Demo1JuheView> {

    /**
     * 直接根据地质拼接键值对解析的请求方式
     */
    public void getDemo1JuhePresenter(String type, String key) {
        JuheNet.build(ApiDemo1.class, getIdentifier()).getList(type, key).enqueue(new Callback<DemoJuheModel>() {
            @Override
            public void onResponse(Result<DemoJuheModel> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetDemo1JuheFailed(result.getMessage());
                    return;
                }
                DemoJuheModel data = result.getResult();
                if (data == null) {
                    //暂无数据bufen
                    getView().onGetDemo1JuheEmpty(data);
                    return;
                }
                //请求成功bufen
                getView().onGetDemo1JuheSuccess(data);

            }
        });
    }

    /**
     * 上传文件glin请求方式
     */
    public void getDemo1JuheFilePresenter(File file, String rate, String pname, String device_id, String key) {
        JuheNet.build(ApiDemo1.class, getIdentifier()).getfile(file, rate, pname, device_id, key).enqueue(new Callback<DemoJuheFileModel>() {
            @Override
            public void onResponse(Result<DemoJuheFileModel> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetDemo1JuheFlieFailed(result.getMessage());
                    return;
                }
                DemoJuheFileModel data = result.getResult();
                if (data == null) {
                    //暂无数据bufen
                    getView().onGetDemo1JuheFlieEmpty(data);
                    return;
                }
                //请求成功bufen
                getView().onGetDemo1JuheFlieSuccess(data);
            }
        });
    }

    /**
     * 上传文件retrofit请求方式
     */
    public void get_request_fileupload_retrofit(String rate, String pname, String device_id, String key, File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        // 添加描述
//        String descriptionString = "hello, 这是文件描述";
        RequestBody rate1 = RequestBody.create(MediaType.parse("multipart/form-data"), rate);
        RequestBody pname1 = RequestBody.create(MediaType.parse("multipart/form-data"), pname);
        RequestBody device_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), device_id);
        RequestBody key1 = RequestBody.create(MediaType.parse("multipart/form-data"), key);

        RetrofitNetNew.build(ApiDemo1.class, getIdentifier()).uploadFile(rate1, pname1, device_id1, key1, body).
                enqueue(new retrofit2.Callback<DemoJuheFileModel>() {
                    @Override
                    public void onResponse(Call<DemoJuheFileModel> call, Response<DemoJuheFileModel> response) {
                        Log.v("Upload", response.body().getResult() + "");
                        if (!hasView()) {
                            return;
                        }
                        DemoJuheFileModel detectResult = response.body();
                        if (detectResult != null) {
                            getView().onGetDemo1JuheRetrofitFlieSuccess(detectResult);
                        } else {
                            getView().onGetDemo1JuheRetrofitFlieEmpty(detectResult);
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DemoJuheFileModel> call, Throwable t) {
                        if (getView() == null) {
                            return;
                        }
                        getView().onGetDemo1JuheRetrofitFlieFailed(t.getMessage());
                        call.cancel();
                    }
                });
    }

}
