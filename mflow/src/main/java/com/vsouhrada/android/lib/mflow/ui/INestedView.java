package com.vsouhrada.android.lib.mflow.ui;

import mortar.Presenter;


/**
 * <p>This is an interface which should be implements by all classes which supports Nested view feature.</p>
 * <p>The Nested views are a standard views but with possibility to be included inside of another view - from this use-case we
 * named it as the Nested views (It is similar to the Nested Fragments in standard android SDK)</p>
 *
 * @param <T> the type of the 'Nested' presenter.
 * @author vsouhrada
 * @version 1.0.0
 * @see Presenter
 * @since 1.0.0
 */
public interface INestedView<T extends Presenter> {

  /**
   * Returns the current instance of the 'Nested' presenter.
   *
   * @return the current instance of the 'Nested' presenter
   * @since 1.0.0
   */
  T getPresenter();

  /**
   * By this method you're able to set the 'Nested' presenter to your view.
   *
   * @param presenter the 'Nested' presenter
   * @since 1.0.0
   */
  void setPresenter(T presenter);

}
