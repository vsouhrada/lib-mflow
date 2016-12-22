package com.vsouhrada.android.lib.mflow.demo.functions.tab_indicator;


import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;

import flow.HasParent;


/**
 * @author vsouhrada
 */
public class TabScreen extends DrawerScreenBlueprint implements HasParent<FeaturesScreen> {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_tab_pager;
  }

  @Override
  public int getTitleResourceId() {
    return R.string.tab_pager_title;
  }

  @Override
  public String getMortarScopeName() {
    return TabScreen.class.getSimpleName();
  }

  @Override
  public FeaturesScreen getParent() {
    return new FeaturesScreen();
  }

  @Override
  public Object getDaggerModule() {
    return new Module();
  }

  @dagger.Module(
          injects = TabView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
