//package com.haier.cellarette.biz_demo1.presenter;
//
//
//import com.haier.cellarette.biz_demo1.api.RankingApi;
//import com.haier.cellarette.biz_demo1.bean.DemoNewModel;
//import com.haier.cellarette.biz_demo1.view.DemoRetrofitNewView;
//import com.haier.cellarette.libmvp.mvp.Presenter;
//import com.haier.cellarette.libretrofit.RetrofitNetNew;
//import com.haier.cellarette.libvariants.NetConfig;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * 发表评论
// */
//
//public class HaoyouPresenter extends Presenter<DemoRetrofitNewView> {
//
//    public void huoquhaoyou(String topic_id) {
////        JSONObject p = new JSONObject();
////        p.put("topic_id", topic_id);
//////        p.put("first_parent_id", first_parent_id);
//////        p.put("parent_comment_id", parent_comment_id);
//////        p.put("content", content);
//////        p.put("pic_url", pic_url);
//////        p.put("vidio_url", vidio_url);
//////        p.put("audio_url", audio_url);
////        Http.select(Http.XCOOK).create(AnswerApi.class, getIdentifier()).huoquhaoyou(ParamsUtils.just(p))
////                .enqueue(new Callback<List<Huoquhaoyou>>() {
////                    @Override
////                    public void onResponse(Result<List<Huoquhaoyou>> result) {
////                        if (!hasView()){
////                            return;
////                        }
////                        if (!result.isOK()){
////                            getView().onHaoyouError(result.getMessage());
////                            return;
////                        }
////                        getView().onHaoyouSuccess(result.getResult());
////                    }
////                });
//    }
//
//    public void ceshi_ref(String type, String key) {
//        RetrofitNetNew.build(RankingApi.class, getIdentifier()).get_request_post(type, key).enqueue(new Callback<DemoNewModel>() {
//            @Override
//            public void onResponse(Call<DemoNewModel> call, Response<DemoNewModel> response) {
//                if (getView() == null) {
//                    return;
//                }
//                getView().banben(NetConfig.SERVER_ISERVICE);
//                DemoNewModel detectResult = response.body();
//                if (detectResult != null) {
//                    getView().Success(detectResult);
//                } else {
//                    getView().Failed(response.message());
//                }
//                call.cancel();
//
//            }
//
//            @Override
//            public void onFailure(Call<DemoNewModel> call, Throwable t) {
//                if (getView() == null) {
//                    return;
//                }
//                getView().Failed(t.getMessage());
//
//                call.cancel();
//            }
//        });
//    }
//}
