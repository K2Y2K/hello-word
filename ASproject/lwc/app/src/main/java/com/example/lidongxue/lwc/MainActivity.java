package com.example.lidongxue.lwc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BookListFragment.Callbacks{
/*
* 该activity没有bug
* 实现了在一个activity里加载两个fragment,其中一个为自定义的fragment;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_twopane);
    }

    @Override
    public void onItemSelected(Integer id) {
        Bundle arguments=new Bundle();
        arguments.putInt(BookDetailFragment.ITEM_ID,id);
        BookDetailFragment fragment=new BookDetailFragment();
        fragment.setArguments(arguments);
        getFragmentManager().beginTransaction().replace(R.id.book_detail_container,fragment).commit();
    }
}
