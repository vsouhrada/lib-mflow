package com.vsouhrada.android.lib.mflow;

import android.os.Build;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * An utility class for views.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Views {

  private Views() {}

  private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

  /**
   * Generate a value suitable for use in your view.
   * This value will not collide with ID values generated at build time by aapt for R.id.
   *
   * @return a generated ID value
   * @since 1.0.0
   */
  public static int generateViewId() {
    if (Build.VERSION.SDK_INT < 17) {
      for (; ; ) {
        final int result = sNextGeneratedId.get();
        // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
        int newValue = result + 1;
        if (newValue > 0x00FFFFFF)
          newValue = 1; // Roll over to 1, not 0.
        if (sNextGeneratedId.compareAndSet(result, newValue)) {
          return result;
        }
      }
    } else {
      return View.generateViewId();
    }
  }
}
