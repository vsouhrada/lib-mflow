package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoCustomParentView;
import com.vsouhrada.android.lib.mflow.screen.HasCustomParent;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.Layout;

/**
 * @author vsouhrada
 * @version 1.1.0
 * @see ScreenBlueprint
 * @see HasCustomParent
 * @since 1.1.0
 */
@Layout(R.layout.v_ab_demo_custom_parent)
public class ABDemoCustomParentScreen extends ScreenBlueprint implements HasCustomParent {

  // If you implements HasCustomParent so you're now responsible for correct UP navigation
  // which you can do in your presenter class

  @Override public int getTitleResourceId() {
    return R.string.ab_demo_up_custom_parent_title;
  }

  @Override public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override public Object getDaggerModule() {
    return new Module();
  }

  @dagger.Module(
          injects = ABDemoCustomParentView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
