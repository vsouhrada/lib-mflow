package com.vsouhrada.android.lib.mflow.presenter;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;
import com.vsouhrada.android.lib.mflow.view.CoreDrawerView;

import flow.Flow;
import flow.Parcer;
import mortar.Blueprint;


/**
 * Base class for the Main Presenter which must created when you want to use the MFlow framework.
 * <p> A Presenter simplifies life by surviving configuration changes, and has just enough access to the Activity lifecycle to be
 * restored after process death.</p>
 * <p>This class is a really CORE Presenter. The main goal of this presenter is
 * handling with the {@link flow.Backstack}.
 * By this class you can manage transitions between different screens. Next this class automatically set titles for your screens on the
 * ActionBar.
 * </p>
 * <p>This presenter is an abstract and child of this class must return the first screen shown by this presenter.
 * See method {@link #getFirstScreen()}</p>
 *
 * @author vsouhrada
 * @version 1.2.0
 * @see CorePresenter
 * @see Blueprint
 * @since 1.0.0
 */
public abstract class CoreDrawerPresenter<S extends Blueprint> extends CorePresenter<S, CoreDrawerView> {

  private final DrawerPresenter drawerPresenter;

  private boolean hasDrawer = false;

  protected CoreDrawerPresenter(Parcer<Object> parcer, @ForApplication Context context, ActionBarPresenter actionBar,
                                DrawerPresenter drawerPresenter, ActivityPresenter activityPresenter) {

    super(parcer, context, actionBar, activityPresenter);
    this.drawerPresenter = drawerPresenter;
  }

  /**
   * Method returns instance of {@link Blueprint} which represents the Navigation Drawer
   * @return instance of {@link Blueprint} which represents the Navigation Drawer
   * @since 1.0.0
   */
  protected abstract S getDrawerScreen();

  @Override
  protected void showScreen(Blueprint newScreen, Flow.Direction flowDirection) {
    if (newScreen instanceof DrawerScreenBlueprint) {
      hasDrawer = true;
    } else {
      hasDrawer = false;
    }

    super.showScreen(newScreen, flowDirection);
    // Navigation Drawer settings
    drawerPresenter.setConfig(
            new DrawerPresenter.Config(hasDrawer, hasDrawer ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED));


    if (hasDrawer) {
      getView().showDrawer(getDrawerScreen());
    }
  }

  @Override protected boolean hasUp(Blueprint newScreen) {
    boolean hasUp = super.hasUp(newScreen);
    if (!hasUp && hasDrawer) {
      // if drawer is enabled and new screen is not instance of HasParent class so we still need that
      // hasUp will be enabled, otherwise is impossible to show navigation drawer on a first screen
      hasUp = true;
    }

    return hasUp;
  }
}
