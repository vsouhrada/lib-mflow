package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen.ABDemoCustomParentScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen.ABDemoHomeButtonScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen.ABDemoMenuXmlScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoMainView;
import com.vsouhrada.android.lib.mflow.demo.utils.IconUtils;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import mortar.ViewPresenter;
import rx.util.functions.Action0;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see ViewPresenter
 * @see ABDemoMainView
 * @since 1.0.0
 */
@Singleton
public class ABDemoMainPresenter extends ViewPresenter<ABDemoMainView> {

  private final Context context;
  private final Flow flow;
  private final ActionBarPresenter actionBar;

  private List<ActionBarPresenter.MenuAction> menuActions;

  @Inject
  public ABDemoMainPresenter(@ForApplication Context context, @MainScope Flow flow, ActionBarPresenter actionBar) {
    this.context = context;
    this.flow = flow;
    this.actionBar = actionBar;
    this.menuActions = createMenuActions();
  }

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);
    // Get configuration
    ActionBarPresenter.Config actionBarConfig = actionBar.getConfig();
    // Set menu items with actions
    actionBarConfig = actionBarConfig.withAction(menuActions);
    // Set a new configuration - ActionBar will be updated
    actionBar.setConfig(actionBarConfig);
  }

  public void showHomeButtonActionBarDemo() {
    flow.goTo(new ABDemoHomeButtonScreen());
  }

  private List<ActionBarPresenter.MenuAction> createMenuActions() {
    if (menuActions == null) {
      menuActions = new ArrayList<>();
    }

    menuActions.add(new ActionBarPresenter.MenuAction(IconUtils.getDrawableFromAttributeResource(context, R.attr.ic_menu_camera),
            context.getString(R.string.ab_demo_main_menu_item_camera), new Action0() {

      @Override
      public void call() {
        // Do something here
      }
    }
    ));

    menuActions.add(new ActionBarPresenter.MenuAction(context.getString(R.string.ab_demo_main_menu_item_new), new Action0() {

      @Override
      public void call() {
        // Do something here
      }
    }));

    return menuActions;
  }

  public void showLoadingMenuFromXmlAction() {
    flow.goTo(new ABDemoMenuXmlScreen());
  }

  public void showCustomParentAction() {
    flow.goTo(new ABDemoCustomParentScreen());
  }
}
