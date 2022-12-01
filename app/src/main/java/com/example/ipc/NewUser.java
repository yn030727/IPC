package com.example.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class NewUser implements Parcelable {
    //NewUser内部有四个变量
    public int userid;
    public String userName;
    public boolean isMale;
    public Book book;//Book是另外一个序列化对象。
    //它的反序列化过程需要传递当前线程的上下问类加载器


    public NewUser(int userid , String userName , boolean isMale){
        this.userid=userid;
        this.userName=userName;
        this.isMale=isMale;
    }

    protected NewUser(Parcel in){
        userName = in.readString();
        userid = in.readInt();
        isMale = in.readInt()==1;
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }



    //CREATOR来完成反序列化
    //其内部标明了如何创建序列化对象和数组。(通过Parcel的read操作来完成反序列化）
    public static final Creator<NewUser> CREATOR = new Creator<NewUser>() {
        @Override
        public NewUser createFromParcel(Parcel in) {
            return new NewUser(in);
        }

        @Override
        public NewUser[] newArray(int size) {
            return new NewUser[size];
        }
    };

    //内容描述功能，一般情况下都返回0
    @Override
    public int describeContents() {
        return 0;
    }


    //实现序列化操作
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
