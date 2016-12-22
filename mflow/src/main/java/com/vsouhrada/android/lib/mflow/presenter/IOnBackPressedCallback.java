package com.vsouhrada.android.lib.mflow.presenter;

/**
 * Callback interface which react on backPressed Action.
 *
 * @author vsouhrada
 * @version 1.3.0
 * @since 1.3.0
 */
public interface IOnBackPressedCallback {

  /**
   * Called when the activity has detected the user's press of the back key.
   *
   * @return true if you want to handle action yourself, otherwise false is returned
   * @since 1.3.0
   */
  boolean onBackPressed();

}
