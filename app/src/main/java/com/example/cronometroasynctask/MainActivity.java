package com.example.cronometroasynctask;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private ReverseChronometer reverseChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reverseChronometer = (ReverseChronometer) findViewById(R.id.chronometer);

        //reverseChronometer.setOverallDuration(120);
        //reverseChronometer.setText("Valor cambiado en Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reverseChronometer.run();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reverseChronometer.stop();
    }


}
