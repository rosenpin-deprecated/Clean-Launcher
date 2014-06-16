package com.assist.me.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by tomer on 02/06/14.
 */

public class DrawerClickListener implements AdapterView.OnItemClickListener {

    Context mContext;
    MainActivity.Pac[] pacsForAdapter;
    PackageManager pmForListener;

    public DrawerClickListener(Context c, MainActivity.Pac[] pacs, PackageManager pm){
        mContext=c;
        pacsForAdapter=pacs;
        pmForListener = pm;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
        if (MainActivity.appLaunchable){
            Intent launchIntent = new Intent(Intent.ACTION_MAIN);
            launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cp = new ComponentName(pacsForAdapter[pos].packageName, pacsForAdapter[pos].name);
            launchIntent.setComponent(cp);

            mContext.startActivity(launchIntent);
        }
    }

}