package com.vsouhrada.android.lib.mflow.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;

import com.vsouhrada.android.lib.mflow.R;
import com.vsouhrada.android.lib.mflow.presenter.CoreDrawerPresenter;
import com.vsouhrada.android.lib.mflow.screen.CanShowDrawer;
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
 * @see DrawerLayout
 * @see ICoreView
 * @see CanShowDrawer
 * @see Blueprint
 * @since 1.0.0
 */
public abstract class CoreDrawerView extends DrawerLayout implements ICoreView, CanShowDrawer<Blueprint> {

  private final ScreenConductor<Blueprint> screenConductor;

  private ActionBarDrawerToggle drawerToggle;

  public CoreDrawerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
    screenConductor = new ScreenConductor<>(context, this);
  }

  /**
   * This method returns instance of presenter that control this view.
   *
   * @return instance of presenter that control this view
   * @since 1.0.0
   */
  public abstract CoreDrawerPresenter getPresenter();

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    getPresenter().takeView(this);
    drawerToggle = new ActionBarDrawerToggle(
            (Activity) getContext(),
            this,
            R.drawable.ic_drawer,
            R.string.drawer_open,
            R.string.drawer_close
    ) {

      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
      }
    };

    post(new Runnable() {

      @Override
      public void run() {
        drawerToggle.syncState();
      }
    });
    setDrawerListener(drawerToggle);

  }

  @Override
  public Flow getFlow() {
    return getPresenter().getFlow();
  }

  /**
   * Returns {@link ActionBarDrawerToggle}.
   *
   * @return current instance of {@link ActionBarDrawerToggle}
   * @since 1.0.0
   */
  public ActionBarDrawerToggle getDrawerToggle() {
    return drawerToggle;
  }

  @Override
  public void showScreen(Blueprint screen, Flow.Direction direction) {
    screenConductor.showScreen(screen, direction);
  }

  @Override public void showDrawer(Blueprint screen) {
    screenConductor.showDrawer(screen);
  }

}