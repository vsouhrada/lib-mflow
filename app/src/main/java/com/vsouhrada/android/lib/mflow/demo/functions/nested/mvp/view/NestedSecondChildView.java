package com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.presenter.NestedSecondChildPresenter;
import com.vsouhrada.android.lib.mflow.ui.INestedView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see RelativeLayout
 * @see INestedView
 * @see NestedSecondChildPresenter
 * @since 1.0.0
 */
public class NestedSecondChildView extends RelativeLayout implements INestedView<NestedSecondChildPresenter> {

  @InjectView(R.id.message_text_view)
  TextView messageTextView;

  @Inject
  NestedSecondChildPresenter presenter;

  public NestedSecondChildView(Context context, AttributeSet attrs) {
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
  public NestedSecondChildPresenter getPresenter() {
    return presenter;
  }

  @Override
  public void setPresenter(NestedSecondChildPresenter newPresenter) {
    if (presenter != null) {
      presenter.dropView(this);
    }

    presenter = newPresenter;
    presenter.takeView(this);
  }

  public void showMessage(CharSequence text) {
    messageTextView.setText(text);
  }

}
