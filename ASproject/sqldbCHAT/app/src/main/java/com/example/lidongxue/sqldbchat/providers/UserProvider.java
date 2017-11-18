package com.example.lidongxue.sqldbchat.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lidongxue.sqldbchat.content.Users;
import com.example.lidongxue.sqldbchat.database.UserDB;
import com.example.lidongxue.sqldbchat.database.UserDBH;

/**
 * Created by lidongxue on 17-10-10.
 */

public class UserProvider extends ContentProvider {
    private static UriMatcher matcher=
            new UriMatcher(UriMatcher.NO_MATCH);
    private static  final int USERID=1;
    private static  final int USERMSG=2;
    private UserDBH userDbh;
    private UserDB userdb;
    static {
        matcher.addURI(Users.AUTHORITY,"userid",USERID);
        matcher.addURI(Users.AUTHORITY,"usermsg/#",USERMSG);
    }


    @Override
    public boolean onCreate() {
        userDbh=new UserDBH(this.getContext(),"userMsg.db3",null,1);
        return true;
    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)){
            case USERID:
                return "vnd.android.cursor.dir/com.example.user";
            case USERMSG:
                return "vnd.android.cursor.item/com.example.user";
            default:
                throw new IllegalArgumentException("未知Uri:"+uri);
        }

    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db=userDbh.getReadableDatabase();
        switch (matcher.match(uri)){
            case USERID:
                return db.query("user",strings,s,strings1,null,null,s1);
            case USERMSG:
                long id= ContentUris.parseId(uri);
                String whereClause =Users.User.U_ID+"="+id;
                if(s!=null&& !"".equals(s)){
                    whereClause=whereClause+"and"+s;
                }
                return db.query("user",strings,whereClause,strings1,null,null,s1);
            default:
                throw new IllegalArgumentException("未知Uri:"+uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db=userDbh.getReadableDatabase();
        switch (matcher.match(uri)){
            case USERID:
                long rowID=db.insert("user",Users.User.U_ID,contentValues);
                if(rowID>0){
                    Uri userUri=ContentUris.withAppendedId(uri,rowID);
                    getContext().getContentResolver().notifyChange(userUri,null);
                    return userUri;
                }
                break;
            case USERMSG:
               break;
            default:
                throw new IllegalArgumentException("未知Uri:"+uri);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    /*contentResolve对象不识别这个方法
    public long insert(Context context, Uri uri, String u_ids, String u_pwds){
        long num=0;
        userdb=UserDB.getInstance(context);
        switch (matcher.match(uri)){
            case USERID:
                num=userdb.insertUser(u_ids,u_pwds);
                break;
            default:
                throw new IllegalArgumentException("未知Uri:"+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return num;
    }*/
}
