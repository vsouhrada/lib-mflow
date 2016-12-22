package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter.ABDemoMainPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @since 1.0.0
 */
public class ABDemoMainView extends RelativeLayout {

  @Inject
  ABDemoMainPresenter presenter;

  public ABDemoMainView(Context context, AttributeSet attrs) {
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

  @OnClick(R.id.ab_demo_home_button)
  public void onHomeButtonClickAction() {
    presenter.showHomeButtonActionBarDemo();
  }

  @OnClick(R.id.ab_demo_menu_xml_button)
  public void onMenuXmlButton() {
    presenter.showLoadingMenuFromXmlAction();
  }

  @OnClick(R.id.ab_demo_custom_parent)
  public void onCustomParentButton() {
    presenter.showCustomParentAction();
  }
}
