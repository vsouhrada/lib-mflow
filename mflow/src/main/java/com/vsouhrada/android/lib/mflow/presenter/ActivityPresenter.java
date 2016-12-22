package com.vsouhrada.android.lib.mflow.presenter;

import com.vsouhrada.android.lib.mflow.view.IPresenterView;

import javax.inject.Singleton;

import mortar.Mortar;
import mortar.MortarScope;
import mortar.Presenter;


/**
 * @author vsouhrada
 * @version 1.3.0
 * @see Presenter
 * @see ActivityPresenter.View
 * @since 1.0.0
 */
@Singleton
public class ActivityPresenter extends Presenter<ActivityPresenter.View> {

  /**
   * @author vsouhrada
   * @version 1.3.0
   * @see IPresenterView
   * @since 1.0.0
   */
  public interface View extends IPresenterView {

    /**
     * Call this when your activity is done and should be closed.
     *
     * @see android.app.Activity#finish()
     * @since 1.0.0
     */
    void finish();

    /**
     * Call this in case of that you want to start a new activity from your current activity.
     *
     * @param cls new activity
     * @since 1.0.0
     */
    void startActivity(Class<?> cls);

    /**
     * Set the back (button) pressed action.
     *
     * @param onBackPressedCallback callback that will be called in case of that back button has been pressed.
     * @since 1.2.0
     */
    void setOnBackPressedAction(IOnBackPressedCallback onBackPressedCallback);

  }

  @Override
  protected MortarScope extractScope(View view) {
    return Mortar.getScope(view.getMortarContext());
  }

  /**
   * Call this when your activity is done and should be closed.
   *
   * @since 1.0.0
   */
  public void finish() {
    if (getView() != null) {
      getView().finish();
    }
  }

  /**
   * Call this in case of that you want to start a new activity from your current activity.
   *
   * @param cls new activity
   * @since 1.0.0
   */
  public void startActivity(Class<?> cls) {
    if (getView() != null) {
      getView().startActivity(cls);
    }
  }

  /**
   * Set the back (button) pressed action.
   *
   * @param onBackPressedCallback callback that will be called in case of that back button has been pressed.
   * @since 1.2.0
   */
  public void setOnBackPressedAction(IOnBackPressedCallback onBackPressedCallback) {
    if (getView() != null) {
      getView().setOnBackPressedAction(onBackPressedCallback);
    }
  }

  /**
   * By this method we are able to reset state of this presenter before a new screen will be shown.
   * @since 1.2.0
   */
  public void resetStateBeforeNewScreen() {
    setOnBackPressedAction(null);
  }

}