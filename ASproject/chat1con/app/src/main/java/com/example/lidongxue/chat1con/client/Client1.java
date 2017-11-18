package com.example.lidongxue.chat1con.client;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.example.lidongxue.chat1con.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by lidongxue on 17-10-14.
 */

public class Client1 extends Activity {
    EditText show;
    Handler handler;
    BufferedReader br;
    Socket socket;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client1);
        show=findViewById(R.id.show);
        handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {

                if(msg.what==0x123){
                    show.append("\n"+msg.obj.toString());
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                     //socket = new Socket("10.0.2.2", 8192);
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("10.0.2.2",8192), 1000);
                     br =new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));

                    new Thread(){
                        @Override
                        public void run() {
                            String line=null;
                            try{
                                while(( line=br.readLine())!=null){
                                    Message msg=new Message();
                                    msg.what=0x123;
                                    msg.obj=line;
                                    handler.sendMessage(msg);
                                    br.close();
                                    socket.close();
                                }
                            }catch (IOException e){
                                e.printStackTrace();
                                System.out.println("服务器传送的数据为空");
                            }
                        }
                    }.start();

                } catch( SocketTimeoutException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("网络连接超时");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
