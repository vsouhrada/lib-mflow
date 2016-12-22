package com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.save_state.mvp.presenter.MasterPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see RelativeLayout
 * @since 1.0.0
 */
public class MasterView extends RelativeLayout {

  @InjectView(R.id.entered_text) EditText enteredEditText;

  @Inject
  MasterPresenter presenter;

  public MasterView(Context context, AttributeSet attrs) {
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

  @OnClick(R.id.new_screen_button)
  public void onShowNextScreenButtonClick() {
    presenter.forward(enteredEditText.getText().toString());
  }
}
