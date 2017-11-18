package com.example.lidongxue.sqldbchat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lidongxue on 17-10-9.
 */

public class UserDBH extends SQLiteOpenHelper {
    final String CREATE_TABLE_USER ="create table user(u_id string primary key  , "+
            "username string , "+"u_pwd string , "+"u_pic string , "+
            "u_region string , "+"u_status int default 0 "+")";
    final String CREATE_TABLE_USER_CONTACT ="create table user_contact(_id integer primary key autoincrement , "+
            "u_id string , "+"u_ids string , "+"foreign key(u_id) references user )";
    final String CREATE_TABLE_USER_CONTACTS ="create table user_contacts(_id integer primary key autoincrement , "+
            "u_id string , "+"to_id string , "+"to_id_name string, "+"to_chat_bg string"+")";
    final String CREATE_TABLE_USER_CHAT ="create table user_chat(u_id string , "
            +"to_id string , "+" chat_mes string , "+" primary key(u_id,to_id), "+
            "foreign key(u_id) references user "+")";


    public UserDBH(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //第一次使用数据库时自动创建用户表);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER_CONTACT);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER_CONTACTS);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER_CHAT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
