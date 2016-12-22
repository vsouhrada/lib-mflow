package com.vsouhrada.android.lib.mflow.view;

import android.content.Context;
import mortar.MortarScope;


/**
 * <p>
 * This is a base view for all presenters which goal communication and handling with standard android classes like Activities, ActionBar,
 * ..., and so on.
 * </p>
 * <p>
 * Each "Handler" presenter should define own interface which will extends this interface.
 * </p>
 *
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IPresenterView {


  /**
   * Return current {@link Context} for the given scope
   *
   * @return current {@link Context} for the given scope
   * @since 1.0.0
   */
  Context getMortarContext();

  /**
   * Return current instance of the {@link MortarScope}.
   *
   * @return current instance of the {@link MortarScope}.
   * @since 1.0.0
   */
  MortarScope getMortarScope();

}
