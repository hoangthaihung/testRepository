package example.android.com.surfaceviewdemo;

import android.app.Activity;
import android.os.Bundle;

//http://android-er.blogspot.com/2014/03/simple-surfaceview-example.html

//https://www.simplifiedcoding.net/android-game-development-tutorial-1/

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
       /* MyThread myThread = new MyThread(new MySurfaceView(this));
        myThread.run();*/
    }
}
