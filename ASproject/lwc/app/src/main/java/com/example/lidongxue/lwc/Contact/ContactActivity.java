package com.example.lidongxue.lwc.Contact;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lidongxue.lwc.R;

/**
 * Created by lidongxue on 17-9-21.
 */

public class ContactActivity extends Activity {
    final int PICK_CONTACT=0;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_contact);
        Button bn= findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("vnd.android.cursor.item/phone");
                startActivityForResult(intent,PICK_CONTACT);
            }
        });
    }
    /*
    bug出在android6之后获取通讯录的权限和之前的不一样，需要申请权限，该问题没有解决
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case(PICK_CONTACT):
                if(resultCode==Activity.RESULT_OK){
                    Uri contactData =data.getData();
                    CursorLoader cursorLoader=new CursorLoader(this,contactData,null,null,null,null);
                    Cursor cursor=cursorLoader.loadInBackground();
                    if(cursor.moveToFirst()){
                        String contactId=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name =cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber="此联系人尚未输入电话号码";
                        Cursor phones=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ "=" +contactId,null,null);
                        if(phones.moveToFirst()){
                            phoneNumber=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }
                        phones.close();
                        EditText show=(EditText) findViewById(R.id.show);
                        show.setText(name);
                        EditText phone=(EditText)findViewById(R.id.phone);
                        phone.setText(phoneNumber);
                    }
                    cursor.close();
                }
                break;
        }
    }
}
