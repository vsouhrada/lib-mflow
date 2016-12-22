package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoHomeButtonView;
import com.vsouhrada.android.lib.mflow.presenter.DrawerPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter
 * @since 1.0.0
 */
@Singleton
public class ABDemoHomeButtonPresenter extends ViewPresenter<ABDemoHomeButtonView> {

  private final DrawerPresenter drawerPresenter;

  @Inject
  public ABDemoHomeButtonPresenter(DrawerPresenter drawerPresenter) {
    this.drawerPresenter = drawerPresenter;
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    drawerPresenter.setConfig(new DrawerPresenter.Config(false, DrawerLayout.LOCK_MODE_LOCKED_CLOSED));
  }
}
