package com.vsouhrada.android.lib.mflow.demo.functions.dialog;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @author vsouhrada
 * @version 1.1.0
 * @see Parcelable
 * @since 1.1.0
 */
public class DemoDialogInfo implements Parcelable {

  public final String title;
  public final String message;

  public DemoDialogInfo(String title, String message) {
    this.title = title;
    this.message = message;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int flags) {
    parcel.writeString(title);
    parcel.writeString(message);
  }

  public static final Creator<DemoDialogInfo> CREATOR = new Creator<DemoDialogInfo>() {

    @Override public DemoDialogInfo createFromParcel(Parcel parcel) {
      return new DemoDialogInfo(parcel.readString(), parcel.readString());
    }

    @Override public DemoDialogInfo[] newArray(int size) {
      return new DemoDialogInfo[size];
    }
  };
}
