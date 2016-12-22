package com.vsouhrada.android.lib.mflow;

import android.app.Application;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Modules {

  public static Object[] list(Application app) {
    return new Object[]{
            new ApplicationModule(app)
    };
  }

  private Modules() {
    // No instances.
  }
}
