package com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.dialog.DemoDialogInfo;
import com.vsouhrada.android.lib.mflow.demo.functions.dialog.mvp.presenter.DialogDemoPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mortar.Mortar;
import mortar.Popup;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see LinearLayout
 * @since 1.1.0
 */
public class DialogDemoView extends LinearLayout {

  @Inject
  DialogDemoPresenter presenter;

  private DemoDialogPopup demoDialogAlertPopup;

  public DialogDemoView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    ButterKnife.inject(this);

    presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    presenter.dropView(this);

    super.onDetachedFromWindow();
  }

  public Popup<DemoDialogInfo, Boolean> getDemoDialogAlertPopup() {
    if (demoDialogAlertPopup == null) {
      demoDialogAlertPopup = new DemoDialogPopup(getContext());
    }

    return demoDialogAlertPopup;
  }

  @OnClick(R.id.show_alert_dialog_button)
  public void onShowAlertDialogButtonAction() {
    presenter.showAlertDialog();
  }
}
