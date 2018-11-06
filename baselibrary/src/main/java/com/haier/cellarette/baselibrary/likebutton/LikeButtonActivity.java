package com.haier.cellarette.baselibrary.likebutton;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.likebutton.widgets.OnAnimationEndListener;
import com.haier.cellarette.baselibrary.likebutton.widgets.OnLikeListener;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * android iconics
 */
public class LikeButtonActivity extends AppCompatActivity implements OnLikeListener, OnAnimationEndListener {

    public static final String TAG = "LikeButtonActivity";

    LikeButton starButton;
    LikeButton likeButton;
    LikeButton thumbButton;
    LikeButton smileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likebutton_main);
        starButton = findViewById(R.id.star_button);
        likeButton = findViewById(R.id.heart_button);
        thumbButton = findViewById(R.id.thumb_button);
        smileButton = findViewById(R.id.smile_button);

        starButton.setOnAnimationEndListener(this);
        starButton.setOnLikeListener(this);

        likeButton.setOnLikeListener(this);
        likeButton.setOnAnimationEndListener(this);

        smileButton.setOnLikeListener(this);
        smileButton.setOnAnimationEndListener(this);

        thumbButton.setOnLikeListener(this);
        thumbButton.setOnAnimationEndListener(this);

        thumbButton.setLiked(true);

        usingCustomIcons();

    }

    public void usingCustomIcons() {

        //shown when the button is in its default state or when unLiked.
        smileButton.setUnlikeDrawable(new BitmapDrawable(getResources(), new IconicsDrawable(this, CommunityMaterial.Icon.cmd_emoticon).colorRes(android.R.color.darker_gray).sizeDp(25).toBitmap()));

        //shown when the button is liked!
        smileButton.setLikeDrawable(new BitmapDrawable(getResources(), new IconicsDrawable(this, CommunityMaterial.Icon.cmd_emoticon).colorRes(android.R.color.holo_purple).sizeDp(25).toBitmap()));
    }

    @Override
    public void liked(LikeButton likeButton) {
        Toast.makeText(this, "Liked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        Toast.makeText(this, "Disliked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {
        Log.d(TAG, "Animation End for %s" + likeButton);
    }

}
