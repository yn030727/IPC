package com.example.ipc.utils;

import android.os.Environment;

public class MyConstants {
    //我的常数
    //NING_PATH 打开sd卡的目录
    //CACHE_FILE_PATH 用于在sd卡目录下创建文件
    public static final String NING_PATH= Environment.getExternalStorageDirectory().getPath()+"/IPC";
    public static final String CACHE_FILE_PATH = NING_PATH + "usercache";
    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;
}
