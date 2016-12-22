package com.vsouhrada.android.lib.mflow.screen;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsouhrada.android.lib.mflow.R;
import com.vsouhrada.android.lib.mflow.Views;

import flow.Flow;
import flow.Layout;
import flow.Layouts;
import mortar.Blueprint;
import mortar.Mortar;
import mortar.MortarScope;

import static android.view.animation.AnimationUtils.loadAnimation;


/**
 * A conductor that can swap subviews within a container view.
 * <p/>
 *
 * @param <S> the type of the screens that serve as a {@link Blueprint} for subview. Must
 *            be annotated with {@link Layout}, suitable for use with {@link Layouts#createView}.
 * @author vsouhrada
 * @version 1.1.0
 * @see Blueprint
 * @see android.widget.FrameLayout
 * @since 1.0.0
 */
public class ScreenConductor<S extends Blueprint> implements CanShowScreen<S>, CanShowDrawer<S> {

  // Using static view ids to find and replace core layouts
  private final static int drawerViewId = Views.generateViewId();
  private final static int contentViewId = Views.generateViewId();

  private final Context context;
  private final ViewGroup container;

  /**
   * Construct a new {@link ScreenConductor} instance.
   *
   * @param context   current {@link Context}
   * @param container view container
   * @since 1.0.0
   */
  public ScreenConductor(Context context, ViewGroup container) {
    this.context = context;
    this.container = container;
  }

  @Override
  public void showScreen(S screen, Flow.Direction direction) {
    MortarScope myScope = Mortar.getScope(context);
    MortarScope newChildScope = myScope.requireChild(screen);

    View oldChild = getContentView();

    destroyOldScope(screen, oldChild);

    // Create the new child.
    Context childContext = newChildScope.createContext(context);
    View newChild;

    if (!hasScreenLayoutAnnotation(screen) && screen instanceof IScreenBlueprint) {
      IScreenBlueprint screenBlueprint = (IScreenBlueprint) screen;
      newChild = LayoutInflater.from(childContext).inflate(screenBlueprint.getLayoutResourceId(), null);
    } else {
      newChild = Layouts.createView(childContext, screen);
    }

    newChild.setId(contentViewId);

    setAnimation(direction, oldChild, newChild);

    // Out with the old, in with the new.
    if (oldChild != null) {
      container.removeView(oldChild);
    }
    container.addView(newChild);
  }

  @Override public void showDrawer(S screen) {
    View oldChild = container.findViewById(drawerViewId);

    if (destroyOldScope(screen, oldChild)) {
      View newChild = createNewChildView(screen, drawerViewId);

      if (oldChild != null) {
        container.removeView(oldChild);
      }

      int drawerWithDimResId = R.dimen.navigation_drawer_width;
      if (screen instanceof DrawerScreenBlueprint) {
        drawerWithDimResId = ((DrawerScreenBlueprint) screen).getDrawerWidthDimensionResId();
      }

      // Set some basic layout parameters so the drawer works
      DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
              context.getResources().getDimensionPixelSize(drawerWithDimResId),
              ViewGroup.LayoutParams.MATCH_PARENT
      );
      params.gravity = Gravity.LEFT;
      newChild.setLayoutParams(params);

      container.addView(newChild, 1);
    }
  }

  /**
   * Destroys old child scope if it was different than the new one. Returns true
   * if successful
   *
   * @since 1.0.0
   */
  protected boolean destroyOldScope(S screen, View oldChild) {
    MortarScope myScope = Mortar.getScope(context);
    if (oldChild != null) {
      MortarScope oldChildScope = Mortar.getScope(oldChild.getContext());
      if (oldChildScope.getName().equals(screen.getMortarScopeName())) {
        return false;
      }
      myScope.destroyChild(oldChildScope);
    }

    return true;
  }

  /**
   * Creates a new child View from a given screen and sets its view Id
   *
   * @since 1.0.0
   */
  protected View createNewChildView(S screen, final int viewId) {
    MortarScope myScope = Mortar.getScope(context);
    MortarScope newChildScope = myScope.requireChild(screen);

    Context childContext = newChildScope.createContext(context);

    View newChild;

    if (!hasScreenLayoutAnnotation(screen) && screen instanceof IScreenBlueprint) {
      IScreenBlueprint screenBlueprint = (IScreenBlueprint) screen;
      newChild = LayoutInflater.from(childContext).inflate(screenBlueprint.getLayoutResourceId(), null);
    } else {
      newChild = Layouts.createView(childContext, screen);
    }

    newChild.setId(viewId);

    return newChild;
  }

  /**
   * Set animation between old and new screens(Views)
   *
   * @param direction for animation (@link Flow.Direction}
   * @param oldChild  old view/screen
   * @param newChild  new view/screen
   * @since 1.0.0
   */
  protected void setAnimation(Flow.Direction direction, View oldChild, View newChild) {
    if (oldChild == null) return;

    int out = direction == Flow.Direction.FORWARD ? R.anim.slide_out_left : R.anim.slide_out_right;
    int in = direction == Flow.Direction.FORWARD ? R.anim.slide_in_right : R.anim.slide_in_left;

    oldChild.setAnimation(loadAnimation(context, out));
    newChild.setAnimation(loadAnimation(context, in));
  }

  /**
   * Method returns parent view - content view for all subviews.
   *
   * @return content view - parent view or in other word - return view container
   * @since 1.0.0
   */
  private View getContentView() {
    return container.findViewById(contentViewId);
  }

  /**
   * Returns true in case of that annotation {@link Layout} is used in a screen for layout definition, otherwise false is returned.
   *
   * @param screen current instance of screen
   * @param <S>    current screen
   * @return true in case of that annotation {@link Layout} is used in a screen for layout definition, otherwise false is returned
   * @since 1.1.0
   */
  protected <S> boolean hasScreenLayoutAnnotation(S screen) {
    if (screen != null) {
      return screen.getClass().getAnnotation(Layout.class) != null;
    }

    return false;
  }

}