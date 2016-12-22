package com.vsouhrada.android.lib.mflow.screen;


/**
 * Defines a scope to be built via {@link mortar.MortarScope}.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see IScreenBlueprint
 * @since 1.0.0
 */
public abstract class ScreenBlueprint implements IScreenBlueprint {

  @Override public int getLayoutResourceId() {
    return NO_RESOURCE;
  }

  @Override
  public int getTitleResourceId() {
    return NO_RESOURCE;
  }

  @Override
  public int getSubtitleResourceId() {
    return NO_RESOURCE;
  }

  @Override
  public boolean isHomeButtonEnabled() {
    return true;
  }

  @Override
  public int getMenuResourceId() {
    return NO_RESOURCE;
  }

  @Override public boolean equals(Object o) {
    return this == o || getClass().getName().equals(o.getClass().getName());
  }
}