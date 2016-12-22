package com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen.FlowDemoAScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen.FlowDemoCScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.view.FlowDemoBView;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter
 * @see FlowDemoAView
 * @since 1.0.0
 */
@Singleton
public class FlowDemoBPresenter extends ViewPresenter<FlowDemoBView> {

  private final Context context;
  private final Flow flow;
  private final ActionBarPresenter actionBar;

  @Inject
  public FlowDemoBPresenter(@ForApplication Context context, @MainScope Flow flow, ActionBarPresenter actionBar) {
    this.context = context;
    this.flow = flow;
    this.actionBar = actionBar;
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);
  }

  @Override public void dropView(FlowDemoBView view) {
    super.dropView(view);
  }

  @Override protected void onExitScope() {
    super.onExitScope();
  }

  public void forward() {
    flow.goTo(new FlowDemoCScreen());
  }

  public void reset() {
    flow.resetTo(new FlowDemoAScreen());
  }

  public void replace() {
    flow.replaceTo(new FlowDemoAScreen());
  }

  public void back() {
    flow.goBack();
  }
}
