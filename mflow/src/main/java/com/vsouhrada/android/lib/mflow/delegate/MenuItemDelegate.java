package com.vsouhrada.android.lib.mflow.delegate;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.*;


/**
 * Delegate implementation around {@link MenuItem}
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see MenuItem
 * @since 1.0.0
 */
public class MenuItemDelegate implements MenuItem {

  public MenuItem delegate;

  public MenuItemDelegate(MenuItem delegate) {
    this.delegate = delegate;
  }

  @Override public int getItemId() {
    return delegate.getItemId();
  }

  @Override public int getGroupId() {
    return delegate.getGroupId();
  }

  @Override public int getOrder() {
    return delegate.getOrder();
  }

  @Override public MenuItem setTitle(CharSequence title) {
    return delegate.setTitle(title);
  }

  @Override public MenuItem setTitle(int title) {
    return delegate.setTitle(title);
  }

  @Override public CharSequence getTitle() {
    return delegate.getTitle();
  }

  @Override public MenuItem setTitleCondensed(CharSequence title) {
    return delegate.setTitleCondensed(title);
  }

  @Override public CharSequence getTitleCondensed() {
    return delegate.getTitleCondensed();
  }

  @Override public MenuItem setIcon(Drawable icon) {
    return delegate.setIcon(icon);
  }

  @Override public MenuItem setIcon(int iconRes) {
    return delegate.setIcon(iconRes);
  }

  @Override public Drawable getIcon() {
    return delegate.getIcon();
  }

  @Override public MenuItem setIntent(Intent intent) {
    return delegate.setIntent(intent);
  }

  @Override public Intent getIntent() {
    return delegate.getIntent();
  }

  @Override public MenuItem setShortcut(char numericChar, char alphaChar) {
    return delegate.setShortcut(numericChar, alphaChar);
  }

  @Override public MenuItem setNumericShortcut(char numericChar) {
    return delegate.setNumericShortcut(numericChar);
  }

  @Override public char getNumericShortcut() {
    return delegate.getNumericShortcut();
  }

  @Override public MenuItem setAlphabeticShortcut(char alphaChar) {
    return delegate.setAlphabeticShortcut(alphaChar);
  }

  @Override public char getAlphabeticShortcut() {
    return delegate.getAlphabeticShortcut();
  }

  @Override public MenuItem setCheckable(boolean checkable) {
    return delegate.setCheckable(checkable);
  }

  @Override public boolean isCheckable() {
    return delegate.isCheckable();
  }

  @Override public MenuItem setChecked(boolean checked) {
    return delegate.setChecked(checked);
  }

  @Override public boolean isChecked() {
    return delegate.isChecked();
  }

  @Override public MenuItem setVisible(boolean visible) {
    return delegate.setVisible(visible);
  }

  @Override public boolean isVisible() {
    return delegate.isVisible();
  }

  @Override public MenuItem setEnabled(boolean enabled) {
    return delegate.setEnabled(enabled);
  }

  @Override public boolean isEnabled() {
    return delegate.isEnabled();
  }

  @Override public boolean hasSubMenu() {
    return delegate.hasSubMenu();
  }

  @Override public SubMenu getSubMenu() {
    return delegate.getSubMenu();
  }

  @Override public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
    return delegate.setOnMenuItemClickListener(menuItemClickListener);
  }

  @Override public ContextMenu.ContextMenuInfo getMenuInfo() {
    return delegate.getMenuInfo();
  }

  @Override public void setShowAsAction(int actionEnum) {
    delegate.setShowAsAction(actionEnum);
  }

  @Override public MenuItem setShowAsActionFlags(int actionEnum) {
    return delegate.setShowAsActionFlags(actionEnum);
  }

  @Override public MenuItem setActionView(View view) {
    return delegate.setActionView(view);
  }

  @Override public MenuItem setActionView(int resId) {
    return delegate.setActionView(resId);
  }

  @Override public View getActionView() {
    return delegate.getActionView();
  }

  @Override public MenuItem setActionProvider(ActionProvider actionProvider) {
    return delegate.setActionProvider(actionProvider);
  }

  @Override public ActionProvider getActionProvider() {
    return delegate.getActionProvider();
  }

  @Override public boolean expandActionView() {
    return delegate.expandActionView();
  }

  @Override public boolean collapseActionView() {
    return delegate.collapseActionView();
  }

  @Override public boolean isActionViewExpanded() {
    return delegate.isActionViewExpanded();
  }

  @Override public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
    return delegate.setOnActionExpandListener(listener);
  }

  public void setDelegate(MenuItem delegate) {
    this.delegate = delegate;
  }

  public MenuItem getDelegate() {
    return delegate;
  }
}
