package com.haier.cellarette.baselibrary.likebutton.widgets;

import com.haier.cellarette.baselibrary.likebutton.LikeButton;

/**
 * Created by Joel on 23/12/2015.
 */
public interface OnLikeListener {
    void liked(LikeButton likeButton);
    void unLiked(LikeButton likeButton);
}
