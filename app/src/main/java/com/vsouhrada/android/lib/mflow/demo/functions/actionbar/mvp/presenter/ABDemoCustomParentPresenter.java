package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.presenter;

import android.os.Bundle;

import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoCustomParentView;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;

import javax.inject.Inject;

import flow.Flow;
import mortar.ViewPresenter;
import rx.util.functions.Action0;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see ViewPresenter
 * @see ABDemoCustomParentView
 * @since 1.1.0
 */
public class ABDemoCustomParentPresenter extends ViewPresenter<ABDemoCustomParentView> {

  private final Flow flow;
  private final ActionBarPresenter actionBarPresenter;

  @Inject
  public ABDemoCustomParentPresenter(Flow flow, ActionBarPresenter actionBarPresenter) {
    this.flow = flow;
    this.actionBarPresenter = actionBarPresenter;
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    // Here UP navigation is handle
    actionBarPresenter.setOnUpAction(new Action0() {

      @Override public void call() {
        flow.goBack();
      }
    });
  }
}
