package com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.vsouhrada.android.lib.mflow.demo.functions.dialog.DemoDialogInfo;
import com.vsouhrada.android.lib.mflow.view.DialogPopup;

import mortar.PopupPresenter;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see DialogPopup
 * @since 1.1.0
 */
public class DemoDialogPopup extends DialogPopup<DemoDialogInfo, Boolean> {

  public DemoDialogPopup(Context context) {
    super(context);
  }

  @Override protected Dialog createDialog(DemoDialogInfo info, boolean withFlourish, final PopupPresenter<DemoDialogInfo, Boolean> presenter) {
    dialog = new AlertDialog.Builder(context).setTitle(info.title)
            .setMessage(info.message)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

              @Override public void onClick(DialogInterface d, int which) {
                dialog = null;
                presenter.onDismissed(true);
              }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {

              @Override public void onClick(DialogInterface d, int which) {
                dialog = null;
                presenter.onDismissed(false);
              }
            })
            .setCancelable(true)
            .setOnCancelListener(new DialogInterface.OnCancelListener() {

              @Override public void onCancel(DialogInterface d) {
                dialog = null;
                presenter.onDismissed(false);
              }
            })
            .show();

    return dialog;
  }
}
