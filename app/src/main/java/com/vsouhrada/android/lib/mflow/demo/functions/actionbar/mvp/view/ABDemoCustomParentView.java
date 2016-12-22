package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter.ABDemoCustomParentPresenter;

import javax.inject.Inject;

import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see FrameLayout
 * @since 1.1.0
 */
public class ABDemoCustomParentView extends FrameLayout {

  @Inject
  ABDemoCustomParentPresenter presenter;

  public ABDemoCustomParentView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    presenter.dropView(this);

    super.onDetachedFromWindow();
  }
}
