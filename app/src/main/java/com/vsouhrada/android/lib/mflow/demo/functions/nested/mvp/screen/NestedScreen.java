package com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.screen;


import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view.NestedFirstChildView;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view.NestedSecondChildView;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view.NestedView;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @see HasParent<FeaturesScreen>
 * @since 1.0.0
 */
public class NestedScreen extends DrawerScreenBlueprint implements HasParent<FeaturesScreen> {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_nested_parent;
  }

  @Override
  public int getTitleResourceId() {
    return R.string.nested_view_title;
  }

  @Override
  public String getMortarScopeName() {
    return getClass().getName();
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
          injects = {
                  NestedView.class,
                  NestedFirstChildView.class,
                  NestedSecondChildView.class
          },
          addsTo = MainScreen.Module.class
  )
  class Module {

  }

}
