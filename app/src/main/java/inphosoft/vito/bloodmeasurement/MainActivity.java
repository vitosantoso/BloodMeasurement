package inphosoft.vito.bloodmeasurement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv;


    private Button startButton;

    ProgressBar mProgress;
    Boolean keepRunning;
    long timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circle);

        mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(1);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/

        tv = (TextView) findViewById(R.id.tv);

        /* Comment sementara buat tombol reset
        resetButton = (Button) findViewById(R.id.buttonReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pStatus = 0;
                tv.setText("0%");
                mProgress.setProgress(0);
                keepRunning = false;
            }
        });
        */

        startButton = (Button) findViewById(R.id.buttonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //runThread();
                //Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                Intent intent = new Intent(MainActivity.this, StartCountdown.class);
                keepRunning = true;
                startActivity(intent);
            }
        });
    }


}


