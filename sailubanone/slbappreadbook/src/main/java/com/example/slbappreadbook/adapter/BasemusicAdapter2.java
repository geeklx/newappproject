package com.example.slbappreadbook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.slbappreadbook.R;
import com.example.slbappreadbook.domain.BaseBean2;
import com.geek.libglide47.base.GlideImageLoader;
import com.geek.libglide47.base.progress.CircleProgressView;
import com.geek.libglide47.base.progress.OnGlideImageViewListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.List;
import java.util.Random;

public class BasemusicAdapter2 extends PagerAdapter {
    private List<BaseBean2> views;
    private Context context;

    public BasemusicAdapter2(Context context, List<BaseBean2> items) {
        this.context = context;
        this.views = items;
    }

    public void setData(List<BaseBean2> items) {
        this.views = items;
    }

    public BaseBean2 getContacts(int pos) {
        return views.get(pos);
    }

    public BaseBean2 getNext(int pos) {
        if (pos + 1 > views.size() - 1) {
            return null;
        }
        return views.get(pos + 1);
    }

    public int getLastItem() {
        return views.get(views.size() - 1).getId();
    }

    @Override
    public int getCount() {
        return views.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
//        RelativeLayout view = (RelativeLayout) View.inflate(context, R.layout.activity_main41_item, null);
        View view = View.inflate(context, R.layout.activity_main41_item, null);
        final PhotoView glideImageView = view.findViewById(R.id.iv1);
        CircleProgressView progressView1 = view.findViewById(R.id.progressView1);
        CircleProgressView progressView2 = view.findViewById(R.id.progressView2);
        CircleProgressView progressView3 = view.findViewById(R.id.progressView3);
        final View maskView = view.findViewById(R.id.maskView);
        final CircleProgressView progressView;
        int randomNum = new Random().nextInt(3);
        switch (randomNum) {
            case 1:
                progressView = progressView2;
                break;
            case 2:
                progressView = progressView3;
                break;
            case 0:
            default:
                progressView = progressView1;
                break;
        }
        progressView1.setVisibility(View.GONE);
        progressView2.setVisibility(View.GONE);
        progressView3.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
        String image_url = views.get(position).getUrl();
        String image_url_thumbnail = views.get(position).getUrl();
        GlideImageLoader imageLoader = GlideImageLoader.create(glideImageView);
        RequestOptions requestOptions = imageLoader.requestOptions(R.color.black)
                .centerCrop();
        RequestOptions requestOptionsWithoutCache = imageLoader.requestOptions(R.color.black)
                .centerCrop()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        imageLoader.setOnGlideImageViewListener(image_url, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toasty.normal(context, exception.getMessage()).show();
                }
                progressView.setProgress(percent);
                progressView.setVisibility(isDone ? View.GONE : View.VISIBLE);
                maskView.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });

        imageLoader.requestBuilder(image_url, requestOptionsWithoutCache)
                .thumbnail(Glide.with(context)
                        .load(image_url_thumbnail)
                        .apply(requestOptions))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(glideImageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}