package com.news.com.prefectnews.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Administrator on 2017/3/14.
 */

public  class SnackbarUtil {
    public static void useSnackbar(View view,String title){
        Snackbar.make(view,title,Snackbar.LENGTH_SHORT).setAction("Undo",null).show();
    }
}
