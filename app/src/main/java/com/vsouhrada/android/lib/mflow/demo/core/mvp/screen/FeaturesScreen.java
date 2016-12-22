package com.vsouhrada.android.lib.mflow.demo.core.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.view.FeaturesOverview;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @since 1.0.0
 */
public class FeaturesScreen extends DrawerScreenBlueprint {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_features_overview;
  }

  @Override
  public int getTitleResourceId() {
    return R.string.features_ab_title;
  }

  @Override
  public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override
  public Object getDaggerModule() {
    return new Module();
  }

  @dagger.Module(
          injects = FeaturesOverview.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
