package com.haier.shopcommon.jeffery.shopcate.presenter;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSONObject;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.shopcommon.jeffery.shopcate.bean.CategoryBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by JefferyLeng on 2018/6/20.
 */
public class ShopCatePresenter extends Presenter<IShopCategoryView> {

    private Context mContext;

    private ArrayList<CategoryBean.DataBean> cateListData = new ArrayList<>();

    public ArrayList<CategoryBean.DataBean> loadCateList() {
        String json = getJson(mContext, "category.json");
        CategoryBean categoryBean = JSONObject.parseObject(json, CategoryBean.class);
        for (int i = 0; i < categoryBean.getData().size(); i++) {
            CategoryBean.DataBean dataBean = categoryBean.getData().get(i);
            cateListData.add(dataBean);
        }

        return cateListData;
    }

    public void start(Context context) {
        this.mContext = context;
        getView().fillCateList(loadCateList());

    }

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


}
