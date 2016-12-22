package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen;


import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoMainView;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see HasParent
 * @see ScreenBlueprint
 * @see HasParent<FeaturesScreen>
 * @since 1.0.0
 */
public class ABDemoMainScreen extends DrawerScreenBlueprint implements HasParent<FeaturesScreen> {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_ab_demo_main;
  }

  @Override
  public int getTitleResourceId() {
    return R.string.ab_demo_main_title;
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
          injects = ABDemoMainView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
