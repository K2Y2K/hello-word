package com.example.lidongxue.serversocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends AppCompatActivity {

    public static final String SERVERIP = "10.0.2.15";//这个地址其实没有用到
    public static final int SERVERPORT = 8191;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run() {
                try {
                    //ServerSocket serverSocket = new ServerSocket(SERVERPORT);
                    ServerSocket serverSocket = new ServerSocket();
                    SocketAddress saEndPoint = new InetSocketAddress(SERVERIP, SERVERPORT);
                    Log.d("---------------", "Server bind endpoint :" + saEndPoint);
                    serverSocket.bind(saEndPoint);
                    while (true) {
                        Socket client = serverSocket.accept();
                        System.out.println("S: Receiving...\n"+client.getInetAddress());
                        client.getInetAddress();
                        OutputStream os =client.getOutputStream();
                        os.write("您好，你收到服务器的重阳节祝福了吗！　\n".getBytes("utf-8"));
                        os.close();
                        client.close();
                    }
                }catch(Exception e) {
                    System.out.println("S: Error");
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
