package com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.presenter.FlowDemoBPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.2.0
 * @see LinearLayout
 * @since 1.2.0
 */
public class FlowDemoBView extends LinearLayout {

  @Inject
  FlowDemoBPresenter presenter;

  public FlowDemoBView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    ButterKnife.inject(this);

    presenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    presenter.dropView(this);

    super.onDetachedFromWindow();
  }

  @OnClick(R.id.flow_b_forward_button)
  public void onForwardButtonAction() {
    presenter.forward();
  }

  @OnClick(R.id.flow_b_back_button)
  public void onBackButtonAction() {
   presenter.back();
  }

  @OnClick(R.id.flow_b_reset_button)
  public void onResetButtonAction() {
    presenter.reset();
  }

  @OnClick(R.id.flow_b_replace_button)
  public void onReplaceButtonAction() {
    presenter.replace();
  }
}
