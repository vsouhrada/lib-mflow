package com.vsouhrada.android.lib.mflow.demo.functions.tab_indicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsouhrada.android.lib.mflow.demo.R;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see PagerAdapter
 * @since 1.0.0
 */
public class TabPagerAdapter extends PagerAdapter {

  private final Context context;
  private SparseArray<View> views;

  public TabPagerAdapter(Context context) {
    this.context = context;
    views = new SparseArray<>();
  }

  @Override public int getCount() {
    return 2;
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    // If we already have this item instantiated, there is nothing to do.
    if (views.size() > 0) {
      View view = views.get(position);
      if (view != null) {
        return view;
      }
    }

    View view = LayoutInflater.from(container.getContext()).inflate(R.layout.v_tab_pager_content, container, false);
    views.put(position, view);
    container.addView(view);
    return view;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    View view = views.get(position);
    if (view != null) {
      views.remove(position);
      container.removeView(view);
    }
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getString(R.string.tab_pager_tab1_title);

      case 1:
        return context.getString(R.string.tab_pager_tab2_title);

      default:
        return "";
    }
  }
}
