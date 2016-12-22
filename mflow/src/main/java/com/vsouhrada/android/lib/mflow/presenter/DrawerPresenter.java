package com.vsouhrada.android.lib.mflow.presenter;

import android.os.Bundle;

import com.vsouhrada.android.lib.mflow.view.IPresenterView;

import mortar.Mortar;
import mortar.MortarScope;
import mortar.Presenter;


/**
 * Allows shared configuration of the Navigation Drawer.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see Presenter
 * @since 1.0.0
 */
public class DrawerPresenter extends Presenter<DrawerPresenter.View> {

  /**
   * This interface should be implemented by a view which is managed by this presenter
   *
   * @author vsouhrada
   * @version 1.0.0
   * @see IPresenterView
   * @since 1.0.0
   */
  public interface View extends IPresenterView {

    /**
     * Enable or disable the drawer indicator. The indicator defaults to enabled.
     * <p/>
     * <p>When the indicator is disabled, the <code>ActionBar</code> will revert to displaying
     * the home-as-up indicator provided by the <code>Activity</code>'s theme in the
     * <code>android.R.attr.homeAsUpIndicator</code> attribute instead of the animated
     * drawer glyph.</p>
     *
     * @param enable true to enable, false to disable
     * @since 1.0.0
     */
    void setDrawerIndicatorEnabled(boolean enable);

    /**
     * Enable or disable interaction with all drawers.
     * <p/>
     * <p>This allows the application to restrict the user's ability to open or close
     * any drawer within this layout. DrawerLayout will still respond to calls to
     * {@link android.support.v4.widget.DrawerLayout#openDrawer(int)}, {@link android.support.v4.widget.DrawerLayout#closeDrawer(int)}
     * and friends if a drawer is locked.</p>
     * <p/>
     * <p>Locking drawers open or closed will implicitly open or close
     * any drawers as appropriate.</p>
     *
     * @param lockMode The new lock mode for the given drawer. One of {@link android.support.v4.widget.DrawerLayout#LOCK_MODE_UNLOCKED},
     *                 {@link android.support.v4.widget.DrawerLayout#LOCK_MODE_LOCKED_CLOSED}
     *                 or {@link android.support.v4.widget.DrawerLayout#LOCK_MODE_LOCKED_OPEN}.
     * @see android.support.v4.widget.DrawerLayout
     * @since 1.0.0
     */
    void setDrawerLockMode(final int lockMode);

  }

  /**
   * Configuration of the action bar.
   *
   * @author vsouhrada
   * @version 1.0.0
   * @since 1.0.0
   */
  public static class Config {

    public final boolean isIndicatorEnabled;
    public final int lockMode;

    public Config(boolean isIndicatorEnabled, int lockMode) {
      this.isIndicatorEnabled = isIndicatorEnabled;
      this.lockMode = lockMode;
    }

  }

  private Config config;

  @Override protected MortarScope extractScope(View view) {
    return Mortar.getScope(view.getMortarContext());
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    if (config != null) {
      update();
    }
  }

  /**
   * Returns current configuration for this presenter.
   *
   * @return current configuration for this presenter
   * @since 1.0.0
   */
  public Config getConfig() {
    return config;
  }

  /**
   * Set up configuration
   *
   * @param config current instance of configuration for this presenter
   * @since 1.0.0
   */
  public void setConfig(Config config) {
    this.config = config;
    update();
  }

  private void update() {
    View view = getView();
    if (view == null) {
      return;
    }
    view.setDrawerIndicatorEnabled(config.isIndicatorEnabled);
    view.setDrawerLockMode(config.lockMode);
  }

}
