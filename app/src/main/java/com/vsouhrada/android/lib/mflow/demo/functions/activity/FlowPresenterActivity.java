package com.vsouhrada.android.lib.mflow.demo.functions.activity;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.screen.FlowActivityCoreScreen;
import com.vsouhrada.android.lib.mflow.ui.BaseFlowActionBarActivity;

import mortar.Blueprint;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see BaseFlowActionBarActivity
 * @since 1.0.0
 */
public class FlowPresenterActivity extends BaseFlowActionBarActivity {

  @Override
  protected Blueprint getBlueprint() {
    return new FlowActivityCoreScreen();
  }

  @Override
  protected int getContentView() {
    return R.layout.a_presenter_flow;
  }

}
