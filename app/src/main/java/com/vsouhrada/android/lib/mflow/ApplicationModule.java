package com.vsouhrada.android.lib.mflow;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.vsouhrada.android.lib.mflow.annotations.ForApplication;
import com.vsouhrada.android.lib.mflow.demo.app.MFlowApp;
import com.vsouhrada.android.lib.mflow.demo.core.MainActivity;
import com.vsouhrada.android.lib.mflow.demo.functions.activity.FlowPresenterActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 */
@Module(
        includes = {
                MFlowModule.class
        },
        injects = {
                MFlowApp.class,
                MainActivity.class,
                FlowPresenterActivity.class
        },
        library = true
)
public final class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ForApplication
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    /**
     * @return
     * @since 1.0.0
     */
    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

}
