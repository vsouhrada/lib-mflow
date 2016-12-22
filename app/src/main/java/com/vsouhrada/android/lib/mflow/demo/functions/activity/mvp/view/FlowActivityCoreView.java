package com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.view;

import android.content.Context;
import android.util.AttributeSet;

import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.presenter.FlowActivityCorePresenter;
import com.vsouhrada.android.lib.mflow.presenter.CorePresenter;
import com.vsouhrada.android.lib.mflow.view.CoreView;

import javax.inject.Inject;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see CoreView
 * @since 1.0.0
 */
public class FlowActivityCoreView extends CoreView {

  @Inject
  FlowActivityCorePresenter presenter;

  public FlowActivityCoreView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public CorePresenter getPresenter() {
    return presenter;
  }
}
