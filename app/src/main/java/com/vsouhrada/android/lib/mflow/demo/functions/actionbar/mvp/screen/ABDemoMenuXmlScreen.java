package com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.actionbar.mvp.view.ABDemoMenuXmlView;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @see HasParent<ABDemoMainScreen>
 * @since 1.0.0
 */
public class ABDemoMenuXmlScreen extends ScreenBlueprint implements HasParent<ABDemoMainScreen> {

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_ab_demo_menu;
  }

  @Override
  public int getTitleResourceId() {
    return R.string.ab_demo_menu_xml_title;
  }

  @Override
  public int getMenuResourceId() {
    return R.menu.menu_main;
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
          injects = ABDemoMenuXmlView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
