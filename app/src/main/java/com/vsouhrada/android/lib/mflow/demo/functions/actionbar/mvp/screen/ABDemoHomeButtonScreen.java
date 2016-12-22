package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen;


import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoHomeButtonView;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @see HasParent<ABDemoMainScreen>
 * @since 1.0.0
 */
public class ABDemoHomeButtonScreen extends ScreenBlueprint implements HasParent<ABDemoMainScreen> {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_ab_demo_home_button;
  }

  @Override
  public int getTitleResourceId() {
    return R.string.ab_demo_home_btn_title;
  }

  @Override
  public int getSubtitleResourceId() {
    return R.string.ab_demo_main_title;
  }

  @Override
  public boolean isHomeButtonEnabled() {
    return false;
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
  public ABDemoMainScreen getParent() {
    return new ABDemoMainScreen();
  }

  @dagger.Module(
          injects = ABDemoHomeButtonView.class,
          addsTo = MainScreen.Module.class
  )
  class Module {

  }
}
