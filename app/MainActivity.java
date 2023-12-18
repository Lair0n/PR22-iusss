package com.example.pr_22_kopylov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private GridView mGrid;
    private GridAdapter mAdapter;
    private TextView mStepScreen;
    private Chronometer mTimeScreen;

    private Integer StepCount; // кол-во ходов
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGrid = findViewById(R.id.field);
        mGrid.setNumColumns(6);
        mGrid.setEnabled(true);

        mTimeScreen = (Chronometer) findViewById(R.id.timeview);
        mStepScreen = (TextView)findViewById(R.id.stepview);

        // шрифт
        Typeface type = Typeface.createFromAsset(getAssets(),"my-font.ttf");
        mTimeScreen.setTypeface(type);
        mStepScreen.setTypeface(type);

        StepCount = 0;
        mStepScreen.setText (StepCount.toString());

        mTimeScreen.start();

        mAdapter = new GridAdapter(this, 6,6);
        mGrid.setAdapter(mAdapter);

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id){

                mAdapter.checkOpenCells ();
                mAdapter.openCell (position);

                StepCount ++;
                mStepScreen.setText (StepCount.toString());

                if (mAdapter.checkGameOver())
                {
                    mTimeScreen.stop();
                    String time = mTimeScreen.getText().toString();
                    String TextToast = "Игра закончена nХодов: " + StepCount.toString() + "nВремя: " + time;
                    Toast.makeText (getApplicationContext(), TextToast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

