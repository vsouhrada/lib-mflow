package com.vsouhrada.android.lib.mflow.demo.core.mvp.screen;

import com.vsouhrada.android.lib.mflow.ApplicationModule;
import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.presenter.MainPresenter;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.view.MainView;

import javax.inject.Singleton;

import dagger.Provides;
import flow.Flow;
import mortar.Blueprint;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see Blueprint
 * @since 1.0.0
 */
public class MainScreen implements Blueprint {

  @Override
  public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override
  public Object getDaggerModule() {
    return new Module();
  }

  @dagger.Module(
          injects = MainView.class,
          addsTo = ApplicationModule.class,
          library = true
  )
  public class Module {

    @Provides
    @MainScope
    @Singleton
    Flow provideFlow(MainPresenter presenter) {
      return presenter.getFlow();
    }

  }
}
