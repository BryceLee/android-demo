package com.lee.components.bindermodule;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

  int bookId;
  String bookname;

  public Book(int bookId) {
    this.bookId = bookId;
  }

  public Book(int bookId, String bookname) {
    this.bookId = bookId;
    this.bookname = bookname;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.bookId);
    dest.writeString(this.bookname);
  }

  protected Book(Parcel in) {
    this.bookId = in.readInt();
    this.bookname = in.readString();
  }

  public static final Creator<Book> CREATOR = new Creator<Book>() {
    @Override
    public Book createFromParcel(Parcel source) {
      return new Book(source);
    }

    @Override
    public Book[] newArray(int size) {
      return new Book[size];
    }
  };
}
