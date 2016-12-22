package com.vsouhrada.android.lib.mflow.demo.core.mvp.screen;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.annotations.DrawerAdapter;
import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.view.DrawerView;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;

import dagger.Provides;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see DrawerScreenBlueprint
 * @since 1.0.0
 */
public class DrawerScreen extends DrawerScreenBlueprint {

  @Override public int getLayoutResourceId() {
    return R.layout.drawer;
  }

  @Override public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override public Object getDaggerModule() {
    return new Module();
  }

  @dagger.Module(
          injects = DrawerView.class,
          addsTo = MainScreen.Module.class,
          library = true
  )

  public class Module {

    @Provides
    @DrawerAdapter
    public ArrayAdapter<String> provideAdapter(@ForApplication Context context) {
      return new ArrayAdapter<>(
              context,
              android.R.layout.simple_list_item_activated_1,
              android.R.id.text1,
              context.getResources().getStringArray(R.array.drawer_items)
      );
    }

  }

}