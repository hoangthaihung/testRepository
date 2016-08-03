package example.android.com.surfaceviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dale on 8/2/2016.
 */
public class MySurfaceView extends SurfaceView {
    private SurfaceHolder surfaceHolder;
    private MyThread myThread;
    MainActivity mainActivity;

    long timeStart;
    long timeA;
    long timeB;
    long timeFillBackground;
    long timeDrawBitmap;
    long timeTotal;
    long numberOfPt;

    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context _context) {
        mainActivity = (MainActivity) _context;
        numberOfPt = 0;
        myThread = new MyThread(this);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                myThread.setRunning(true);
                myThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join(); // main (UI) thread, want to wait for myThread finishing
                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private Bitmap prepareBitmap_A(int w, int h, long count) {
        int[] data = new int[w * h];
        // fill with dummy data
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++) {
                if (count >= 0) {
                    data[x + y * w] = 0xFFff0000;
                    count--;
                } else {
                    data[x + y * w] = 0xFFa0a0a0;
                }
            }

        timeA = System.currentTimeMillis();
        Bitmap bm = Bitmap.createBitmap(data, w, h, Bitmap.Config.ARGB_8888);
        timeB = System.currentTimeMillis();
        return bm;
    }

    private Bitmap prepareBitmap_B(int w, int h, int count) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        timeA = System.currentTimeMillis();

        // fill with dummy data
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++) {
                if (count >= 0) {
                    bm.setPixel(x, y, 0xFFff0000);
                    count--;
                } else {
                    bm.setPixel(x, y, 0xFFa0a0a0);
                }
            }

        timeB = System.currentTimeMillis();
        return bm;
    }

    protected void drawSomething(Canvas canvas) {
        numberOfPt += 500;
        if (numberOfPt > (long) (getWidth() * getHeight())) {
            numberOfPt = 0;
        }

        timeStart = System.currentTimeMillis();
        Bitmap bmDummy = prepareBitmap_A(getWidth(), getHeight(), numberOfPt);
        canvas.drawColor(Color.BLACK);
        timeFillBackground = System.currentTimeMillis();
        canvas.drawBitmap(bmDummy, 0, 0, null);
        timeDrawBitmap = System.currentTimeMillis();

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.showDuration(
                        timeA - timeStart,
                        timeB - timeA,
                        timeFillBackground - timeB,
                        timeDrawBitmap - timeFillBackground,
                        timeDrawBitmap - timeStart
                );
            }
        });

    }
}
