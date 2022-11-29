package com.example.ipc;

import java.io.Serializable;

public class User implements Serializable {
    //只需要实现Serializable接口即可
    private static final long serialVersionUID = 519067123721295773L;
    public int userId;
    public String userName;
    public boolean isMale;
}
