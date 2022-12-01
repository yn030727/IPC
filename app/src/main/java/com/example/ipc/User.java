package com.example.ipc;

import java.io.Serializable;


public class User implements Serializable {
    //只需要实现Serializable接口即可
    private static final long serialVersionUID = 519067123721295773L;
    //声明serialVersionUID，不是必须的，但是如果不声明，会影响反序列化
    public int userId;
    public String userName;
    public boolean isMale;

    public User(int userId, String userName, boolean isMale) {
        this.isMale=isMale;
        this.userId=userId;
        this.userName=userName;
    }

    public int getUserId() {
        return userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

}
