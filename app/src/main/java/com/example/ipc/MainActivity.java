package com.example.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User(0 , "jake" ,true);

        //序列化过程
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.txt"));
            out.writeObject(user);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化过程
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("cache.txt"));
            User newUser = (User)input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //序列化过程将元素添加到文件当中
        //反序列化过程将元素从文件当中读取出来
        //上述代码演示了采用Serializable方式序列化对象的过程

    }
}