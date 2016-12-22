package com.vsouhrada.android.lib.mflow.demo.core.mvp.presenter;

import android.content.Context;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.DrawerScreen;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;
import com.vsouhrada.android.lib.mflow.presenter.CoreDrawerPresenter;
import com.vsouhrada.android.lib.mflow.presenter.DrawerPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Parcer;
import mortar.Blueprint;


/**
 * @author vsouhrada
 * @version 1.2.0
 * @see CoreDrawerPresenter
 * @since 1.0.0
 */
@Singleton
public class MainPresenter extends CoreDrawerPresenter {

  @Inject
  protected MainPresenter(Parcer<Object> parcer, @ForApplication Context context, ActionBarPresenter actionBar,
                          DrawerPresenter drawerPresenter, ActivityPresenter activityPresenter) {

    super(parcer, context, actionBar, drawerPresenter, activityPresenter);
  }

  @Override
  protected Blueprint getFirstScreen() {
    return new FeaturesScreen();
  }

  @Override protected Blueprint getDrawerScreen() {
    return new DrawerScreen();
  }
}
