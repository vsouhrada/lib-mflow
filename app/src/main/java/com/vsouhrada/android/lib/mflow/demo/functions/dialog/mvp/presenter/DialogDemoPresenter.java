package com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.dialog.DemoDialogInfo;
import com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.view.DialogDemoView;

import javax.inject.Inject;

import mortar.PopupPresenter;
import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see ViewPresenter
 * @see DialogDemoView
 * @since 1.1.0
 */
public class DialogDemoPresenter extends ViewPresenter<DialogDemoView> {

  private final Context context;

  private PopupPresenter<DemoDialogInfo, Boolean> demoDialogPopupPresenter;

  @Inject
  public DialogDemoPresenter(@ForApplication final Context context) {
    this.context = context;
    demoDialogPopupPresenter = new PopupPresenter<DemoDialogInfo, Boolean>() {

      @Override protected void onPopupResult(Boolean result) {
        if (result != null) {
          if (result) {
            Toast.makeText(context, ":)", Toast.LENGTH_SHORT).show();
          } else {
            Toast.makeText(context, ":(", Toast.LENGTH_SHORT).show();
          }
        }
      }
    };
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);

    demoDialogPopupPresenter.takeView(getView().getDemoDialogAlertPopup());
  }

  @Override public void dropView(DialogDemoView view) {
    if (demoDialogPopupPresenter != null) {
      demoDialogPopupPresenter.dropView(getView().getDemoDialogAlertPopup());
    }

    super.dropView(view);
  }

  public void showAlertDialog() {
    demoDialogPopupPresenter.show(new DemoDialogInfo(context.getString(R.string.dialog_demo_title),
            context.getString(R.string.dialog_demo_message)));
  }
}
