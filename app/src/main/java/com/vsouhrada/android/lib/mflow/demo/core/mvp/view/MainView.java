package com.vsouhrada.android.lib.mflow.demo.core.mvp.view;

import android.content.Context;
import android.util.AttributeSet;

import com.vsouhrada.android.lib.mflow.demo.core.mvp.presenter.MainPresenter;
import com.vsouhrada.android.lib.mflow.presenter.CoreDrawerPresenter;
import com.vsouhrada.android.lib.mflow.view.CoreDrawerView;

import javax.inject.Inject;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see CoreDrawerView
 * @since 1.0.0
 */
public class MainView extends CoreDrawerView {

  @Inject
  MainPresenter presenter;

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public CoreDrawerPresenter getPresenter() {
    return presenter;
  }
}
