package com.example.lidongxue.sqldbchat.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongxue on 17-10-9.
 */

public class UserDB {
    public static final String DB_NAME = "userMsg.db3";
    public static final int VERSON = 1;
    private static UserDB userDB;
    private UserDBH userDBH;
    private SQLiteDatabase db;
    public UserDB(Context context){
        userDBH=new UserDBH(context, DB_NAME, null, VERSON);
        db=userDBH.getWritableDatabase();
        //db=userDBH.getReadableDatabase();
    }
    public synchronized static UserDB getInstance(Context context){
        if(userDB==null){
            userDB=new UserDB(context);
        }
        return userDB;
    }
    //往用户表里插入账号和密码数据　
    public long insertUser(String u_ids, String u_pwds){
        long ren=0;
        db.beginTransaction();
       /* try{
            db.execSQL("insert into user"+"("+clumname+
                    ") values("+ "\"" +value+ "\"" +")");
            Log.d("insert success.","插入成功");
        }catch(RuntimeException e){
            Log.e("insert error.",e.toString());
        }finally {
            db.endTransaction();
        }*/
        try{
            db.execSQL("insert into user(u_id,u_pwd) values(?,?)" ,new String[]{u_ids,u_pwds});
//          这里需要对于字符串数据加引号
//          db.execSQL("insert into user(u_id,u_pwd) values("  + u_ids + "," +u_pwds+ ") ");
            Cursor cursor1=db.rawQuery("select * from user",null);
            Log.d("query success.","查询所信息成功");
            Log.d("查询queryUserMsg返回行和列的个数:","行数"+cursor1.getCount()+"列数 "+
                    cursor1.getColumnCount());
            db.setTransactionSuccessful();
            Log.d("insert success.","插入成功");
            return ren=cursor1.getCount();
        }catch(RuntimeException e){
            Log.e("insert error.",e.toString());
        }finally {

            db.endTransaction();
        }
        return ren;

    }
    //根据用户账号获取用户信息
    public Cursor queryUserMsg(String u_id){
        Cursor cursor2=db.rawQuery("select * from user where u_id=?",
                new String[]{String.valueOf(u_id)});
        Log.d("query success.","查询条件信息成功");
        Log.d("查询queryUserMsg()行和列个数:","行数"+cursor2.getCount()+"列数 "+
                cursor2.getColumnCount());
        return  cursor2;
    }
    //查询用户表里的全部信息
    public Cursor queryUserMsgs(){
        Cursor cursor3=db.rawQuery("select * from user",null);
        Log.d("query success.","查询所信息成功");
        Log.d("查询queryUserMsgs()行和列个数:","行数"+cursor3.getCount()+"列数 "+
                cursor3.getColumnCount());
        return  cursor3;
    }
    //删除用户表里的全部信息
    public long deleteUserMsgs(){
        long ren=0;
        Cursor userMsgsCursor =queryUserMsgs();
        if(userMsgsCursor.getCount()!=0){
            db.execSQL("delete from user");
            Log.d("delete success.","删除用户表中所有信息成功");
        }
        if(userMsgsCursor!=null){
            userMsgsCursor.close();
        }


        return ren;
    }

    //将游标数据装换成数组的线性表存储
    public ArrayList<Map<String,String>>converCursorToList(Cursor cursor){
        ArrayList<Map<String,String>> result=new ArrayList<Map<String,String>>();
        while(cursor.moveToNext()){
            //将结果集中的数据存入ArrayList中
            Map<String,String> map =new HashMap<>();
            map.put("u_id",cursor.getString(0));
            map.put("username",cursor.getString(1));
            map.put("u_pwd",cursor.getString(2));
            map.put("u_pic",cursor.getString(3));
            map.put("u_region",cursor.getString(4));
            map.put("u_status",String.valueOf(cursor.getInt(5)));
            result.add(map);

        }
        return  result;
    }

    //查询用户密码
    public String queryUserPwd(String u_id){
        String keyword=null;
        Cursor userMsgCursor =queryUserMsg(u_id);
        Log.d("查询密码返回行和列的个数:","行数"+userMsgCursor.getCount()+"列数 "+
                userMsgCursor.getColumnCount());
        if(userMsgCursor.moveToFirst()){
           do{
               int column =userMsgCursor.getColumnIndex("u_pwd");
               keyword=userMsgCursor.getString(column);
           }while(userMsgCursor.moveToNext());

        }
        if(userMsgCursor!=null){
            userMsgCursor.close();
        }
        return keyword;
    }
    //修改用户表
    public long updateUserMsg(String updclumname, String updvalues, String catchclumname, String catchvalues){
        long ren = 0;
        db.beginTransaction();
        try {
            //
            db.execSQL("update user set " + updclumname + " =" + "\"" + updvalues + "\"" + "where " + catchclumname + "=" + "\"" + catchvalues + "\"");
            db.setTransactionSuccessful();
            Log.d("update success.","修改成功");
            ren = 1;
        } catch (RuntimeException e) {
            Log.e("update error.", e.toString());
        } finally {
            db.endTransaction();
        }
        return ren;
    }

}
