package example.android.com.surfaceviewdemo;

import android.graphics.Canvas;

/**
 * Created by dale on 8/2/2016.
 */
public class MyThread extends Thread {
    MySurfaceView myView;
    private boolean running = false;

    public MyThread(MySurfaceView _view) {
        this.myView = _view;
    }

    public void setRunning(boolean _run) {
        this.running = _run;
    }

    @Override
    public void run() {
        while(running) {
            Canvas canvas = myView.getHolder().lockCanvas();

            if (canvas != null){
                synchronized (myView.getHolder()) {
                    myView.drawSomething(canvas);
                }
                myView.getHolder().unlockCanvasAndPost(canvas);
            }
        }

        try {
            sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
