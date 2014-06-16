package com.assist.me.launcher;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by tomer on 02/06/14.
 */

public class AppTouchListener implements View.OnTouchListener {
    int iconSize;

    public AppTouchListener(int size) {
        iconSize = size;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(iconSize,iconSize);
                lp.leftMargin = (int) event.getRawX()-iconSize/2;
                lp.topMargin = (int) event.getRawY()-iconSize/2;
                v.setLayoutParams(lp);


        }
        return true;
    }

}