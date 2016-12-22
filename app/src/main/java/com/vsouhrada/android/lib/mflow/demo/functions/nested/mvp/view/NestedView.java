package com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.functions.nested.mvp.presenter.NestedPresenter;

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
public class NestedView extends RelativeLayout {

  @InjectView(R.id.first_nested_child)
  NestedFirstChildView nestedFirstChildView;
  @InjectView(R.id.second_nested_child)
  NestedSecondChildView nestedSecondChildView;

  @Inject
  NestedPresenter nestedPresenter;

  public NestedView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    ButterKnife.inject(this);

    nestedPresenter.setNestedFirstChildPresenter(nestedFirstChildView.getPresenter());
    nestedPresenter.setNestedSecondChildPresenter(nestedSecondChildView.getPresenter());

    nestedPresenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();

    nestedPresenter.dropView(this);
  }
}
