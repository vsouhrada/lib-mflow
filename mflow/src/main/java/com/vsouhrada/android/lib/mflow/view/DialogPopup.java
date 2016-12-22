package com.vsouhrada.android.lib.mflow.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import mortar.Popup;
import mortar.PopupPresenter;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see Parcelable
 * @see Popup
 * @since 1.1.0
 */
public abstract class DialogPopup<D extends Parcelable, R> implements Popup<D, R> {

  protected final Context context;

  protected Dialog dialog;

  protected PopupPresenter<D, R> presenter;

  public DialogPopup(Context context) {
    this.context = context;
  }

  @Override public void show(D info, boolean withFlourish, PopupPresenter<D, R> presenter) {
    this.presenter = presenter;

    if (isShowing()) {
      /**
       * Sometimes you want to put the dialog right back up right as it is dismissed.
       * In such a case the outgoing dialog might not quite have been torn down yet.
       * Help the process along.
       */
      dialog.dismiss();
      dialog = null;
    }
    dialog = createDialog(info, withFlourish, presenter);
    dialog.show();
  }

  @Override public boolean isShowing() {
    return dialog != null && dialog.isShowing();
  }

  @Override public void dismiss(boolean withFlourish) {
    dialog.dismiss();
    dialog = null;
  }

  @Override public Context getContext() {
    return context;
  }

  protected abstract Dialog createDialog(D info, boolean withFlourish, final PopupPresenter<D, R> presenter);

}