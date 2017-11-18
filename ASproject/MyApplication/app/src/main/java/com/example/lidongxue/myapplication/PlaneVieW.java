package com.example.lidongxue.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

/**
 * Created by lidongxue on 17-9-18.
 */

public class PlaneVieW extends View {
    public float curX;
    public float curY;
    Bitmap plane;
    public PlaneVieW(Context context) {
        super(context);
        plane= BitmapFactory.decodeResource(context.getResources(),R.drawable.a);

    }
}
