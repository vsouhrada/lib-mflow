package com.vsouhrada.android.lib.mflow.ui;

import com.vsouhrada.android.lib.mflow.screen.CanShowScreen;

import flow.Flow;
import mortar.Blueprint;


/**
 * This is an interface which should be implemented by the Core Presenter implementation.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see CanShowScreen
 * @see Blueprint
 * @since 1.0.0
 */
public interface ICoreView extends CanShowScreen<Blueprint> {

  /**
   * Returns current Flow of the application
   *
   * @return current Flow of the application
   * @since 1.0.0
   */
  Flow getFlow();

}