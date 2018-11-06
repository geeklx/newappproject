package com.haier.biz.wine_management.api;


import com.haier.biz.wine_management.bean.WineManageBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:
 */
public interface WineManagementApi {

    @POST(NetConfig.SERVER_ISERVICE2 + "tft/wineManage/getWineListJc66")
    Call<WineManageBean> pollOrder(@Body RequestBody body);
}
