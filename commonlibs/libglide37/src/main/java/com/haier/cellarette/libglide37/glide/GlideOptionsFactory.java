package com.haier.cellarette.libglide37.glide;

import android.content.Context;

import java.util.HashMap;

public class GlideOptionsFactory {
    private static HashMap<Type, GlideOptions> mOptions;

    private GlideOptionsFactory() {}

    public static void init(Context ctx, int defLoading) {
        if (mOptions == null) {
            mOptions = new HashMap<>();
            mOptions.put(Type.DEFAULT, new GlideOptions(defLoading, 0));
            mOptions.put(Type.RADIUS, new GlideOptions(defLoading, dip2px(ctx,10)));
        }
    }

    public static GlideOptions get(Type type) {
        if (mOptions.containsKey(type)) {
            return mOptions.get(type);
        }

        throw new IllegalArgumentException();
    }

    public enum Type {
        DEFAULT (1), RADIUS (2);
        private int type;

        Type(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "type:" + type;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
