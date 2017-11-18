package com.example.lidongxue.serverone;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by lidongxue on 17-10-14.
 */

public class MyServer {
    public static ArrayList<Socket> socketList=
            new ArrayList<Socket>();
    public static void main(String[] args)throws IOException{
        ServerSocket ss=new ServerSocket(12345);
        System.out.println("服务器１");
        while(true){
            Socket s=ss.accept();
            System.out.println("S: Receiving...\n"+s.getInetAddress());
            System.out.println("服务器２监听到客户器");
            socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }
}
