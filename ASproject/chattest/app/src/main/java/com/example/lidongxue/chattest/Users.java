package com.example.lidongxue.chattest;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by lidongxue on 17-10-10.
 */

public final class Users {
    public static final String AUTHORITY="com.example.providers.userprovider";
    //定义一个静态内部类
    public static final class User implements BaseColumns{
        //定义content所允许操作的六个数列
        public final static String U_ID="u_id";
        public final static String U_NAME="username";
        public final static String U_PWD="u_pwd";
        public final static String U_PIC="u_pic";
        public final static String U_REGION="u_region";
        public final static String U_STATUS="u_status";
        //定义该content提供服务的两个uri
        public final static Uri USERID_CONTENT_URI =Uri.parse("content://"+AUTHORITY+"/userid");
        public final static Uri USERMSG_CONTENT_URI =Uri.parse("content://"+AUTHORITY+"/usermsg");


    }


}
