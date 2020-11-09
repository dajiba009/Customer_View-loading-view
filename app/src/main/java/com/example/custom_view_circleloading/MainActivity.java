package com.example.custom_view_circleloading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    private float max = 100.0f;

    private float current = 0.0f;

    CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniview();
    }

    private void iniview() {
//        final TestCustomView testCustomView = findViewById(R.id.test_color);
//        testCustomView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                testCustomView.startAnimation(Color.BLUE,Color.RED);
//            }
//        });
//
//        mButton = findViewById(R.id.argbevaluator_color);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                current = 0.0f;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while(current <= max){
//                            float precent = current / max;
//                            //testCustomView.argbChange(newCurrent);
//                            testCustomView.gradientColor(precent,Color.BLUE,Color.RED);
//                            current += 10f;
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }).start();
//            }
//        });


        circleView = findViewById(R.id.circle_view);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(current <= max){
                            float precent = current / max;
                            circleView.drawProgress(precent);
                            current += 10f;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}