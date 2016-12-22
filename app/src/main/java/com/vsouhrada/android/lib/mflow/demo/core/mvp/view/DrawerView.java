package com.vsouhrada.android.lib.mflow.demo.core.mvp.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vsouhrada.android.lib.mflow.demo.core.mvp.presenter.DrawerViewPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import mortar.Mortar;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ListView
 * @since 1.0.0
 */
public class DrawerView extends ListView {

  @Inject
  DrawerViewPresenter presenter;

  public int currentSelectedPosition = 0;

  public DrawerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Mortar.inject(context, this);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    ButterKnife.inject(this);

    setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
      }
    });

    presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    presenter.dropView(this);

    super.onDetachedFromWindow();
  }

  public void setListAdapter(ArrayAdapter<String> adapter) {
    setAdapter(adapter);
    setItemChecked(currentSelectedPosition, true);
  }

  private void selectItem(int position) {
    currentSelectedPosition = position;
    setItemChecked(position, true);

    if (getParent() != null && getParent() instanceof DrawerLayout) {
      ((DrawerLayout) getParent()).closeDrawer(this);
      presenter.goToScreenAtPosition(position);
    }
  }
}
