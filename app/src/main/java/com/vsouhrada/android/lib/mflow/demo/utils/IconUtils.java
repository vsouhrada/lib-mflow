package com.vsouhrada.android.lib.mflow.demo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.vsouhrada.android.lib.mflow.demo.R;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 */
public class IconUtils {

  public static Drawable getDrawableFromAttributeResource(Context context, int attr) {
    TypedArray a = context.getTheme().obtainStyledAttributes(R.style.AppTheme, new int[]{attr});

    Drawable drawable = context.getResources().getDrawable(a.getResourceId(0, 0));

    a.recycle();

    return drawable;
  }

}
