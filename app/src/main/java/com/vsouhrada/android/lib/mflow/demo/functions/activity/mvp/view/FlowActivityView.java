package com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.presenter.FlowActivityPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see LinearLayout
 * @since 1.0.0
 */
public class FlowActivityView extends LinearLayout {

  @Inject
  FlowActivityPresenter presenter;

  public FlowActivityView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    ButterKnife.inject(this);

    presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    presenter.dropView(this);

    super.onDetachedFromWindow();
  }

  @SuppressWarnings("UnusedDeclaration") // Used by ButterKnife
  @OnClick(R.id.flow_activity_finish_button)
  public void onFinishButtonAction() {
    presenter.finish();
  }

}