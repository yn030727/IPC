package com.example.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ipc.utils.MyConstants;
import com.example.ipc.utils.MyUtils;

import java.io.File;
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


    //我们在MainActivity的onResume中序列化一个User对象到sd卡上的一个文件里
    //然后在SecondActivity中能够正确恢复User对象的值。
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Ning","onResume");
        persistToFile();
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    private void persistToFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建User实例，是序列化对象
                User user = new User(1 , "helloWorld" , false);
                Log.d("Ning","User make ");
                //创建目录
                File dir = new File(MyConstants.NING_PATH);
                //创建失败就用方法再创建一个目录
                if(!dir.exists()){
                    dir.mkdirs();
                }
                //创建文件
                File cachedFile = new File (MyConstants.CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    //打开输出流
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cachedFile));
                    //写入user信息
                    objectOutputStream.writeObject(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    //关闭输出流
                    MyUtils.close(objectOutputStream);
                }
            }
        }).start();
    }
}