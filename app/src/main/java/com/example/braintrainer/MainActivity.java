package com.example.braintrainer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView question;
    TextView score;
    TextView decision;
    TextView timer;
    Button startButton;
    static int totalcount,crtcount,lives;
    int x,y;
    int randomNumbers[];
    int location_of_crt_ans;
    CountDownTimer countDownTimer;
    int tmp;
    Button b1 ;
    Button b2 ;
    Button b3 ;
    Button b4 ;
    public void set_random(){
        Random rand = new Random();
        x = rand.nextInt(101);
        y = rand.nextInt(101);
        location_of_crt_ans = rand.nextInt(4);
        question.setText(Integer.toString(x) + " + " + Integer.toString(y));
        for (int i = 0; i < 4; i++) {
            if (i != location_of_crt_ans) {
                int k = rand.nextInt(201);
                while (k == x + y) {
                    k = rand.nextInt(201);
                }
                randomNumbers[i] = k;
            } else {
                randomNumbers[i] = x + y;
            }

        }
        b1.setText(Integer.toString(randomNumbers[0]));
        b2.setText(Integer.toString(randomNumbers[1]));
        b3.setText(Integer.toString(randomNumbers[2]));
        b4.setText(Integer.toString(randomNumbers[3]));
    }
    public void setTimer(){
        if(tmp>0){
            countDownTimer.cancel();
        }
        countDownTimer=new CountDownTimer(31000 - (crtcount * 1000), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int) millisUntilFinished / 1000)+"s");
            }
            @Override
            public void onFinish() {
                totalcount++;
                decision.setText(Integer.toString(crtcount)+ " correct out of "+Integer.toString(totalcount));
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                question.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);


            }
        }.start();
        tmp++;
        set_random();
    }
    public void checkAnswer(View view){
            if (x + y == randomNumbers[Integer.parseInt(view.getTag().toString())]) {
                decision.setText("Correct");
                crtcount++;
            } else {
                decision.setText("Wrong");
                lives--;
            }
            totalcount++;
           if (lives <= 0) {
                decision.setText(Integer.toString(crtcount)+ " correct out of "+Integer.toString(totalcount));
                b1.setVisibility(View.INVISIBLE);
               b2.setVisibility(View.INVISIBLE);
               b3.setVisibility(View.INVISIBLE);
               b4.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                question.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);
            }
           score.setText(Integer.toString(lives));
            countDownTimer.cancel();
            setTimer();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            question = (TextView) findViewById(R.id.questionTextView);
            decision = (TextView) findViewById(R.id.answer);
            score = (TextView) findViewById(R.id.scoretextView);
            timer=(TextView)findViewById(R.id.timeTextView);
            startButton=(Button)findViewById(R.id.Start);
            randomNumbers = new int[4];
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
            tmp=0;
        }
    public void start(View view){
        totalcount=0;
        crtcount=0;
        lives=3;
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);
        view.setVisibility(View.INVISIBLE);
        question.setVisibility(View.VISIBLE);
        decision.setVisibility(View.VISIBLE);
        decision.setText("");
        score.setText(Integer.toString(lives));
        score.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        setTimer();
        tmp=0;

    }
}