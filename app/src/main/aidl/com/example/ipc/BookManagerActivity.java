package com.example.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class BookManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

    }

    private IBookManager2 mRemoteBookManager;
    //首先我们声明一个DeathRecipient对象。
    //实现这个方法，当Binder死亡的时候，系统就会回调binderDied方法
    //然后我们就可以移出之前绑定的binder代理并重新绑定远程服务
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(mRemoteBookManager == null)return ;
            mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient,0);
            mRemoteBookManager = null;
            //这里重新绑定远程Service
        }
    };
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager2 bookManager2 = IBookManager2.Stub.asInterface(service);
            mRemoteBookManager = bookManager2;
            try {
                //其中linkToDeath的第二个参数是个标记位，我们直接设0即可。
                //经过上面两个操作，我们就给Binder设置了死亡代理，当死亡的时候就可以收到通知了。
                //我们也可以通过binder的isBinderAlive也可以判断Binder是否死亡
                mRemoteBookManager.asBinder().linkToDeath(mDeathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}