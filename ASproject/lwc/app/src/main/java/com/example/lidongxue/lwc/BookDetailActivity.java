package com.example.lidongxue.lwc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * Created by lidongxue on 17-9-20.
 */

public class BookDetailActivity extends AppCompatActivity{
    /*
        该程序运行报错　应该是版本问题　造成的不能引用私有包
        Before Android 4.1, method int android.support.v7.widget.ListViewCompat.lookForSelectablePosition(int, boolean) would have incorrectly
        overridden the package-private method in android.widget.ListView
    */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
       //有bug:Attempt to invoke virtual method 'void android.app.ActionBar.setDisplayHomeAsUpEnabled(boolean)' on a null object reference
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("------Ｓtate----", String.valueOf(savedInstanceState));

        if(savedInstanceState==null){
            BookDetailFragment fragment=new BookDetailFragment();
            Bundle arguments=new Bundle();
            arguments.putInt(BookDetailFragment.ITEM_ID,getIntent().getIntExtra(BookDetailFragment.ITEM_ID,0));
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction().add(R.id.book_detail_container,fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            Intent intent=new Intent(this,BookListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
