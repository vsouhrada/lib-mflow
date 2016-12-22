package com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.screen;


import com.vsouhrada.android.lib.mflow.demo.R;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.FeaturesScreen;
import com.vsouhrada.android.lib.mflow.demo.core.mvp.screen.MainScreen;
import com.vsouhrada.android.lib.mflow.demo.functions.flow.mvp.view.FlowDemoAView;
import com.vsouhrada.android.lib.mflow.screen.DrawerScreenBlueprint;
import com.vsouhrada.android.lib.mflow.screen.ScreenBlueprint;

import flow.HasParent;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see ScreenBlueprint
 * @see HasParent<FeaturesScreen>
 * @since 1.0.0
 */
public class FlowDemoAScreen extends DrawerScreenBlueprint implements HasParent<FeaturesScreen> {

    @Override
    public int getLayoutResourceId() {
        return R.layout.v_flow_demo_a;
    }

    @Override
    public int getTitleResourceId() {
        return R.string.flow_demo_a_title;
    }

    @Override
    public String getMortarScopeName() {
        return getClass().getName();
    }

    @Override
    public Object getDaggerModule() {
        return new Module();
    }

    @Override
    public FeaturesScreen getParent() {
        return new FeaturesScreen();
    }

    @dagger.Module(
            injects = FlowDemoAView.class,
            addsTo = MainScreen.Module.class
    )
    public class Module {

    }
}
