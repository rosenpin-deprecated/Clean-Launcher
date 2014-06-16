package com.assist.me.launcher;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.*;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {
    DrawerAdapter drawerAdapterObject;
    GridView drawerGrid;
    RelativeLayout homeView;
    int sdk = Build.VERSION.SDK_INT;
    class Pac{
        Drawable icon;
        String name;
        String packageName;
        String label;
    }
    Pac[] pacs;
    PackageManager pm;

    static boolean appLaunchable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerGrid = (GridView) findViewById(R.id.content);

        homeView = (RelativeLayout) findViewById(R.id.home_view);
        pm =getPackageManager();
        set_pacs();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        registerReceiver(new PacReceiver(), filter);
        if(sdk < Build.VERSION_CODES.KITKAT) {
            setTheme(android.R.style.Theme_Holo_NoActionBar_Fullscreen);
        }
        else{
            final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            final Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            drawerGrid.setBackground(wallpaperDrawable);
        }
    }

    public void set_pacs(){
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
        pacs = new Pac[pacsList.size()];
        for(int I=0;I<pacsList.size();I++){
            pacs[I]= new Pac();
            pacs[I].icon=pacsList.get(I).loadIcon(pm);
            pacs[I].packageName=pacsList.get(I).activityInfo.packageName;
            pacs[I].name=pacsList.get(I).activityInfo.name;
            pacs[I].label=pacsList.get(I).loadLabel(pm).toString();
        }
        new SortApps().exchange_sort(pacs);
        drawerAdapterObject = new DrawerAdapter(this, pacs);
        drawerGrid.setAdapter(drawerAdapterObject);
        drawerGrid.setOnItemClickListener(new DrawerClickListener(this, pacs, pm));
    }

    public class PacReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            set_pacs();
        }

    }

}