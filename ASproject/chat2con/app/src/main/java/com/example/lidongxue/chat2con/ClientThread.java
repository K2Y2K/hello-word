package com.example.lidongxue.chat2con;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by lidongxue on 17-10-14.
 */

public class ClientThread implements Runnable {
    private Socket s;
    private Handler handler;
    public  Handler revHandler;
    BufferedReader br=null;
    OutputStream os=null;
    public ClientThread(Handler handler){
        this.handler=handler;
    }
    @Override
    public void run() {
        try {
            s=new Socket("10.10.11.109",12345);
            // s=new Socket("10.0.2.2",12345);

            System.out.println("和服务器连接成功"+s.getInetAddress());
            br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            os=s.getOutputStream();
            new Thread(){
                @Override
                public void run() {
                    String content=null;
                    try{
                        while ((content=br.readLine())!=null){
                            System.out.println("接收服务器信息content："+content);
                            Message msg=new Message();
                            msg.what=0x123;
                            msg.obj=content;
                            handler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            Looper.prepare();
            revHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==0x345){
                        try{
                            os.write((msg.obj.toString() +"\r\n").getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (SocketTimeoutException e) {
            System.out.println("网络连接超时");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
