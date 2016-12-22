package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter.ABDemoHomeButtonPresenter;

import javax.inject.Inject;

import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see FrameLayout
 * @since 1.0.0
 */
public class ABDemoHomeButtonView extends FrameLayout {

  @Inject
  ABDemoHomeButtonPresenter presenter;

  public ABDemoHomeButtonView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    presenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();

    presenter.dropView(this);
  }
}
