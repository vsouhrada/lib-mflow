package com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.presenter.FlowDemoAPresenter;

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
public class FlowDemoAView extends LinearLayout {

  @Inject
  FlowDemoAPresenter presenter;

  public FlowDemoAView(Context context, AttributeSet attrs) {
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

  @OnClick(R.id.flow_a_forward_button)
  public void onForwardButtonAction() {
    presenter.goTo();
  }

  @OnClick(R.id.flow_a_back_button)
  public void onBackButtonAction() {

  }

  @OnClick(R.id.flow_a_reset_button)
  public void onResetButtonAction() {
    presenter.reset();
  }

  @OnClick(R.id.flow_a_replace_button)
  public void onReplaceButtonAction() {
    presenter.replace();
  }
}
