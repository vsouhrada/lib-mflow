package com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.presenter;

import android.os.Bundle;
import com.squareup.otto.Bus;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.event.NestedFirstChildEvent;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view.NestedFirstChildView;

import javax.inject.Inject;

import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter
 * @see NestedFirstChildView
 * @since 1.0.0
 */
public class NestedFirstChildPresenter extends ViewPresenter<NestedFirstChildView> {

  private final Bus bus;

  @Inject
  public NestedFirstChildPresenter(Bus bus) {
    this.bus = bus;
  }

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);
  }

  public void sendMessage(String message) {
    bus.post(new NestedFirstChildEvent(message));
  }
}