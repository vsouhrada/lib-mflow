package com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.presenter.ChildPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see RelativeLayout
 * @since 1.0.0
 */
public class ChildView extends RelativeLayout {

  @InjectView(R.id.text) TextView enteredText;

  @Inject
  ChildPresenter presenter;

  public ChildView(Context context, AttributeSet attrs) {
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
    presenter.dropView(this);

    super.onDetachedFromWindow();
  }

  public void showText(String text) {
    if (enteredText != null) {
      enteredText.setText(text);
    }
  }
}
