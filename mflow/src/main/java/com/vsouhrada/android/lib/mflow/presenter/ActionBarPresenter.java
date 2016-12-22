package com.vsouhrada.android.lib.mflow.presenter;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import com.vsouhrada.android.lib.mflow.delegate.MenuItemDelegate;
import com.vsouhrada.android.lib.mflow.screen.IScreenBlueprint;
import com.vsouhrada.android.lib.mflow.view.IPresenterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

import javax.inject.Singleton;

import mortar.Mortar;
import mortar.MortarScope;
import mortar.Presenter;
import rx.util.functions.Action0;
import rx.util.functions.Action1;


/**
 * <p>Allows shared configuration of the Android ActionBar.</p>
 * <p><b>Note</b>: This class is a Singleton {@link Singleton}.</p>
 *
 * @author vsouhrada
 * @version 1.1.0
 * @see Presenter
 * @see View
 * @since 1.0.0
 */
@Singleton
public class ActionBarPresenter extends Presenter<ActionBarPresenter.View> {

  /**
   * This interface should be implemented by a view which is managed by this presenter
   *
   * @author vsouhrada
   * @version 1.1.0
   * @see IPresenterView
   * @since 1.0.0
   */
  public interface View extends IPresenterView {

    /**
     * Set whether to include the application home affordance in the action bar.
     * Home is presented as either an activity icon or logo.
     * <p/>
     * <p>To set several display options at once, see the setDisplayOptions methods.
     *
     * @param enabled true to show home, false otherwise.
     * @since 1.0.0
     */
    void setShowHomeEnabled(boolean enabled);

    /**
     * Set whether home should be displayed as an "up" affordance.
     * Set this to true if selecting "home" returns up by a single level in your UI
     * rather than back to the top level or front page.
     * <p/>
     * <p>To set several display options at once, see the setDisplayOptions methods.
     *
     * @param enabled true to show the user that selecting home will return one
     *                level up rather than to the top level of the app.
     * @since 1.0.0
     */
    void setUpButtonEnabled(boolean enabled);

    /**
     * Set the action bar's on Up nav click action.
     *
     * @param onUpAction action that will be called in case of clicking them Up nav button.
     * @since 1.1.0
     */
    void setOnUpButtonAction(Action0 onUpAction);

    /**
     * Set the action bar's title. This will only be displayed if
     * {@link android.app.ActionBar#DISPLAY_SHOW_TITLE} is set.
     *
     * @param title Title to set
     * @since 1.0.0
     */
    void setTitle(CharSequence title);

    /**
     * Set the action bar's subtitle. This will only be displayed if
     * {@link android.app.ActionBar#DISPLAY_SHOW_TITLE} is set. Set to null to disable the
     * subtitle entirely.
     *
     * @param subtitle Subtitle to set
     * @since 1.0.0
     */
    void setSubtitle(CharSequence subtitle);

    /**
     * <p>Set the action bar's menu items by using {@link MenuAction}
     * class.</p>
     * </p><b>BE AWARE</b>: Using this method has some limitations compared to using menu from xml. For more details see
     * {@link MenuAction}.</p>
     *
     * @param action list of menu actions which will be shown on the action bar.
     * @see #setMenu(int)
     * @since 1.0.0
     */
    void setMenu(List<MenuAction> action);

    /**
     * Set the action bar's menu resource - resource id of the xml file with menu which contains menu what you want to show.
     *
     * @param menuResourceId an unique identifier of menu xml file
     * @see #setOnMenuItemClickAction(Action1)
     * @since 1.0.0
     */
    void setMenu(int menuResourceId);

    /**
     * Set the action bar's on menu item click action.
     *
     * @param clickAction action that will be called in case of clicking them menu item
     * @since 1.0.0
     */
    void setOnMenuItemClickAction(Action1<Integer> clickAction);

    /**
     * This method reset menu resource variable. By this method we can reset previous state of menu which was loaded from the XML file.
     *
     * @since 1.0.0
     */
    void resetMenuResource();

    /**
     * Return the menu item with a particular identifier.
     *
     * @param menuId The identifier to find.
     * @return The menu item object, or null if there is no item with
     * this identifier.
     * @since 1.0.0
     */
    MenuItem getMenuItem(final int menuId);

  }

  /**
   * Configuration of the action bar.
   *
   * @author vsouhrada
   * @version 1.0.0
   * @since 1.0.0
   */
  public static class Config {

    public final boolean showHomeEnabled;
    public final boolean upButtonEnabled;

    public final CharSequence title;
    public final CharSequence subtitle;

    public final List<MenuAction> action;

    public int menuResourceId = IScreenBlueprint.NO_RESOURCE;

    /**
     * Construct a new action bar's configuration.
     *
     * @param showHomeEnabled true to show home, false otherwise.
     * @param upButtonEnabled true to show the user that selecting home will return one level up rather than to the top level of the app.
     * @param title           action bar's title.
     * @param subtitle        action bar's subtitle.
     * @param action          menu items represented by {@link MenuAction}
     * @since 1.0.0
     */
    public Config(boolean showHomeEnabled, boolean upButtonEnabled, CharSequence title, CharSequence subtitle, List<MenuAction> action) {
      this.showHomeEnabled = showHomeEnabled;
      this.upButtonEnabled = upButtonEnabled;
      this.title = title;
      this.subtitle = subtitle;
      this.action = action;
    }

    /**
     * Construct a new action bar's configuration.
     *
     * @param menuResourceId
     * @param showHomeEnabled true to show home, false otherwise.
     * @param upButtonEnabled true to show the user that selecting home will return one level up rather than to the top level of the app.
     * @param title           action bar's title.
     * @param subtitle        action bar's subtitle.
     * @since 1.0.0
     */
    public Config(int menuResourceId, boolean showHomeEnabled, boolean upButtonEnabled, CharSequence title, CharSequence subtitle) {
      this(showHomeEnabled, upButtonEnabled, title, subtitle, null);
      this.menuResourceId = menuResourceId;
    }

    /**
     * @param action list of menu actions which will be shown on the action bar.
     * @return new action bar's configuration
     */
    public Config withAction(List<MenuAction> action) {
      return new Config(showHomeEnabled, upButtonEnabled, title, subtitle, action);
    }

    public Config withAction(MenuAction action) {
      return new Config(showHomeEnabled, upButtonEnabled, title, subtitle, Arrays.asList(action));
    }
  }

  /**
   * @author vsouhrada
   * @version 1.0.0
   * @since 1.0.0
   */
  public static class MenuAction {

    public final Drawable iconResId;
    public final CharSequence title;
    public final Action0 action;

    public MenuAction(Drawable iconResId, CharSequence title, Action0 action) {
      this.iconResId = iconResId;
      this.title = title;
      this.action = action;
    }

    /**
     * Construct a new menu item's action.
     *
     * @param title  menu item's title
     * @param action menu item's on click action
     * @since 1.0.0
     */
    public MenuAction(CharSequence title, Action0 action) {
      this(null, title, action);
    }

  }

  private Config config;

  /**
   * Default constructor
   */
  public ActionBarPresenter() {
  }

  private static final WeakHashMap<Integer, MenuItemDelegate> PROXIES = new WeakHashMap<>();

  private List<IOnMenuInitializationCallback> menuCreatedCallbacks = new ArrayList<>();

  /*@Override
  public void onLoad(Bundle savedInstanceState) {
    if (config != null) {
      update();
    }
  }*/

  /**
   * By this method you can set a configuration of the action bar. Method will take care about action bar refresh itself.
   *
   * @param config current action bar's configuration
   * @since 1.0.0
   */
  public void setConfig(Config config) {
    this.config = config;
    update();
  }

  public Config getConfig() {
    return config;
  }

  @Override
  protected MortarScope extractScope(View view) {
    return Mortar.getScope(view.getMortarContext());
  }

  private void update() {
    View view = getView();
    if (view == null) return;

    setOnUpAction(null);

    view.setShowHomeEnabled(config.showHomeEnabled);
    view.setUpButtonEnabled(config.upButtonEnabled);
    view.setTitle(config.title);
    view.setSubtitle(config.subtitle);
    // ActionBar configuration
    if (config.menuResourceId != IScreenBlueprint.NO_RESOURCE) {
      PROXIES.clear();
      view.setMenu(config.menuResourceId);
    } else {
      PROXIES.clear();
      view.resetMenuResource();
      view.setMenu(config.action);
    }
  }

  /**
   * Set the action bar's title. This will only be displayed if
   * {@link android.app.ActionBar#DISPLAY_SHOW_TITLE} is set.
   *
   * @param title Title to set
   * @since 1.0.0
   */
  public void setTitle(CharSequence title) {
    View view = getView();
    if (view != null) {
      view.setTitle(title);
    }
  }

  /**
   * <p>Return the menu item with a particular identifier.</p>
   * <p><b>BE AWARE</b>:</p> This method can be use only in case of that you load a menu that is defined
   * in the xml file.
   *
   * @param menuItemId The identifier to find.
   * @return The menu item object, or null if there is no item with this identifier.
   * @since 1.0.0
   */
  public MenuItem getMenuItem(final int menuItemId) {
    MenuItemDelegate menuItemDelegate = PROXIES.get(menuItemId);
    if (menuItemDelegate == null && getView() != null) {
      MenuItem menuItem = getView().getMenuItem(menuItemId);
      if (menuItem != null) {
        PROXIES.put(menuItem.getItemId(), new MenuItemDelegate(menuItem));

        return menuItem;
      }
    } else {
      return menuItemDelegate.delegate;
    }

    return null;
  }

  /**
   * Set the action bar's on menu item click action.
   *
   * @param clickAction action that will be called in case of clicking them menu item
   * @since 1.0.0
   */
  public void setOnMenuItemClickAction(Action1<Integer> clickAction) {
    if (getView() != null) {
      getView().setOnMenuItemClickAction(clickAction);
    }
  }

  /**
   * Call this method in case of that you want to set a delegate menu item for your item which is as a parameter in this method.
   *
   * @param newItem menu item
   * @since 1.0.0
   */
  public void menuContextAware(MenuItem newItem) {
    MenuItemDelegate menuItemDelegate = PROXIES.get(newItem.getItemId());
    if (menuItemDelegate != null) {
      menuItemDelegate.setDelegate(newItem);
    }
  }

  /**
   * Set the action bar's on Up nav click action.
   *
   * @param onUpAction action that will be called in case of clicking them Up nav button.
   * @since 1.1.0
   */
  public void setOnUpAction(Action0 onUpAction) {
    if (getView() != null) {
      getView().setOnUpButtonAction(onUpAction);
    }
  }

  public void clearOnMenuCreatedListeners() {
    if (menuCreatedCallbacks != null) {
      menuCreatedCallbacks.clear();
    }
  }

  public void onMenuCreated() {
    if (menuCreatedCallbacks != null) {
      for (IOnMenuInitializationCallback callback: menuCreatedCallbacks) {
        callback.onMenuCreated();
      }
    }
  }

  public void onMenuPrepared() {
    if (menuCreatedCallbacks != null) {
      for (IOnMenuInitializationCallback callback: menuCreatedCallbacks) {
        callback.onMenuPrepared();
      }
    }
  }

  public void registerOnMenuCreatedListener(IOnMenuInitializationCallback callback) {
    if (menuCreatedCallbacks == null) {
      menuCreatedCallbacks = new ArrayList<>();
    }

    menuCreatedCallbacks.add(callback);
  }

  public void unregisterOnMenuCreatedListener(IOnMenuInitializationCallback callback) {
    if (menuCreatedCallbacks != null) {
      menuCreatedCallbacks.remove(callback);
    }
  }

  @Override protected void onExitScope() {
    super.onExitScope();
    clearOnMenuCreatedListeners();
  }


}