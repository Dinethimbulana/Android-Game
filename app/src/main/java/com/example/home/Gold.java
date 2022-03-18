package com.example.home;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.home.GameView.screenRatioX;
import static com.example.home.GameView.screenRatioY;


public class Gold {

    public int speed = 20;
    int x = 0, y, width, height, goldCounter = 1;
    Bitmap goldKey, goldSnitch;

    Gold (Resources res) {

        goldKey = BitmapFactory.decodeResource(res, R.drawable.flyinggoldkey);
        goldSnitch = BitmapFactory.decodeResource(res, R.drawable.goldensnitch);

        width = goldKey.getWidth();
        height = goldKey.getHeight();

        width /= 6;
        height /= 6;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        goldKey = Bitmap.createScaledBitmap(goldKey, width, height, false);
        goldSnitch = Bitmap.createScaledBitmap(goldSnitch, width, height, false);

        y = -height;

    }

    Bitmap getGold () {
        if (goldCounter<4){
            goldCounter++;
            return goldSnitch;
        }
        goldCounter = 1;
        return goldKey;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
}


