package com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.presenter.NestedFirstChildPresenter;
import com.vsouhrada.android.lib.mflow.ui.INestedView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see RelativeLayout
 * @see INestedView
 * @see NestedFirstChildPresenter
 * @since 1.0.0
 */
public class NestedFirstChildView extends RelativeLayout implements INestedView<NestedFirstChildPresenter> {

  @InjectView(R.id.entered_msg)
  EditText enteredMessageEditText;

  @Inject
  NestedFirstChildPresenter presenter;

  public NestedFirstChildView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    ButterKnife.inject(this);

    presenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();

    presenter.dropView(this);
  }

  @Override
  public NestedFirstChildPresenter getPresenter() {
    return presenter;
  }

  @Override
  public void setPresenter(NestedFirstChildPresenter presenter) {
    if (this.presenter != null) {
      this.presenter.dropView(this);
    }

    this.presenter = presenter;
    this.presenter.takeView(this);
  }

  @OnClick(R.id.send_button)
  public void onSendButtonAction() {
    presenter.sendMessage(enteredMessageEditText.getText().toString());
  }
}
