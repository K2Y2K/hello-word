package com.example.lidongxue.chat1con.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

/**
 * Created by lidongxue on 17-10-12.
 */

public class ConnectionService extends Service {
    public static final String SERVER_NAME = "127.0.0.1";//主机名izqhrnmkjn55syz
    public static final String SERVER_IP = "10.0.2.2";//ip 192.168.0.252
    public static final int PORT = 5223;//端口
    private XMPPTCPConnection connection;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public ConnectionService getService() {
            System.out.println("--获得LocalBinder方法中getService()成功--");
            return ConnectionService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                super.run();
                getConnection();
            }
        }.start();
        System.out.println("--onCreate()--");

        System.out.println("--getConnection()连接成功--");
    }

    private final void fail(Object o) {

        if (o != null) {
            System.out.println(o);
        }
    }
    private final void fail(Object o, Object... args) {
        if (o != null && args != null && args.length > 0) {
            String s = o.toString();
            for (int i = 0; i < args.length; i++) {
                String item = args[i] == null ? "" : args[i].toString();
                if (s.contains("{" + i + "}")) {
                    s = s.replace("{" + i + "}", item);
                } else {
                    s += " " + item;
                }
            }
            System.out.println(s);
        }
    }


    public XMPPTCPConnection getConnection() {

        try {
            if (connection == null) {
                XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                        .setHost(SERVER_IP)//服务器IP地址
                        //服务器端口
                        .setPort(PORT)
                        //设置登录状态
                        .setSendPresence(false)
                        //.setXmppDomain("lee.com")

                        //服务器名称
                        .setServiceName(SERVER_NAME)
                        //是否开启安全模式
                        .setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled)
                        //是否开启压缩
                        .setCompressionEnabled(false)
                       // .setUsernameAndPassword("admin","123456lee")
                        //开启调试模式
                        .setDebuggerEnabled(true).build();
                connection = new XMPPTCPConnection(config);


                        connection.disconnect();
                        connection.connect();
                        System.out.println("－－－连接成功");
                        connection.login("admin","123456lee");
            }
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;

    }
    /**
     * 是否连接成功
     *
     * @return
     */
    public boolean isConnected() {
        if (connection == null) {
            return false;
        }
        if (!connection.isConnected()) {
            try {
                connection.connect();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

}
