package com.haier.cellarette.biz_demo1.api;

import com.haier.cellarette.biz_demo1.bean.DemoNewModel;
import com.haier.cellarette.libvariants.NetConfig;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RankingApi {

//    //个人段位信息
//    @JSON(NetConfig.URL_ZHIDAO_V1 + "user/get.user.fration")
//    Call<RankingResponse> getRanking(String json);
//
//
//    //好友（微信）排行榜
//    @JSON(NetConfig.URL_ZHIDAO_V1 + "user/rank/friends")
//    Call<FriendsListInfo> getFriendsRanking(String json);
//
//    //切换称号
//    @JSON(NetConfig.URL_ZHIDAO_V1 + "user/update.user.show.fration")
//    Call<UpdataRankingResponse> updataRanking(String json);
//
//    //关注、粉丝列表
//    @JSON(NetConfig.URL_ZHIDAO_V1 + "user/followers")
//    Call<FollowListInfo> getFollow(String json);

    @FormUrlEncoded
    @POST(NetConfig.SERVER_ISERVICE2 + "http://v.juhe.cn/toutiao/index")
    Call<DemoNewModel> get_request_post(@Field("type") String type, @Field("key") String key);
}
