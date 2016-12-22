package com.vsouhrada.android.lib.mflow.presenter;

import android.content.Context;
import android.view.View;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.screen.CanShowScreen;
import com.vsouhrada.android.lib.mflow.screen.HasCustomParent;
import com.vsouhrada.android.lib.mflow.screen.IScreenBlueprint;
import com.vsouhrada.android.lib.mflow.view.CoreView;

import flow.Flow;
import flow.HasParent;
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
 * @version 1.2.1
 * @see FlowPresenter
 * @see CoreView
 * @see Blueprint
 * @since 1.0.
 */
public abstract class CorePresenter<S extends Blueprint, V extends View & CanShowScreen<Blueprint>> extends FlowPresenter<Blueprint, V> {

  protected final Context context;
  protected final ActionBarPresenter actionBarPresenter;
  protected final ActivityPresenter activityPresenter;

  protected CorePresenter(Parcer<Object> parcer, @ForApplication Context context, ActionBarPresenter actionBarPresenter,
                          ActivityPresenter activityPresenter) {

    super(parcer);
    this.context = context;
    this.actionBarPresenter = actionBarPresenter;
    this.activityPresenter = activityPresenter;
  }

  /**
   * Return true in case of that application using the ActionBar pattern otherwise false is returned.
   *
   * @return true in case of that application using the ActionBar pattern otherwise false is returned.
   * @since 1.0.0
   */
  public boolean isActionBarEnabled() {
    return true;
  }

  @Override
  protected void showScreen(Blueprint newScreen, Flow.Direction flowDirection) {
    boolean hasUp = hasUp(newScreen);
    boolean isHomeButtonEnabled = false;
    CharSequence title = null;
    CharSequence subtitle = null;
    int menuResourceId = IScreenBlueprint.NO_RESOURCE;

    if (newScreen instanceof IScreenBlueprint) {
      IScreenBlueprint screenBlueprint = (IScreenBlueprint) newScreen;
      title = getTitle(screenBlueprint);
      isHomeButtonEnabled = screenBlueprint.isHomeButtonEnabled();
      // Set subtitle
      int subtitleResId = screenBlueprint.getSubtitleResourceId();
      if (subtitleResId != IScreenBlueprint.NO_RESOURCE) {
        subtitle = context.getString(subtitleResId);
      }
      menuResourceId = screenBlueprint.getMenuResourceId();
    }

    actionBarPresenter.clearOnMenuCreatedListeners();

    if (isActionBarEnabled()) {
      if (menuResourceId != IScreenBlueprint.NO_RESOURCE) {
        actionBarPresenter.setConfig(new ActionBarPresenter.Config(menuResourceId, isHomeButtonEnabled, hasUp, title, subtitle));
      } else {
        actionBarPresenter.setConfig(new ActionBarPresenter.Config(isHomeButtonEnabled, hasUp, title, subtitle, null));
      }
    }
    // Need to reset previous state of activity presenter. For instance onBackPressedAction must be set to null!
    activityPresenter.resetStateBeforeNewScreen();

    super.showScreen(newScreen, flowDirection);
  }

  /**
   * Returns title of the current {@link IScreenBlueprint}.
   *
   * @param screen the current screen
   * @return title of the current {@link IScreenBlueprint}.
   * @since 1.0.0
   */
  public CharSequence getTitle(IScreenBlueprint screen) {
    int titleResId = screen.getTitleResourceId();
    if (titleResId != IScreenBlueprint.NO_RESOURCE) {
      return context.getString(titleResId);
    }

    return null;
  }

  /**
   * Return true in case of that newScreen contains UP navigation button otherwise false is returned.
   *
   * @param newScreen instance of screen on which will be checked if has UP button (UP navigation in Navigation Drawer)
   * @return true in case of that newScreen contains UP navigation button otherwise false is returned.
   * @since 1.0.0
   */
  protected boolean hasUp(Blueprint newScreen) {
    return (newScreen instanceof HasParent) || (newScreen instanceof HasCustomParent);
  }
}
