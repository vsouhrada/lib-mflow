package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter.ABDemoMenuXmlPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see RelativeLayout
 * @since 1.0.0
 */
public class ABDemoMenuXmlView extends RelativeLayout {

  @Inject
  ABDemoMenuXmlPresenter presenter;

  public ABDemoMenuXmlView(Context context, AttributeSet attrs) {
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

  @OnClick(R.id.ab_demo_menu_hide_item)
  public void onHideMenuItem() {
    presenter.hideMenuItem(R.id.abc_camera_item);
  }
}
