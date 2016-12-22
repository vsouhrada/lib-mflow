package com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.presenter;


import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.screen.ChildScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.view.MasterView;

import javax.inject.Inject;

import flow.Flow;
import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter
 * @see MasterView
 * @since 1.0.0
 */
public class MasterPresenter extends ViewPresenter<MasterView> {

  private Flow flow;

  @Inject
  public MasterPresenter(@MainScope Flow flow) {
    this.flow = flow;
  }

  public void forward(String text) {
    flow.goTo(new ChildScreen(text));
  }

}
