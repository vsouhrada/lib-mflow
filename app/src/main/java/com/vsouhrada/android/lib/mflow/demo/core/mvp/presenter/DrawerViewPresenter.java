package com.vsouhrada.android.lib.mflow.demo.core.mvp.presenter;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.vsouhrada.android.lib.mflow.annotations.DrawerAdapter;
import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.view.DrawerView;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen.ABDemoMainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.FlowPresenterActivity;
import com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.screen.DialogDemoScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen.FlowDemoAScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.screen.NestedScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.screen.MasterScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.tab_indicator.TabScreen;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;

import javax.inject.Inject;

import flow.Flow;
import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see ViewPresenter
 * @see DrawerView
 * @since 1.0.0
 */
public class DrawerViewPresenter extends ViewPresenter<DrawerView> {

  public static final int ACTION_ACTION_BAR = 0;
  public static final int ACTION_SAVE_STATE = 1;
  public static final int ACTION_FLOW = 2;
  public static final int ACTION_NESTED_VIEW = 3;
  public static final int ACTION_TAB_PAGER = 4;
  public static final int ACTION_START_ACTIVITY = 5;
  public static final int ACTION_DIALOGS = 6;

  private static final String SAVED_ARGUMENT_CURRENT_SELECTED_POSITION = "sCurrentSelectedPosition";

  private final Flow flow;
  private final ArrayAdapter<String> drawerAdapter;
  private final ActivityPresenter activityPresenter;

  @Inject
  public DrawerViewPresenter(@MainScope Flow flow, @DrawerAdapter ArrayAdapter<String> drawerAdapter, ActivityPresenter activityPresenter) {
    this.flow = flow;
    this.drawerAdapter = drawerAdapter;
    this.activityPresenter = activityPresenter;
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    getView().setListAdapter(drawerAdapter);

    if (savedInstanceState != null) {
      getView().currentSelectedPosition = savedInstanceState.getInt(SAVED_ARGUMENT_CURRENT_SELECTED_POSITION, 0);
    }
  }

  @Override protected void onSave(Bundle outState) {
    super.onSave(outState);
    if (getView() != null) {
      outState.putInt(SAVED_ARGUMENT_CURRENT_SELECTED_POSITION, getView().currentSelectedPosition);
    }
  }

  public void goToScreenAtPosition(int position) {
    switch (position) {

      case ACTION_ACTION_BAR:
        flow.replaceTo(new ABDemoMainScreen());
        break;

      case ACTION_SAVE_STATE:
        flow.replaceTo(new MasterScreen());
        break;

      case ACTION_FLOW:
        flow.replaceTo(new FlowDemoAScreen());
        break;

      case ACTION_NESTED_VIEW:
        flow.replaceTo(new NestedScreen());
        break;

      case ACTION_TAB_PAGER:
        flow.replaceTo(new TabScreen());
        break;

      case ACTION_START_ACTIVITY:
        activityPresenter.startActivity(FlowPresenterActivity.class);
        break;

      case ACTION_DIALOGS:
        flow.goTo(new DialogDemoScreen());
        break;

      default:
        break;
    }
  }
}
