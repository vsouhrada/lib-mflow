package com.vsouhrada.android.lib.mflow.screen;

import com.vsouhrada.android.lib.mflow.R;

/**
 * Defines a scope to be built via {@link mortar.MortarScope} for the Navigation Drawer pattern.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @since 1.0.0
 */
public abstract class DrawerScreenBlueprint extends ScreenBlueprint {

  /**
   * Return width of the {@link android.support.v4.widget.DrawerLayout}.
   *
   * @return width of the {@link android.support.v4.widget.DrawerLayout}
   * @see <a href="http://developer.android.com/training/implementing-navigation/nav-drawer.html">Implementing Navigation Dawer</a>
   * @since 1.0.0
   */
  public int getDrawerWidthDimensionResId() {
    return R.dimen.navigation_drawer_width;
  }

}
