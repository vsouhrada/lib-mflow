package com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.presenter;

import android.content.Context;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.screen.FlowActivityScreen;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;
import com.vsouhrada.android.lib.mflow.presenter.CorePresenter;

import javax.inject.Inject;

import flow.Parcer;
import mortar.Blueprint;


/**
 * @author vsouhrada
 * @version 1.2.0
 * @see CorePresenter
 * @since 1.0.0
 */
public class FlowActivityCorePresenter extends CorePresenter {

  @Inject
  protected FlowActivityCorePresenter(Parcer<Object> parcer, @ForApplication Context context, ActionBarPresenter actionBar,
                                      ActivityPresenter activityPresenter) {

    super(parcer, context, actionBar, activityPresenter);
  }

  @Override protected Blueprint getFirstScreen() {
    return new FlowActivityScreen();
  }

}
