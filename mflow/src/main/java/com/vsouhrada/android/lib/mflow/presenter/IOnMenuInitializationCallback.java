package com.vsouhrada.android.lib.mflow.presenter;

/**
 * Callback interface which react on menu created action.
 *
 * @author jzaruba
 * @version 1.3.3
 * @since 1.3.3
 */
public interface IOnMenuInitializationCallback {

  /**
   * Called when action bar menu is created. This method is called first before onMenuPrepared()
   *
   * @since 1.3.3
   */
  void onMenuCreated();

  /**
   * Called when action bar menu is prepared. This method is called after onMenuCreated() and can be called multiple times.
   *
   * @since 1.3.3
   */
  void onMenuPrepared();

}
