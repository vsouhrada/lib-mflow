package com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen.FlowDemoBScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.view.FlowDemoAView;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;
import com.vsouhrada.android.lib.mflow.presenter.IOnBackPressedCallback;

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
public class FlowDemoAPresenter extends ViewPresenter<FlowDemoAView> {

  private final Context context;
  private final Flow flow;
  private final ActionBarPresenter actionBar;
  private final ActivityPresenter activityPresenter;

  @Inject
  public FlowDemoAPresenter(@ForApplication Context context, @MainScope Flow flow, ActionBarPresenter actionBar,
                            ActivityPresenter activityPresenter) {

    this.context = context;
    this.flow = flow;
    this.actionBar = actionBar;
    this.activityPresenter = activityPresenter;
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    // Custom reaction on back pressed action (hardware back button on a device)
    activityPresenter.setOnBackPressedAction(new IOnBackPressedCallback() {

      @Override public boolean onBackPressed() {
        Toast.makeText(context, R.string.flow_demo_custom_back_action_toast, Toast.LENGTH_SHORT).show();
        return false;
      }
    });
  }

  @Override
  protected void onSave(Bundle outState) {
    super.onSave(outState);
  }

  @Override public void dropView(FlowDemoAView view) {
    super.dropView(view);
  }

  @Override protected void onExitScope() {
    super.onExitScope();
  }

  public void goTo() {
    flow.goTo(new FlowDemoBScreen());
  }

  public void reset() {
    flow.resetTo(new FeaturesScreen());
  }

  public void replace() {
    flow.replaceTo(new FeaturesScreen());
  }

}
