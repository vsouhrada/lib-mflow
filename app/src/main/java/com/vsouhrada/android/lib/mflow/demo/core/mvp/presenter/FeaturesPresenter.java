package com.vsouhrada.android.lib.mflow.demo.core.mvp.presenter;

import android.content.Context;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.view.FeaturesOverview;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen.ABDemoMainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.FlowPresenterActivity;
import com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.screen.DialogDemoScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen.FlowDemoAScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.screen.NestedScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.screen.MasterScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.tab_indicator.TabScreen;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see ViewPresenter
 * @see FeaturesOverview
 * @since 1.0.0
 */
@Singleton
public class FeaturesPresenter extends ViewPresenter<FeaturesOverview> {

  public static final int ACTION_ACTION_BAR = 0;
  public static final int ACTION_SAVE_STATE = 1;
  public static final int ACTION_FLOW = 2;
  public static final int ACTION_NESTED_VIEW = 3;
  public static final int ACTION_TAB_PAGER = 4;
  public static final int ACTION_START_ACTIVITY = 5;
  public static final int ACTION_DIALOGS_VIEW = 6;

  private final Flow flow;
  private final ActionBarPresenter actionBar;
  private Context context;
  private ActivityPresenter activityPresenter;

  @Inject
  public FeaturesPresenter(@MainScope Flow flow, ActionBarPresenter actionBar, @ForApplication Context context,
                           ActivityPresenter activityPresenter) {

    this.flow = flow;
    this.actionBar = actionBar;
    this.context = context;
    this.activityPresenter = activityPresenter;
  }

  public void forward(final int action) {
    switch (action) {
      case ACTION_ACTION_BAR:
        flow.goTo(new ABDemoMainScreen());
        break;

      case ACTION_SAVE_STATE:
        flow.goTo(new MasterScreen());
        break;

      case ACTION_FLOW:
        flow.goTo(new FlowDemoAScreen());
        break;

      case ACTION_NESTED_VIEW:
        flow.goTo(new NestedScreen());
        break;

      case ACTION_TAB_PAGER:
        flow.goTo(new TabScreen());
        break;

      case ACTION_START_ACTIVITY:
        activityPresenter.startActivity(FlowPresenterActivity.class);
        break;

      case ACTION_DIALOGS_VIEW:
        flow.goTo(new DialogDemoScreen());
        break;

      default:
        break;
    }
  }

}