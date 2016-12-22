package com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.view.FlowActivityView;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @since 1.0.0
 */
public class FlowActivityScreen extends ScreenBlueprint {

  @Override public int getLayoutResourceId() {
    return R.layout.v_flow_activity;
  }

  @Override public int getTitleResourceId() {
    return R.string.activity_flow_title;
  }

  @Override public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override public Object getDaggerModule() {
    return new Module();
  }

  @dagger.Module(
          injects = FlowActivityView.class,
          addsTo = FlowActivityCoreScreen.Module.class
  )
  public class Module {

  }
}
