package com.example.lidongxue.lwc;

import android.app.Activity;
import android.app.ListFragment;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by lidongxue on 17-9-20.
 */

public class BookListFragment extends ListFragment {
    private Callbacks mcs;
    public  interface  Callbacks{
        public void onItemSelected(Integer id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(),
                android.R.layout.simple_list_item_activated_1,android.R.id.text1,BookContent.ITEMS));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof Callbacks)){
            throw new IllegalStateException("BookListFragment必须实现Callbacks接口");
        }
        mcs = (Callbacks)activity;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mcs = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        mcs.onItemSelected(BookContent.ITEMS.get(position).id);
    }
    public void setActivateOnItemClick(boolean activateOnItemClick){
        getListView().setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE
        );
    }
}
