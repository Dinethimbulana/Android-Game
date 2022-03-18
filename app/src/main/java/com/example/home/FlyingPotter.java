package com.example.home;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.home.GameView.screenRatioX;
import static com.example.home.GameView.screenRatioY;

public class FlyingPotter {

    boolean isGoingUp = false;
    int  x, y, width, height;
    Bitmap flyingpotter, dead;

    FlyingPotter(int screenY, Resources res){

        flyingpotter = BitmapFactory.decodeResource(res, R.drawable.flyingpotter);

        width = flyingpotter.getWidth();
        height = flyingpotter.getHeight();

        width /= 4;
        height /= 4;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;


        flyingpotter = Bitmap.createScaledBitmap(flyingpotter, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.downpotter);
        dead = Bitmap.createScaledBitmap(dead, width + 350, height + 350, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);

    }

    Bitmap getFlyingpotter () {

        return flyingpotter;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

    Bitmap getDead () {
        return dead;
    }
}
