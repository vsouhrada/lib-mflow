package com.vsouhrada.android.lib.mflow.screen;

import mortar.Blueprint;


/**
 * This interface must be implemented by classes which are responsible for exchanges of drawer screens.
 *
 * @param <S> instance of screen which must extends {@link Blueprint}
 * @author vsouhrada
 * @version 1.0.0
 * @see Blueprint
 * @since 1.0.0
 */
public interface CanShowDrawer<S extends Blueprint> {

  /**
   * Calling this method the Navigation Drawer {@link android.support.v4.widget.DrawerLayout} will be shown
   *
   * @param screen current screen of the drawer layout
   * @since 1.0.0
   */
  void showDrawer(S screen);
}
