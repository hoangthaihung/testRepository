package example.android.com.surfaceviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

//http://android-er.blogspot.com/2014/03/simple-surfaceview-example.html

//https://www.simplifiedcoding.net/android-game-development-tutorial-1/

public class MainActivity extends Activity {
    TextView textDurA, textDurB, textDurFillBack, textDurDrawBM, textDurTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        textDurA = (TextView) findViewById(R.id.durA);
        textDurB = (TextView) findViewById(R.id.durB);
        textDurFillBack = (TextView) findViewById(R.id.durFillBack);
        textDurDrawBM = (TextView) findViewById(R.id.durDrawBM);
        textDurTotal = (TextView) findViewById(R.id.durTotal);
    }

    protected void showDuration(long dA, long dB, long dFill, long dDraw, long dTotal) {
        textDurA.setText("Duration(ms) - A: " + dA);
        textDurB.setText("Duration(ms) - B: " + dB);
        textDurFillBack.setText("Duration(ms) - Fill Background: " + dFill);
        textDurDrawBM.setText("Duration(ms) - drawBitmap: " + dDraw);
        textDurTotal.setText("Duration(ms) - Total: " + dTotal);
    }
}
