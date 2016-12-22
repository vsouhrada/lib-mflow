package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoMenuXmlView;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;
import com.vsouhrada.android.lib.mflow.presenter.IOnMenuInitializationCallback;

import javax.inject.Inject;

import mortar.ViewPresenter;
import rx.util.functions.Action1;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter<ABDemoMenuXmlView>
 * @since 1.0.0
 */
public class ABDemoMenuXmlPresenter extends ViewPresenter<ABDemoMenuXmlView> implements IOnMenuInitializationCallback {

  private final Context context;
  private final ActionBarPresenter actionBarPresenter;

  private MenuItem smileMenuItem;

  @Inject
  public ABDemoMenuXmlPresenter(@ForApplication Context context, ActionBarPresenter actionBarPresenter) {
    this.context = context;
    this.actionBarPresenter = actionBarPresenter;
    if (actionBarPresenter != null) {
      actionBarPresenter.registerOnMenuCreatedListener(this);
    }
  }

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    actionBarPresenter.setOnMenuItemClickAction(new Action1<Integer>() {

      @Override
      public void call(Integer integer) {
        Toast.makeText(context, R.string.ab_demo_menu_item_clicked, Toast.LENGTH_SHORT).show();
      }
    });

//    smileMenuItem = actionBarPresenter.getMenuItem(R.id.abc_camera_item3);
//
//    actionBarPresenter.getMenuItem(R.id.abc_camera_item2).setVisible(true);
  }

  public void hideMenuItem(final int menuItemId) {
    MenuItem menuItem = actionBarPresenter.getMenuItem(menuItemId);
    if (menuItem != null) {
      menuItem.setVisible(false);
    }

    smileMenuItem.setVisible(true);
  }

  @Override protected void onExitScope() {
    super.onExitScope();
    if (actionBarPresenter != null) {
      actionBarPresenter.unregisterOnMenuCreatedListener(this);
    }
  }

  @Override public void onMenuCreated() {
    smileMenuItem = actionBarPresenter.getMenuItem(R.id.abc_camera_item3);

    actionBarPresenter.getMenuItem(R.id.abc_camera_item2).setVisible(true);
  }

  @Override public void onMenuPrepared() {

  }
}
