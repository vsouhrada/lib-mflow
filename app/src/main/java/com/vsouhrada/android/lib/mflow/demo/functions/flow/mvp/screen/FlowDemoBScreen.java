package com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.view.FlowDemoBView;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;


/**
 * @author vsouhrada
 * @version 1.2.0
 * @since 1.2.0
 */
public class FlowDemoBScreen extends ScreenBlueprint implements HasParent<FlowDemoAScreen> {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_flow_demo_b;
  }

  @Override public int getTitleResourceId() {
    return R.string.flow_demo_b_title;
  }

  @Override public String getMortarScopeName() {
    return getClass().getSimpleName();
  }

  @Override public Object getDaggerModule() {
    return new Module();
  }

  @Override public FlowDemoAScreen getParent() {
    return new FlowDemoAScreen();
  }

  @dagger.Module(
          injects = FlowDemoBView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
