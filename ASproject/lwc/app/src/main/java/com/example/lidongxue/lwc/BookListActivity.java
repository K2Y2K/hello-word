package com.example.lidongxue.lwc;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by lidongxue on 17-9-20.
 */

public class BookListActivity extends Activity implements BookListFragment.Callbacks {
    private boolean mTwoPane;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        FrameLayout frameLayout= findViewById(R.id.book_detail_container);
        if(frameLayout!=null){
            Log.d("tag","true");
            mTwoPane=true;
            ((BookListFragment)getFragmentManager().findFragmentById(R.id.book_list)).setActivateOnItemClick(true);
        }
        else{
            Log.d("------tag","false");
        }
    }

    @Override
    public void onItemSelected(Integer id) {
        if(mTwoPane){
            Bundle arguments=new Bundle();
            arguments.putInt(BookDetailFragment.ITEM_ID,id);
            BookDetailFragment fragment=new BookDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction().replace(R.id.book_detail_container,fragment).commit();
        }else{
            Intent detailIntent =new Intent(this, BookDetailActivity.class);
            detailIntent.putExtra(BookDetailFragment.ITEM_ID,id);
            startActivity(detailIntent);
        }
    }
}
