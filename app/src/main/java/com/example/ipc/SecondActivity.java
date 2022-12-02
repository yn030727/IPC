package com.example.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ipc.utils.MyConstants;
import com.example.ipc.utils.MyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = (User) getIntent().getSerializableExtra("extra_user");
        recoverFromFile();
    }

    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = null;
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
                ObjectInputStream objectInputStream = null ;
                if (cachedFile.exists()) {
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(cachedFile));
                        user = (User) objectInputStream.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        MyUtils.close(objectInputStream);
                    }
                }
            }
        }).start();
    }
}