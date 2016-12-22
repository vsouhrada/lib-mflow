package com.vsouhrada.android.lib.mflow.screen;

import mortar.Blueprint;


/**
 * <p>Defines a scope to be built via {@link mortar.MortarScope#requireChild(Blueprint)} or
 * {@link mortar.Mortar#requireActivityScope(mortar.MortarScope, Blueprint)}.</p>
 * <p>By using this interface you have possibility to set up layout resource in a library project (annotation are not possible in library
 * project), next you can define title and subtitle for the actionbar and enable or disable home button.</p>
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see Blueprint
 * @since 1.0.0
 */
public interface IScreenBlueprint extends Blueprint {

  /**
   * @since 1.0.0
   */
  public static final int NO_RESOURCE = 0;

  /**
   * Returns an unique layout identifier.
   *
   * @return
   * @since 1.0.0
   */
  public int getLayoutResourceId();

  /**
   * Returns an unique title identifier for the ActionBar
   *
   * @return an unique title identifier for the ActionBar
   * @since 1.0.0
   */
  public int getTitleResourceId();

  /**
   * Returns an unique subtitle identifier for the ActionBar
   *
   * @return an unique subtitle identifier for the ActionBar
   * @since 1.0.0
   */
  public int getSubtitleResourceId();

  /**
   * Returns true in case of that you want to show a home button on the ActionBar.
   *
   * @return true in case of that you want to show a home button on the ActionBar.
   * @since 1.0.0
   */
  public boolean isHomeButtonEnabled();

  /**
   * Returns an unique menu identifier
   *
   * @return an unique menu identifier
   * @since 1.0.0
   */
  public int getMenuResourceId();

}