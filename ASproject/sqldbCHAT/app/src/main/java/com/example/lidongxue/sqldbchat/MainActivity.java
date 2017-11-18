package com.example.lidongxue.sqldbchat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.lidongxue.sqldbchat.database.UserDB;

public class MainActivity extends AppCompatActivity {


    UserDB userdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userdb=UserDB.getInstance(MainActivity.this);
        String uid=((EditText)findViewById(R.id.u_id)).getText().toString();
        System.out.print(MainActivity.this.getCacheDir().toString());
        Log.d("---缓存路径----",MainActivity.this.getCacheDir().toString());
        System.out.print("当前路径"+MainActivity.this.getFilesDir().toString());
        Log.d("---当前路径----",MainActivity.this.getFilesDir().toString());
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //删除用户表中所有数据
               // userdb.deleteUserMsgs();
                /*userdb.insertUser("u_id",((EditText)findViewById(R.id.u_id)).getText().toString());
                userdb.insertUser("u_pwd",((EditText)findViewById(R.id.userpwd)).getText().toString());*/
                userdb.insertUser(((EditText)findViewById(R.id.u_id)).getText().toString(),((EditText)findViewById(R.id.userpwd)).getText().toString());
                Log.d("---1----","添加成功");
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursora= userdb.queryUserMsgs();
                Log.d("查询22返回行和列的个数:","行数"+cursora.getCount()+"列数 "+
                        cursora.getColumnCount());
                Cursor cursorb= userdb.queryUserMsg(((EditText)findViewById(R.id.u_id)).getText().toString());
                Log.d("查询33返回行和列的个数:","行数"+cursorb.getCount()+"列数 "+
                        cursorb.getColumnCount());

                Bundle data=new Bundle();
                //data.putSerializable("data",userdb.converCursorToList(cursora));

                data.putSerializable("data",userdb.converCursorToList(userdb.queryUserMsg(((EditText)findViewById(R.id.u_id)).getText().toString())));
                //data.putSerializable("data",userdb.converCursorToList(userdb.queryUserMsgs()));
                Intent intent =new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);

               /* String ins=((EditText)findViewById(R.id.u_id)).getText().toString();
                Log.d("----2---",((EditText)findViewById(R.id.u_id)).getText().toString());
                Log.d("----3---", String.valueOf(userdb.queryUserMsgs()));
                Log.d("----41---",ins);
                Log.d("----4---",(userdb.queryUserPwd(ins)));
                Log.d("----5---", String.valueOf((userdb.converCursorToList(userdb.queryUserMsgs()))));*/
              /*  ((EditText)findViewById(R.id.query_pwd)).
                        setText(userdb.queryUserPwd(((EditText)findViewById(R.id.query_data)).getText().toString()));*/
               // String key =((EditText)findViewById(R.id.query_data)).getText().toString();

            }
        });
    }
}
