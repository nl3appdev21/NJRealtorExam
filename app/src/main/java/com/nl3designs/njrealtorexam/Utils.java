package com.nl3designs.njrealtorexam;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

public class Utils {

    static public void resizeImageHeight(ImageView ivImage, Activity actv) {

        // TODO : code to change menu inage based on os and screen size !!!

        Point size = new Point();
        WindowManager w = actv.getWindowManager();
        w.getDefaultDisplay().getSize(size);
        w.getDefaultDisplay().getSize(size);
        Display d = w.getDefaultDisplay();

        if(size.y <= 1920) {
            ivImage.getLayoutParams().height=250; // change height from 225dp to 250pix
        }

    }

}
