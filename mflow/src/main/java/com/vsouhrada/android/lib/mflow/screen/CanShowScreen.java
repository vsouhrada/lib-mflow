package com.vsouhrada.android.lib.mflow.screen;

import flow.Flow;
import mortar.Blueprint;


/**
 * This interface must be implemented by classes which are responsible for exchanges of screens.
 *
 * @param <S> instance of screen which must extends {@link Blueprint}
 * @author vsouhrada
 * @version 1.0.0
 * @see Blueprint
 * @since 1.0.0
 */
public interface CanShowScreen<S extends Blueprint> {

  /**
   * Calling this method a new screen will be shown
   *
   * @param screen    current screen which will be shown
   * @param direction flow direction - important for transition animation
   * @since 1.0.0
   */
  void showScreen(S screen, Flow.Direction direction);

}