package com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.view.MasterView;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;

import flow.HasParent;

/**
 * @author vsouhrada
 * @version 1.0.0
 * @see HasParent
 * @see FeaturesScreen
 * @since 1.0.0
 */
public class MasterScreen extends DrawerScreenBlueprint implements HasParent<FeaturesScreen> {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_save_state_master;
  }

  @Override
  public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override
  public Object getDaggerModule() {
    return new Module();
  }

  @Override
  public FeaturesScreen getParent() {
    return new FeaturesScreen();
  }

  @dagger.Module(
          injects = MasterView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
