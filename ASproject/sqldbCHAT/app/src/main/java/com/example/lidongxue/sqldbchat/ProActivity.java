package com.example.lidongxue.sqldbchat;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lidongxue.sqldbchat.content.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongxue on 17-10-10.
 */

public class ProActivity extends AppCompatActivity {
    ContentResolver contentResolver;
    Button insert=null;
    Button search=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        contentResolver=getContentResolver();
        insert= (Button) findViewById(R.id.insert);
        search= (Button) findViewById(R.id.search);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  word=((EditText)findViewById(R.id.word)).getText().toString();
                String  detail=((EditText)findViewById(R.id.detail)).getText().toString();
                ContentValues values=new ContentValues();
                values.put(Users.User.U_ID,word);
                values.put(Users.User.U_PWD,detail);

                contentResolver.insert(Users.User.USERID_CONTENT_URI,values);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=((EditText)findViewById(R.id.key)).getText().toString();
                Cursor cursor=contentResolver.query(Users.User.USERID_CONTENT_URI,null,
                        " u_id like ? or u_pwd like ? ",
                        new String[]{"%"+key+"%","%"+key+"%"},null);
                Bundle data =new Bundle();
                data.putSerializable("data",converCursorToList(cursor));
                Intent intent =new Intent(ProActivity.this,ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);

            }
        });
    }

    protected ArrayList<Map<String,String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String,String>> result=new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()){
            Map<String,String> map=new HashMap<>();
            map.put(Users.User.U_ID,cursor.getString(0));
            map.put(Users.User.U_PWD,cursor.getString(2));
            result.add(map);
        }
        return result;
    }
}
