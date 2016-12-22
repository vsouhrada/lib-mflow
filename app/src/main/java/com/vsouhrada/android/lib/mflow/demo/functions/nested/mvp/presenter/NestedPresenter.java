package com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.presenter;

import android.os.Bundle;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.event.NestedFirstChildEvent;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view.NestedView;

import javax.inject.Inject;

import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter<NestedView>
 * @since 1.0.0
 */
public class NestedPresenter extends ViewPresenter<NestedView> {

  private final Bus bus;

  private NestedFirstChildPresenter nestedFirstChildPresenter;
  private NestedSecondChildPresenter nestedSecondChildPresenter;

  @Inject
  public NestedPresenter(Bus bus) {
    this.bus = bus;
  }

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    bus.register(this);
  }

  @Override
  protected void onExitScope() {
    super.onExitScope();

    bus.unregister(this);
  }

  public void setNestedFirstChildPresenter(NestedFirstChildPresenter nestedFirstChildPresenter) {
    this.nestedFirstChildPresenter = nestedFirstChildPresenter;
  }

  public void setNestedSecondChildPresenter(NestedSecondChildPresenter nestedSecondChildPresenter) {
    this.nestedSecondChildPresenter = nestedSecondChildPresenter;
  }

  @SuppressWarnings("UnusedDeclaration") // Used by event bus
  @Subscribe
  public void onNestedFirstChildEvent(NestedFirstChildEvent event) {
    nestedSecondChildPresenter.showMessage(event.message);
  }

}