package com.example.ipc;

import android.os.Parcel;
import android.os.Parcelable;

class Book1 implements Parcelable{
    public int bookId;
    public String bookName;
    public Book1(int bookId , String bookName){
        this.bookId=bookId;
        this.bookName=bookName;
    }


    protected Book1(Parcel in) {
        bookName=in.readString();
        bookId=in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
}