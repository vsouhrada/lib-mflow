package com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.presenter;

import android.os.Bundle;

import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view.NestedSecondChildView;

import javax.inject.Inject;

import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ViewPresenter
 * @see NestedSecondChildView
 * @since 1.0.0
 */
public class NestedSecondChildPresenter extends ViewPresenter<NestedSecondChildView> {

  @Inject
  public NestedSecondChildPresenter() {

  }

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);
  }

  public void showMessage(CharSequence message) {
    if (getView() != null) {
      getView().showMessage(message);
    }
  }
}
