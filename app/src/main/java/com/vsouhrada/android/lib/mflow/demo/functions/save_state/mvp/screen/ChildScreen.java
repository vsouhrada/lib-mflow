package com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.view.ChildView;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import dagger.Provides;
import flow.HasParent;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @see HasParent<MasterScreen>
 * @since 1.0.0
 */
public class ChildScreen extends ScreenBlueprint implements HasParent<MasterScreen> {

  private String text;

  public ChildScreen(String text) {
    this.text = text;
  }

  @Override
  public int getLayoutResourceId() {
    return R.layout.v_save_state_child;
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
  public MasterScreen getParent() {
    return new MasterScreen();
  }

  @dagger.Module(
          injects = ChildView.class,
          addsTo = MainScreen.Module.class
  )

  public class Module {

    @Provides
    @EnteredText
    public String provideTextToShow() {
      return text;
    }

  }
}
