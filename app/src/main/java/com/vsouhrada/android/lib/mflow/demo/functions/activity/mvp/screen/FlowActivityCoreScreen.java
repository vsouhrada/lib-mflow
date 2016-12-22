package com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.screen;

import com.vsouhrada.android.lib.mflow.ApplicationModule;
import com.vsouhrada.android.lib.mflow.annotations.MainScope;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.presenter.FlowActivityCorePresenter;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.view.FlowActivityCoreView;

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
public class FlowActivityCoreScreen implements Blueprint {

  @Override public String getMortarScopeName() {
    return getClass().getName();
  }

  @Override public Object getDaggerModule() {
    return new Module();
  }

  @dagger.Module(
          injects = FlowActivityCoreView.class,
          addsTo = ApplicationModule.class,
          library = true
  )
  public static class Module {

    @Provides
    @MainScope
    @Singleton Flow provideFlow(FlowActivityCorePresenter presenter) {
      return presenter.getFlow();
    }

  }
}
