package com.vsouhrada.android.lib.mflow.demo.functions.tab_indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vsouhrada.android.lib.mflow.demo.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mortar.Mortar;


/**
 * @author vsouhrada]
 * @version 1.0.0
 * @see LinearLayout
 * @since 1.0.0
 */
public class TabView extends LinearLayout {

  @InjectView(R.id.viewPager)
  ViewPager viewPager;

  @Inject TabPagerPresenter presenter;


  public TabView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    ButterKnife.inject(this);

    viewPager.setAdapter(new TabPagerAdapter(getContext()));

    presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    presenter.dropView(this);

    super.onDetachedFromWindow();
  }


}
