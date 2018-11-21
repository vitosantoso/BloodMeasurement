package inphosoft.vito.bloodmeasurement;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StartCountdown extends AppCompatActivity {

    int pStatus = 0;
    private Handler handler = new Handler();
    private Button resetButton;
    TextView percentage;
    ProgressBar mProgress;
    Boolean keepRunning;
    long timer;
    TextView countdownTimer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circle);

        mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(1);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        percentage = (TextView) findViewById(R.id.percentage);
        countdownTimer = (TextView) findViewById(R.id.countdownTimer);

        runThread();
        startCountdown();

        /*
        if(keepRunning){
            runThread();
        } */

        resetButton = (Button) findViewById(R.id.buttonCancel);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //runThread();
                Intent intent = new Intent(StartCountdown.this, MainActivity.class);
                stopThread();
                startActivity(intent);
            }
        });

    }

    private void runThread(){
        keepRunning = true;
        timer = 1800;
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100 && keepRunning) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            percentage.setText(pStatus + "%");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(timer); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void stopThread(){
        pStatus = 0;
        percentage.setText("0%");
        mProgress.setProgress(0);
        keepRunning = false;
    }

    private void startCountdown(){
        new CountDownTimer(180000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished / 60000 >= 1){
                    countdownTimer.setText("This will take about " + ((millisUntilFinished / 60000) + 1) + " minutes");
                } else if(millisUntilFinished / 60000 < 1){
                    countdownTimer.setText("This will take about " + ((millisUntilFinished / 60000) + 1) + " minute");
                }

            }

            @Override
            public void onFinish() {
                countdownTimer.setText("Done!");
                resetButton.setText("Done!");
            }
        }.start();
    }
}
