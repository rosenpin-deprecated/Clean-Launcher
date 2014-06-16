package com.assist.me.launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by tomer on 02/06/14.
 */

public class AppClickListener implements View.OnClickListener {
    MainActivity.Pac[] pacsForListener;
    Context mContext;

    public AppClickListener(MainActivity.Pac[] pacs, Context ctxt){
        pacsForListener = pacs;
        mContext = ctxt;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        String[] data;
        data= (String[]) v.getTag();

        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cp = new ComponentName(data[0], data[1]);
        launchIntent.setComponent(cp);
        mContext.startActivity(launchIntent);

    }

}