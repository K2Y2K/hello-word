package com.example.lidongxue.chattest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by lidongxue on 17-10-9.
 */

public class ResultActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        ListView listView= (ListView) findViewById(R.id.show);
        Bundle data=getIntent().getExtras();
        List<Map<String,String>> list=(List<Map<String,String>>)data.getSerializable("data");
        SimpleAdapter adapter=new SimpleAdapter(ResultActivity.this,list,R.layout.line,
                new String[]{"u_id","u_pwd"},new int[]{R.id.u_ids,R.id.pwds});
        listView.setAdapter(adapter);
    }


}
