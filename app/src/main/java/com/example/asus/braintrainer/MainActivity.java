package com.example.asus.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//
    Button button,button2,button3,button4,button5,button6;
//    GridLayout gridLayout;

    TextView timerTextView,quesTextView,scoreTextView,resultTextView;
    int randomoption,score=0,noofquestion=0;
    CountDownTimer countDownTimer;
    ConstraintLayout gameConstraintLayout;


    public void playAgain(View view)
    {
        score=0;
        noofquestion=0;
     timerTextView.setText("30s");
     scoreTextView.setText("0/0");
     resultTextView.setVisibility(View.VISIBLE);
     resultTextView.setText("");
     button6.setVisibility(View.INVISIBLE);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
     changeQuestion();
        countDownTimer=new CountDownTimer(30200,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Values Left!!",String.valueOf((int)millisUntilFinished/1000));
                timerTextView.setText(String.valueOf((int)millisUntilFinished/1000)+'s');
            }

            @Override
            public void onFinish() {
                button6.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.INVISIBLE);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                button5.setEnabled(false);
            }
        }.start();

    }


    public void changeQuestion()
    {   ArrayList<Integer> answer=new ArrayList<Integer>();
        Random rand=new Random();
        int a=rand.nextInt(20)+1;
        int b=rand.nextInt(20)+1;

        quesTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        int correctAns=a+b;

        randomoption=rand.nextInt(4);

        for(int i=0;i<4;i++)
        {
            if(i==randomoption)
            {
                answer.add(a+b);
            }
            else
            {
                int wronganswer=rand.nextInt(50);
                while(wronganswer==a+b)
                {
                    wronganswer=rand.nextInt(50);
                }
                answer.add(wronganswer);
            }
        }
        button2.setText(Integer.toString(answer.get(0)));
        button3.setText(Integer.toString(answer.get(1)));
        button4.setText(Integer.toString(answer.get(2)));
        button5.setText(Integer.toString(answer.get(3)));

    }
    public void checkOption(View view)
    {
//        clickButton=(Button)view;
        Log.i("Tag Value!",view.getTag().toString());

        if(randomoption==Integer.valueOf(view.getTag().toString()))
        {

            resultTextView.setText("Correct!");
            score++;

        }
        else
        {
            resultTextView.setText("Wrong!");
        }
        noofquestion++;
        scoreTextView.setText(String.valueOf(score)+'/'+String.valueOf(noofquestion));
        changeQuestion();

    }
    public void start(View view)
    {
        button=(Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(View.VISIBLE);
        playAgain(button2);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView=(TextView)findViewById(R.id.timerTextView);
        quesTextView=(TextView)findViewById(R.id.quesTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        gameConstraintLayout=(ConstraintLayout)findViewById(R.id.gameConstraintLayout);
         button2=(Button)findViewById(R.id.button2);
         button3=(Button)findViewById(R.id.button3);
         button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);


    }
}
