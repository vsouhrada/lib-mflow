package com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.screen;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.view.DialogDemoView;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;
import flow.Layout;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see ScreenBlueprint
 * @see HasParent
 * @since 1.1.0
 */
@Layout(R.layout.v_dialog_demo)
public class DialogDemoScreen extends ScreenBlueprint implements HasParent<FeaturesScreen> {

  @Override public int getTitleResourceId() {
    return R.string.dialog_demo_abc_title;
  }

  @Override public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override public Object getDaggerModule() {
    return new Module();
  }

  @Override public FeaturesScreen getParent() {
    return new FeaturesScreen();
  }

  @dagger.Module(
          injects = DialogDemoView.class,
          addsTo = MainScreen.Module.class
  )
  public class Module {

  }
}
