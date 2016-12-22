package com.vsouhrada.android.lib.mflow.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.vsouhrada.android.lib.mflow.presenter.CorePresenter;
import com.vsouhrada.android.lib.mflow.screen.ScreenConductor;
import com.vsouhrada.android.lib.mflow.ui.ICoreView;

import flow.Flow;
import mortar.Blueprint;
import mortar.Mortar;


/**
 * <p>Base class for the Main View which must created when you want to use the MFlow framework.</p>
 * <p>The view, usually implemented by an Activity (it may be a Fragment, a View… depending on how the app is structured),
 * will contain a reference to the presenter. Presenter will be provided by a dependency injector called <b>Dagger</b></p>
 * <p>The only thing that the view will do is calling a method from the presenter every time there is an interface action
 * (a button click for example).</p>
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see FrameLayout
 * @see ICoreView
 * @since 1.0.0
 */
public abstract class CoreView extends FrameLayout implements ICoreView {

  private final ScreenConductor<Blueprint> screenConductor;

  /**
   * Construct a new {@link CoreView} instance for Main View.
   *
   * @param context current application context
   * @param attrs   attributes for view
   */
  public CoreView(Context context, AttributeSet attrs) {
    super(context, attrs);
    // Provide injection
    Mortar.inject(context, this);
    // Create new ScreenConductor instance
    screenConductor = new ScreenConductor<>(context, this);
  }

  /**
   * This method returns instance of presenter that control this view.
   *
   * @return instance of presenter that control this view
   * @since 1.0.0
   */
  public abstract CorePresenter getPresenter();

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    getPresenter().takeView(this);
  }

  @Override
  public Flow getFlow() {
    return getPresenter().getFlow();
  }


  @Override
  public void showScreen(Blueprint screen, Flow.Direction direction) {
    screenConductor.showScreen(screen, direction);
  }

}