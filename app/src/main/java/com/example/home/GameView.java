package com.example.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.security.keystore.StrongBoxUnavailableException;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable{

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private Background background1, background2;
    private int screenX, screenY, score = 0;
    private Paint paint;
    public static float screenRatioX, screenRatioY;
    private FlyingPotter flyingPotter;
    private Key[] keys;
    private Random random;
    private Gold gold;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080 / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        flyingPotter = new FlyingPotter(screenY, getResources());

        background2.x = screenX;

        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);

        keys = new Key[4];

        for (int i = 0;i < 4;i++){
            Key key = new Key(getResources());
            keys[i] = key;
        }

        gold = new Gold(getResources());

        random = new Random();


    }

    @Override
    public void run(){

        while (isPlaying){

            update();
            draw();
            sleep();
        }

    }

    private void update(){

        if (flyingPotter.isGoingUp)
            flyingPotter.y -= 30 * screenRatioY;
        else
            flyingPotter.y += 30 * screenRatioY;

        if (flyingPotter.y < 0)
            flyingPotter.y =  0;

        if (flyingPotter.y >= screenY - flyingPotter.height)
            flyingPotter.y = screenY - flyingPotter.height;

        for (Key key : keys){
            key.x -= key.speed;

            if (key.x + key.width < 0) {
                int bound = (int) (30 * screenRatioX);
                key.speed = random.nextInt(bound);

                if (key.speed < 10 * screenRatioX)
                    key.speed = (int) (10 * screenRatioX);

                key.x = screenX;
                int p = (screenY - (key.height))/2;
                key.y = random.nextInt(random.nextInt(screenY - (key.height-250)));


            }
            gold.x -= gold.speed;

            if(gold.x + gold.width < 0){
                int bound1 = (int) (30 * screenRatioX);
                gold.speed = random.nextInt(bound1);

                if(gold.speed < 10 * screenRatioX)
                    gold.speed = (int) (10 * screenRatioX);

                gold.x = screenX;
                int q = screenY / 2;
                gold.y = random.nextInt(q);
            }

            if (Rect.intersects(key.getCollisionShape(), flyingPotter.getCollisionShape())) {
                isGameOver = true;
                return;
            }
            if(Rect.intersects(gold.getCollisionShape(), flyingPotter.getCollisionShape())) {
                score++;
                gold.x = -300;
            }
        }
    }

    private void draw(){

        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

            if(isGameOver){
                isPlaying = false;
                canvas.drawBitmap(flyingPotter.getDead(), screenX / 10, 80, paint);
                canvas.drawText("Your Score: " + score + "", screenX / 2, screenY / 2, paint);
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }

            for (Key key : keys)
                canvas.drawBitmap(key.getKey(), key.x, key.y, paint);

            canvas.drawBitmap(flyingPotter.getFlyingpotter(), flyingPotter.x, flyingPotter.y, paint);

            canvas.drawBitmap(gold.getGold(), gold.x, gold.y, paint);

            canvas.drawText(score + "", screenX / 2, 164, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void sleep(){
        try{
            Thread.sleep(30); //17 thibbe mm wens kala 30ta
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void resume(){

        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause(){

        try{
            isPlaying = false;
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flyingPotter.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flyingPotter.isGoingUp = false;
                break;

        }
        return true;
    }

}
