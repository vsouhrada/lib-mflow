package com.vsouhrada.android.lib.mflow.demo.core;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.ui.BaseFlowActionBarActivity;

import mortar.Blueprint;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see BaseFlowActionBarActivity
 * @since 1.0.0
 */
public class MainActivity extends BaseFlowActionBarActivity {

  @Override
  protected Blueprint getBlueprint() {
    return new MainScreen();
  }

  @Override
  protected int getContentView() {
    return R.layout.a_main;
  }

}