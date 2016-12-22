package com.vsouhrada.android.lib.mflow.demo.app;

import android.app.Application;

import com.vsouhrada.android.lib.mflow.BuildConfig;
import com.vsouhrada.android.lib.mflow.Modules;

import dagger.ObjectGraph;
import mortar.Mortar;
import mortar.MortarScope;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see Application
 * @since 1.0.0
 */
public class MFlowApp extends Application {

  private MortarScope rootScope;

  @Override
  public void onCreate() {
    super.onCreate();

    rootScope = Mortar.createRootScope(BuildConfig.DEBUG, ObjectGraph.create(Modules.list(this)));
  }

  @Override
  public Object getSystemService(String name) {
    if (Mortar.isScopeSystemService(name)) {
      return rootScope;
    }

    return super.getSystemService(name);
  }

}
