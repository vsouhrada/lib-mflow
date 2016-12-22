package com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.presenter;


import com.vsouhrada.android.lib.mflow.demo.functions.activity.mvp.view.FlowActivityView;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;

import javax.inject.Inject;

import mortar.ViewPresenter;


/**
 * @author vsouhrada
 * @version 1.0.0
 * @see FlowActivityView
 * @see ViewPresenter
 * @since 1.0.0
 */
public class FlowActivityPresenter extends ViewPresenter<FlowActivityView> {

    private final ActivityPresenter activityPresenter;

    @Inject
    public FlowActivityPresenter(ActivityPresenter activityPresenter) {
        this.activityPresenter = activityPresenter;
    }

    public void finish() {
        activityPresenter.finish();
    }
}
