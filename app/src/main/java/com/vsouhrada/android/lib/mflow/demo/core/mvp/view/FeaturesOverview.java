package com.vsouhrada.android.lib.mflow.demo.core.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.presenter.FeaturesPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see LinearLayout
 * @since 1.0.0
 */
public class FeaturesOverview extends LinearLayout {

  @Inject
  FeaturesPresenter presenter;

  public FeaturesOverview(Context context, AttributeSet attrs) {
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

  @OnClick(R.id.ab_demo_button)
  public void onABDemoButtonClick() {
    presenter.forward(FeaturesPresenter.ACTION_ACTION_BAR);
  }

  @OnClick(R.id.ab_save_state_button)
  public void onSaveStateButtonClick() {
    presenter.forward(FeaturesPresenter.ACTION_SAVE_STATE);
  }

  @OnClick(R.id.ab_flow_demo_button)
  public void onFlowButtonClick() {
    presenter.forward(FeaturesPresenter.ACTION_FLOW);
  }

  @OnClick(R.id.nested_button)
  public void onNestedButtonClick() {
    presenter.forward(FeaturesPresenter.ACTION_NESTED_VIEW);
  }

  @OnClick(R.id.tab_pager_button)
  public void onTabPagerButtonClick() {
    presenter.forward(FeaturesPresenter.ACTION_TAB_PAGER);
  }

  @OnClick(R.id.activity_button)
  public void onActivityButton() {
    presenter.forward(FeaturesPresenter.ACTION_START_ACTIVITY);
  }

  @OnClick(R.id.dialogs_button)
  public void onDialogsButton() {
    presenter.forward(FeaturesPresenter.ACTION_DIALOGS_VIEW);
  }
}
