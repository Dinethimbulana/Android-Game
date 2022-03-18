package com.example.home;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.home.GameView.screenRatioX;
import static com.example.home.GameView.screenRatioY;

public class Key {

    public int speed = 20;
    int x = 0, y, width, height;
    Bitmap key1;

    Key (Resources res) {

        key1 = BitmapFactory.decodeResource(res, R.drawable.flyingkey);

        width = key1.getWidth();
        height = key1.getHeight();

        width /= 6;
        height /= 6;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        key1 = Bitmap.createScaledBitmap(key1, width, height, false);

        y = -height;

    }

    Bitmap getKey () {

        return key1;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}
