package com.haier.cellarette.libglide37;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haier.cellarette.libglide37.R;
import com.haier.cellarette.libglide37.glide.GlideOptions;
import com.haier.cellarette.libglide37.glide.GlideOptionsFactory;
import com.haier.cellarette.libglide37.glide.GlideUtil;


public class Test extends AppCompatActivity {
    ImageView iv_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlideOptionsFactory.init(this, R.drawable.ic_launcher);

        GlideUtil.display(Test.this, iv_img, "", GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        Glide.with(Test.this).load("").skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv_img);
        GlideOptions glideOptions = new GlideOptions(R.drawable.ic_launcher, R.drawable.ic_launcher, 300);
        GlideUtil.display(Test.this, iv_img, "", glideOptions);
    }
}
