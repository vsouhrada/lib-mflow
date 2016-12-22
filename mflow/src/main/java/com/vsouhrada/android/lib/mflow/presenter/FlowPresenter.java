package com.vsouhrada.android.lib.mflow.presenter;

import android.os.Bundle;
import android.view.View;

import com.vsouhrada.android.lib.mflow.screen.CanShowScreen;

import flow.Backstack;
import flow.Flow;
import flow.Parcer;
import mortar.Blueprint;
import mortar.ViewPresenter;


/**
 * Base class for all presenters that manage a {@link Flow}.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see Blueprint
 * @see CanShowScreen
 * @see ViewPresenter
 * @see Flow
 * @since 1.0.0
 */
public abstract class FlowPresenter<S extends Blueprint, V extends View & CanShowScreen<S> /*& CanShowDrawer<S>*/>
        extends ViewPresenter<V> implements Flow.Listener {

  private static final String FLOW_KEY = "FLOW_KEY";

  private final Parcer<Object> parcer;

  private Flow flow;

  protected FlowPresenter(Parcer<Object> parcer) {
    this.parcer = parcer;
  }

  /**
   * Returns the first screen shown by this presenter.
   *
   * @since 1.0.0
   */
  protected abstract S getFirstScreen();

  @Override
  public void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    if (flow == null) {
      Backstack backstack;

      if (savedInstanceState != null) {
        backstack = Backstack.from(savedInstanceState.getParcelable(FLOW_KEY), parcer);
      } else {
        backstack = Backstack.fromUpChain(getFirstScreen());
      }

      flow = new Flow(backstack, this);
    }

    //noinspection unchecked
    showScreen((S) flow.getBackstack().current().getScreen(), null);
  }

  @Override
  public void onSave(Bundle outState) {
    super.onSave(outState);
    outState.putParcelable(FLOW_KEY, flow.getBackstack().getParcelable(parcer));
  }

  @Override
  public void go(Backstack backstack, Flow.Direction flowDirection) {
    //noinspection unchecked
    S newScreen = (S) backstack.current().getScreen();
    showScreen(newScreen, flowDirection);
  }

  /**
   * Go back one screen.
   *
   * @return false if going back is not possible.
   * @since 1.0.0
   */
  public boolean onRetreatSelected() {
    return getFlow().goBack();
  }

  /**
   * Go up one screen.
   *
   * @return false if going up is not possible.
   * @see {@link Flow#goUp()}
   * @since 1.0.0
   */
  public boolean onUpSelected() {
    return getFlow().goUp();
  }

  protected void showScreen(S newScreen, Flow.Direction flowDirection) {
    V view = getView();
    if (view == null) return;

    view.showScreen(newScreen, flowDirection);
  }

  /**
   * Returns current {@link Flow} instance.
   *
   * @return current {@link Flow} instance
   * @since 1.0.0
   */
  public final Flow getFlow() {
    return flow;
  }

}