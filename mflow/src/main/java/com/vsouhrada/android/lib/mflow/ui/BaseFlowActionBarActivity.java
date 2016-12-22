package com.vsouhrada.android.lib.mflow.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vsouhrada.android.lib.mflow.R;
import com.vsouhrada.android.lib.mflow.parcer.MenuItemParcer;
import com.vsouhrada.android.lib.mflow.presenter.ActionBarPresenter;
import com.vsouhrada.android.lib.mflow.presenter.ActivityPresenter;
import com.vsouhrada.android.lib.mflow.presenter.DrawerPresenter;
import com.vsouhrada.android.lib.mflow.presenter.IOnBackPressedCallback;
import com.vsouhrada.android.lib.mflow.screen.IScreenBlueprint;
import com.vsouhrada.android.lib.mflow.view.CoreDrawerView;
import com.vsouhrada.android.lib.mflow.view.CoreView;

import java.util.List;

import javax.inject.Inject;

import flow.Flow;
import mortar.Blueprint;
import mortar.Mortar;
import mortar.MortarActivityScope;
import mortar.MortarScope;
import rx.util.functions.Action0;
import rx.util.functions.Action1;


/**
 * <p>This is implementation of the base activity class from the MFlow framework. Class contains reference on
 * the {@link ActionBarPresenter}, the main flow instance {@link Flow} plus on {MortarActivityScope}
 * class.</p>
 * <p>Each your activity should be extended from this class!!!</p>
 *
 * @author vsouhrada
 * @version 1.3.0
 * @see android.app.Activity
 * @see ActionBarPresenter.View
 * @see ActivityPresenter.View
 * @see DrawerPresenter.View
 * @since 1.0.0
 */
public abstract class BaseFlowActionBarActivity extends ActionBarActivity implements ActionBarPresenter.View, ActivityPresenter.View,
        DrawerPresenter.View {

  private static final String SAVED_SPARSE_MENU_ITEMS_STATE = "sMenuItemsBundled";

  private MortarActivityScope activityScope;

  private Flow mainFlow;

  private List<ActionBarPresenter.MenuAction> menuActions;

  private int menuResourceId = IScreenBlueprint.NO_RESOURCE;

  private Menu currentMenu;

  private Action0 onUpButtonAction;

  private IOnBackPressedCallback onBackPressedCallback;

  private Action1<Integer> onMenuItemClickAction;

  private ActionBarDrawerToggle drawerToggle;

  private ICoreView coreView;

  private SparseArray<MenuItemParcer> sparseMenuItemsState;
  @Inject ActionBarPresenter actionBarPresenter;
  @Inject ActivityPresenter activityPresenter;
  @Inject DrawerPresenter drawerPresenter;

  private boolean destroyedInternals = false;

  /**
   * Return the {@link Blueprint} that defines the {@link MortarScope} for this activity.
   */
  protected abstract Blueprint getBlueprint();

  protected abstract int getContentView();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState != null) {
      // Restore menu items => menu items are saved to savedInstanceState only if a menu was loaded
      // from the Xml file!
      sparseMenuItemsState = savedInstanceState.getSparseParcelableArray(SAVED_SPARSE_MENU_ITEMS_STATE);
    }

    MortarScope parentScope = Mortar.getScope(getApplication());
    activityScope = Mortar.requireActivityScope(parentScope, getBlueprint());
    Mortar.inject(this, this);

    actionBarPresenter.takeView(this);
    activityPresenter.takeView(this);

    activityScope.onCreate(savedInstanceState);
    setContentView(getContentView());

    coreView = getCoreView();
    mainFlow = coreView.getFlow();

    if (coreView instanceof CoreDrawerView) {
      drawerToggle = ((CoreDrawerView) coreView).getDrawerToggle();
      drawerPresenter.takeView(this);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();

    if (isFinishing()) {
      destroyInternals();
    }
  }

  @Override
  public Object getSystemService(String name) {
    if (Mortar.isScopeSystemService(name)) {
      return activityScope;
    }

    return super.getSystemService(name);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (menuResourceId != IScreenBlueprint.NO_RESOURCE && currentMenu != null) {
      int size = currentMenu.size();
      if (size > 0) {
        SparseArray<MenuItemParcer> menuItemsSparse = new SparseArray<>();
        for (int i = 0; i < size; i++) {
          MenuItem menuItem = currentMenu.getItem(i);
          MenuItemParcer parcer = new MenuItemParcer();
          parcer.itemId = menuItem.getItemId();
          parcer.isVisible = menuItem.isVisible();
          parcer.isEnabled = menuItem.isEnabled();

          menuItemsSparse.put(parcer.itemId, parcer);
        }

        outState.putSparseParcelableArray(SAVED_SPARSE_MENU_ITEMS_STATE, menuItemsSparse);
      }
    }

    activityScope.onSaveInstanceState(outState);
  }

  /**
   * Inform the view about back events.
   */
  @Override
  public void onBackPressed() {
    // Check if there is a custom action for back pressed action
    if (onBackPressedCallback != null && onBackPressedCallback.onBackPressed()) {
      // User/Developer will handle back pressed action yourself - so just return
      return;
    }

    // Give the view a chance to handle going back. If it declines the honor, let super do its thing.
    if (!mainFlow.goBack()) {
      super.onBackPressed();
    }
  }

  /**
   * Inform the view about up events.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (drawerToggle != null && drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }

    if (item.getItemId() == android.R.id.home) {
      if (onUpButtonAction == null) {
        return mainFlow.goUp();
      } else {
        // There is a custom action for UP button action
        onUpButtonAction.call();

        return true;
      }
    }

    if (onMenuItemClickAction != null) {
      onMenuItemClickAction.call(item.getItemId());

      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    // When the activity was finishing in onPause() it already destroyed internals
    if (!destroyedInternals) {
      destroyInternals();
    }
    super.onDestroy();
  }

  private void destroyInternals() {
    if (onBackPressedCallback != null) {
      onBackPressedCallback = null;
    }

    if (actionBarPresenter != null) {
      actionBarPresenter.dropView(this);
    }
    if (activityPresenter != null) {
      activityPresenter.dropView(this);
    }
    if (drawerPresenter != null) {
      drawerPresenter.dropView(this);
    }

    // activityScope may be null in case isWrongInstance() returned true in onCreate()
    if (activityScope != null) {
      MortarScope parentScope = Mortar.getScope(getApplication());
      parentScope.destroyChild(activityScope);
      activityScope = null;
    }
    destroyedInternals = true;
  }

  /**
   * Configure the action bar menu as required by {@link ActionBarPresenter.View}.
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    //this.currentMenu = menu;

    if (menuResourceId != IScreenBlueprint.NO_RESOURCE) {
      // Load menu from xml
      getMenuInflater().inflate(menuResourceId, menu);
    } else if (menuActions != null) {
      // Load menu from MenuActions
      for (final ActionBarPresenter.MenuAction menuAction : menuActions) {
        MenuItem menuItem = menu.add(menuAction.title);
        if (menuAction.iconResId != null) {
          menuItem.setIcon(menuAction.iconResId);
        }
        menuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

          @Override
          public boolean onMenuItemClick(MenuItem menuItem) {
            menuAction.action.call();
            return true;
          }
        });
      }
    }

    actionBarPresenter.onMenuCreated();

    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    if (menuResourceId != IScreenBlueprint.NO_RESOURCE && currentMenu != null
            && sparseMenuItemsState != null && sparseMenuItemsState.size() > 0) {

      int size = currentMenu.size();
      for (int i = 0; i < size; i++) {
        // Retrieve previous instance of menu item
        MenuItem oldItem = currentMenu.getItem(i);
        // Retrieve a new instance of menu item based on ID
        MenuItem newItem = menu.findItem(oldItem.getItemId());

        // Restore state of menu item
        MenuItemParcer itemParcer = sparseMenuItemsState.get(oldItem.getItemId());
        newItem.setVisible(itemParcer.isVisible);
        newItem.setEnabled(itemParcer.isEnabled);

        actionBarPresenter.menuContextAware(newItem);
      }
      // We need to reset saved state for menu items, because when user (for instance)
      // will press UP button or back button (in screen B) so previous
      // screen (A) will be shown - next when user return back to the view (B) menu items will be shown in state in which user
      // leaved screen (B).
      sparseMenuItemsState = null;
    }
    currentMenu = menu;

    actionBarPresenter.onMenuPrepared();

    return true;
  }

  @Override
  public MortarScope getMortarScope() {
    return activityScope;
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if (drawerToggle != null) {
      drawerToggle.onConfigurationChanged(newConfig);
    }
  }

  @Override
  public void setShowHomeEnabled(boolean enabled) {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayShowHomeEnabled(enabled);
  }

  @Override
  public void setUpButtonEnabled(boolean enabled) {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(enabled);
    actionBar.setHomeButtonEnabled(enabled);
  }

  @Override public void setOnUpButtonAction(Action0 onUpAction) {
    onUpButtonAction = onUpAction;
  }

  @Override
  public void setSubtitle(CharSequence subtitle) {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setSubtitle(subtitle);
  }

  @Override
  public void setMenu(List<ActionBarPresenter.MenuAction> actions) {
    menuActions = actions;
    supportInvalidateOptionsMenu();
  }

  @Override
  public void setMenu(int menuResourceId) {
    this.menuResourceId = menuResourceId;
    // Reset previous state - in case of that menu actions has been used before
    if (menuActions != null) {
      menuActions = null;
    }
    supportInvalidateOptionsMenu();
  }

  @Override
  public void setOnMenuItemClickAction(Action1<Integer> onMenuItemClickAction) {
    this.onMenuItemClickAction = onMenuItemClickAction;
  }

  @Override
  public void resetMenuResource() {
    menuResourceId = IScreenBlueprint.NO_RESOURCE;
    onMenuItemClickAction = null;
  }

  @Override
  public Context getMortarContext() {
    return this;
  }

  @Override
  public MenuItem getMenuItem(int menuId) {
    if (currentMenu != null) {
      return currentMenu.findItem(menuId);
    }

    return null;
  }

  @Override
  public void finish() {
    super.finish();
  }

  @Override
  public void startActivity(Class<?> cls) {
    startActivity(new Intent(this, cls));
  }

  @Override
  public void setDrawerIndicatorEnabled(boolean enable) {
    if (drawerToggle != null) {
      drawerToggle.setDrawerIndicatorEnabled(enable);
    }
  }

  @Override public void setDrawerLockMode(int lockMode) {
    if (coreView != null && coreView instanceof CoreDrawerView) {
      ((CoreDrawerView) coreView).setDrawerLockMode(lockMode);
    }
  }

  @Override public void setOnBackPressedAction(IOnBackPressedCallback onBackPressedCallback) {
    this.onBackPressedCallback = onBackPressedCallback;
  }

  private ICoreView getCoreView() {
    View root = findViewById(R.id.content_screen);
    if (!(root instanceof CoreView) && !(root instanceof CoreDrawerView)) {
      throw new IllegalStateException("The main view must be instance of the " + CoreView.class.getSimpleName() + " or " + CoreDrawerView
              .class.getSimpleName());
    }
    return (ICoreView) root;
  }
}


