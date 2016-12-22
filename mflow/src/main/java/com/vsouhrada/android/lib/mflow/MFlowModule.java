package com.vsouhrada.android.lib.mflow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vsouhrada.android.lib.mflow.parcer.GsonParcer;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;
import com.vsouhrada.android.lib.mflow.presenter.DrawerPresenter;
import com.vsouhrada.android.lib.mflow.ui.BaseFlowActionBarActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flow.Parcer;


/**
 * This module provides for you all main classes which you will need to use in your application. You can simply include this module inside
 * of your module.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 */
@Module(
        library = true,
        injects = {
                BaseFlowActionBarActivity.class
        }
)
public class MFlowModule {

  /**
   * Method return (provides) instance of {@link ActionBarPresenter}
   *
   * @return the instance of {@link ActionBarPresenter}
   * @since 1.0.0
   */
  @Provides
  @Singleton
  ActionBarPresenter provideActionBarPresenter() {
    return new ActionBarPresenter();
  }

  /**
   * Method return (provides) instance of {@link ActivityPresenter}
   *
   * @return the instance of {@link ActivityPresenter}
   * @since 1.0.0
   */
  @Provides
  @Singleton
  ActivityPresenter provideActivityPresenter() {
    return new ActivityPresenter();
  }

  /**
   * Method return (provides) instance of {@link DrawerPresenter}
   *
   * @return the instance of {@link DrawerPresenter}
   * @since 1.0.0
   */
  @Provides
  @Singleton
  DrawerPresenter provideDrawerPresenter() {
    return new DrawerPresenter();
  }

  /**
   * Method return (provides) instance of {@link Gson}
   *
   * @return the instance of {@link Gson}
   * @since 1.0.0
   */
  @Provides
  @Singleton Gson provideGson() {
    return new GsonBuilder().create();
  }

  /**
   * Method return (provides) instance of {@link GsonParcer}
   *
   * @return the instance of {@link GsonParcer}
   * @since 1.0.0
   */
  @Provides
  @Singleton Parcer<Object> provideParcer(Gson gson) {
    return new GsonParcer<>(gson);
  }
}
