package com.vsouhrada.android.lib.mflow.parcer;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * This is a {@link Parcelable} object for saving state of the menu item from the Action Bar.
 *
 * @author vsouhrada
 * @version 1.0.0
 * @see Parcelable
 * @since 1.0.0
 */
public class MenuItemParcer implements Parcelable {

  public int itemId;

  public boolean isVisible;
  public boolean isEnabled;

  public MenuItemParcer() {
  }

  private MenuItemParcer(Parcel in) {
    itemId = in.readInt();
    isVisible = in.readInt() == 0 ? false : true;
    isEnabled = in.readInt() == 0 ? false : true;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int flags) {
    parcel.writeInt(itemId);
    parcel.writeInt(isVisible ? 1 : 0);
    parcel.writeInt(isEnabled ? 1 : 0);
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("MenuItemParcer{");
    sb.append("itemId=").append(itemId);
    sb.append(", isVisible=").append(isVisible);
    sb.append(", isEnabled=").append(isEnabled);
    sb.append('}');
    return sb.toString();
  }

  public static final Creator<MenuItemParcer> CREATOR = new Creator<MenuItemParcer>() {

    @Override public MenuItemParcer createFromParcel(Parcel source) {
      return new MenuItemParcer(source);
    }

    @Override public MenuItemParcer[] newArray(int size) {
      return new MenuItemParcer[size];
    }
  };
}