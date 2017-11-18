package com.example.lidongxue.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    /*int [] im=new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
    };
    int cur=0;*/
  /*  private int[] data=new int[100];
    int hasDate =0;
    int status=0;
    ProgressBar bar ,bar2;
    Handler mHandler= new Handler(){

        public void  handleMessage(Message msg){
            if(msg.what==0x111){
                bar.setProgress(status);
                bar2.setProgress(status);
            }
        }
    };*/

    private int speed=10;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


     /*
        setContentView(R.layout.main_probar);
        Button bn1= (Button) findViewById(R.id.bn1);
        Button bn2= (Button) findViewById(R.id.bn2);

        bar = (ProgressBar) findViewById(R.id.bar);
        bar2= (ProgressBar) findViewById(R.id.bar1);
        new Thread(){
            @Override
            public void run() {
                while (status<100){
                    status=doWork();
                    mHandler.sendEmptyMessage(0x111);
                }
            }
        }.start();*/
        /*LinearLayout l= (LinearLayout) findViewById(R.id.root);
        final DrawView draw=new DrawView(this);
        draw.setMinimumHeight(100);
        draw.setMinimumWidth(80);
        l.addView(draw);*/
       /* LinearLayout layou =new LinearLayout(this);
        super.setContentView(layou);
        layou.setOrientation(LinearLayout.VERTICAL);
        final TextView show =new TextView(this);
        Button bn =new Button(this);
        bn.setText(R.string.ok);
        bn.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        layou.addView(show);
        layou.addView(bn);
        bn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v ){
                show.setText("hello,hhh"+new java.util.Date());
            }

        });*/
    }


  /*  public int doWork(){
        data[hasDate++]= (int) (Math.random()*100);
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return hasDate;
    }*/
}
