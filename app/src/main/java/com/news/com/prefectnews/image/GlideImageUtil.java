package com.news.com.prefectnews.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/3/6.
 */

public class GlideImageUtil {
public static void ShowImages(Context context, String url, ImageView imag){
    Glide.with(context).load(url).override(250,250).into(imag);

}
}
