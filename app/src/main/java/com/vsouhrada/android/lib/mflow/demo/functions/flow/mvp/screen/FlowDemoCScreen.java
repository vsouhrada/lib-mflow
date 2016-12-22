package com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen;


import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.view.FlowDemoCView;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;
import flow.Layout;


/**
 * @author vsouhrada
 * @version 1.2.0
 * @see ScreenBlueprint
 * @see HasParent
 * @since 1.2.0
 */
@Layout(R.layout.v_flow_demo_c)
public class FlowDemoCScreen extends ScreenBlueprint implements HasParent<FlowDemoBScreen> {

  @Override public int getTitleResourceId() {
    return R.string.flow_demo_c_title;
  }

  @Override public String getMortarScopeName() {
    return getClass().getSimpleName();
  }

  @Override public Object getDaggerModule() {
    return new Module();
  }

  @Override public FlowDemoBScreen getParent() {
    return new FlowDemoBScreen();
  }

  @dagger.Module(
          injects = FlowDemoCView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
