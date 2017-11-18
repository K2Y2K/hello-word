package com.example.lidongxue.chat2con;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView show;
    Button send;
    Handler handler;
    ClientThread clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input= (EditText) findViewById(R.id.input);
        send= (Button) findViewById(R.id.send);
        show= (TextView) findViewById(R.id.show);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
               if(msg.what==0x123){
                  show.append("\n"+msg.obj.toString());
                  // show.setText("\n"+msg.obj.toString());
               }
            }
        };
        clientThread =new ClientThread(handler);
        new Thread(clientThread).start();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Log.d("---input.getText()---", input.getText().toString());
                    if(input.getText().toString()!=null) {
                        Log.d("---input.getText()1---", input.getText().toString());
                        Message msg = new Message();
                        msg.what = 0x345;
                        msg.obj = input.getText().toString();
                        Log.d("---input.getText()3---", msg.obj.toString());
                        clientThread.revHandler.sendMessage(msg);
                        input.setText("");
                        Log.d("------", "发送信息　文本框设置为空");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
