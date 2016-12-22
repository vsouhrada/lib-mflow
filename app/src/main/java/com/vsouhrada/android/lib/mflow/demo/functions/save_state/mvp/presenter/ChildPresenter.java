package com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.presenter;

import android.os.Bundle;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.screen.EnteredText;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.view.ChildView;

import javax.inject.Inject;

import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter
 * @see ChildView
 * @since 1.0.0
 */
public class ChildPresenter extends ViewPresenter<ChildView> {

  private String enteredText;

  @Inject
  public ChildPresenter(@EnteredText String enteredText) {
    this.enteredText = enteredText;
  }

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    getView().showText(enteredText);
  }
}
