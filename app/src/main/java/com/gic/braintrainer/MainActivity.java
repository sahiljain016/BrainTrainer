package com.gic.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    Button AnswerButton1;
    Button AnswerButton2;
    Button AnswerButton3;
    Button AnswerButton4;
    TextView questionTextView;
    TextView TimerTextView;
    TextView ScoreTextView;
    TextView WelcomeTextView;
    TextView LetsGo;
    TextView Intro;
    ConstraintLayout cl;
    int Qrandom1;
    int Qrandom2;
    int sign;
    String m1;
    String m2;
    String m3;
    String m4;
    CountDownTimer cdt;
    int Qnumber=1;
    int Ranumber=1;
    int FinalRanumber;
    int FinalQnumber;

    //GENERATE RANDOM NUMBER METHOD
    public void GenRanNo() {

        Random rand = new Random();

        Qrandom1 = rand.nextInt(20) + 1;
        Qrandom2 = rand.nextInt(20) + 1;

        sign = rand.nextInt(4) + 1;

        if (sign == 1) {
            AnswerButton1.setTextColor(0xFFED280E);
            AnswerButton2.setTextColor(0xFFDD0DEC);
            AnswerButton3.setTextColor(0xFFE16201);
            AnswerButton4.setTextColor(0xFF00E5FF);

            questionTextView.setText(Qrandom1 + " " + "+" + " " + Qrandom2);

            String ansR = Integer.toString(Qrandom1 + Qrandom2);
            String ansW = Integer.toString(Qrandom1 + Qrandom2 + 2);
            String ansW1 = Integer.toString(Qrandom1 + Qrandom2 + 10 - Qrandom2);
            String ansW2 = Integer.toString(Qrandom1 + 10 - Qrandom2);

            AnswerButton1.setText(ansR);
            AnswerButton2.setText(ansW);
            AnswerButton3.setText(ansW1);
            AnswerButton4.setText(ansW2);

            m1 = "Correct";
            m2 = "Incorrect";
            m3 = "Incorrect";
            m4 = "Incorrect";

        } else if (sign == 2) {
            AnswerButton1.setTextColor(0xFFDD0DEC);
            AnswerButton2.setTextColor(0xFFED280E);
            AnswerButton3.setTextColor(0xFF00E5FF);
            AnswerButton4.setTextColor(0xFFE16201);

            questionTextView.setText(Qrandom1 + " " + "-" + " " + Qrandom2);

            String ansR = Integer.toString(Qrandom1 - Qrandom2);
            String ansW = Integer.toString(Qrandom1 - Qrandom2 + 1);
            String ansW1 = Integer.toString(Qrandom1 - Qrandom2 + 10 - Qrandom2);
            String ansW2 = Integer.toString(Qrandom1 - 10 - Qrandom2);

            AnswerButton2.setText(ansR);
            AnswerButton3.setText(ansW);
            AnswerButton4.setText(ansW1);
            AnswerButton1.setText(ansW2);

            m2 = "Correct";
            m1 = "Incorrect";
            m3 = "Incorrect";
            m4 = "Incorrect";
        } else if (sign == 3) {
            AnswerButton1.setTextColor(0xFFE16201);
            AnswerButton2.setTextColor(0xFF00E5FF);
            AnswerButton3.setTextColor(0xFFED280E);
            AnswerButton4.setTextColor(0xFFDD0DEC);

            questionTextView.setText(Qrandom1 + " " + "*" + " " + Qrandom2);

            String ansR = Integer.toString(Qrandom1 * Qrandom2);
            String ansW = Integer.toString(Qrandom1 * Qrandom2 + 10 - Qrandom2);
            String ansW1 = Integer.toString(Qrandom1 * Qrandom2 + 2);
            String ansW2 = Integer.toString(Qrandom1 * 10 - Qrandom2);

            AnswerButton3.setText(ansR);
            AnswerButton4.setText(ansW);
            AnswerButton2.setText(ansW1);
            AnswerButton1.setText(ansW2);

            m3 = "Correct";
            m2 = "Incorrect";
            m1 = "Incorrect";
            m4 = "Incorrect";
        } else if (sign == 4) {
            AnswerButton1.setTextColor(0xFF00E5FF);
            AnswerButton2.setTextColor(0xFFED280E);
            AnswerButton3.setTextColor(0xFFDD0DEC);
            AnswerButton4.setTextColor(0xFFE16201);

            questionTextView.setText(Qrandom1 + " " + "/" + " " + Qrandom2);

            String ansR = Integer.toString(Qrandom1 / Qrandom2);
            String ansW = Integer.toString(Qrandom1 / Qrandom2 - Qrandom2);
            String ansW1 = Integer.toString(Qrandom1 / Qrandom2 + 2);
            String ansW2 = Integer.toString(Qrandom1 / Qrandom2 + 10 - Qrandom2);

            AnswerButton4.setText(ansR);
            AnswerButton1.setText(ansW);
            AnswerButton2.setText(ansW1);
            AnswerButton3.setText(ansW2);

            m4 = "Correct";
            m1 = "Incorrect";
            m2 = "Incorrect";
            m3 = "Incorrect";
        }

    }

    //BACKGROUND METHODS
    public void setBgr() {

        cl.setBackgroundResource(R.drawable.greenbg);
        final Handler handler1r = new Handler();
        handler1r.postDelayed(new Runnable() {
            @Override
            public void run() {
                cl.setBackgroundResource(R.drawable.bluebg);
            }
        }, 700);

    }

    public void setBgw() {

        cl.setBackgroundResource(R.drawable.redbg);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                cl.setBackgroundResource(R.drawable.bluebg);
            }
        }, 700);

    }

    public void rightAnswer1() {
         MediaPlayer au1=MediaPlayer.create(MainActivity.this, R.raw.rightans);
        au1.start();


   }

    public void wrongtAnswer1() {
         MediaPlayer au2=MediaPlayer.create(MainActivity.this, R.raw.wrongans);
         au2.start();

}

// METHODS FOR COUNTDOWN
    public void countDownTimer(){

     cdt =   new CountDownTimer(15000, 1000){

            @Override
            public void onTick(long l) {


                updateTimer((int) l/1000);
            }

            @Override
            public void onFinish() {
MediaPlayer aww=MediaPlayer.create(MainActivity.this,R.raw.aww);
aww.start();
                AnswerButton1 = (Button) findViewById(R.id.ansb1);
                AnswerButton2 = (Button) findViewById(R.id.ansb2);
                AnswerButton3 = (Button) findViewById(R.id.ansb3);
                AnswerButton4 = (Button) findViewById(R.id.ansb4);
                questionTextView = (TextView) findViewById(R.id.Qtv);
                TimerTextView = (TextView) findViewById(R.id.Ttv);
                ScoreTextView = (TextView) findViewById(R.id.Stv);
                WelcomeTextView = (TextView) findViewById(R.id.Wtv);
                cl = (ConstraintLayout) findViewById(R.id.cf);
                LetsGo = (TextView) findViewById(R.id.lg);
                Intro = (TextView) findViewById(R.id.in);
                cl.setBackgroundResource(R.drawable.bg);
                AnswerButton1.setVisibility(INVISIBLE);
                AnswerButton2.setVisibility(INVISIBLE);
                AnswerButton3.setVisibility(INVISIBLE);
                AnswerButton4.setVisibility(INVISIBLE);
                questionTextView.setVisibility(INVISIBLE);
                TimerTextView.setVisibility(INVISIBLE);
                ScoreTextView.setVisibility(INVISIBLE);
                WelcomeTextView.setVisibility(INVISIBLE);
                LetsGo.animate().translationXBy(4000f).setDuration(2000);
                Intro.animate().translationXBy(4000f).setDuration(2000);
                LetsGo.setText("Play AGAIN!");
                GenRanNo();
              Ranumber=1;
              Qnumber=1;
ScoreTextView.setText("");
            }
        }.start();


    }
    public void updateTimer(int SecondsLeft){
        TextView timerTextView = (TextView) findViewById(R.id.Ttv);
        String sf = Integer.toString(SecondsLeft);
        timerTextView.setText(sf);

    }
    //METHOD FOR SCORES
    public void ScoreR(){
       FinalQnumber= Qnumber++;
       FinalRanumber=Ranumber++;
       ScoreTextView.setText(FinalRanumber + " / " + FinalQnumber );
    }
    public void ScoreW(){
        int FinalQnumber= Qnumber++;

        ScoreTextView.setText(FinalRanumber + " / " + FinalQnumber );
    }
    public void rightAnswer10(){
        String ra10=ScoreTextView.getText().toString();
        if(ra10.equals("10 / "+ FinalQnumber)){
            MediaPlayer au3=MediaPlayer.create(MainActivity.this, R.raw.ca10);
            au3.start();}

    }
    //BUTTONS IN GRID LAYOUT START FROM HERE-
    public void ans1(View view) {


        if (m1.equals("Correct")) {
            setBgr();
            rightAnswer1();
ScoreR();
        } else if (m2.equals("Incorrect") || m3.equals("Incorrect") || m4.equals("Incorrect")) {
            setBgw();
           wrongtAnswer1();
            ScoreW();
        }
        cdt.cancel();
        cdt.start();
        rightAnswer10();
        GenRanNo();
    }
    public void ans2(View view) {


        if (m2.equals("Correct")) {
            rightAnswer1();
            setBgr();
            ScoreR();

        } else if (m1.equals("Incorrect") || m3.equals("Incorrect") || m4.equals("Incorrect")){
             setBgw();
            wrongtAnswer1();
            ScoreW();
        }

        cdt.cancel();
        cdt.start();
        rightAnswer10();
        GenRanNo();
    }
    public void ans3(View view) {


        if (m3.equals("Correct")) {
            rightAnswer1();
             setBgr();
            ScoreR();
        } else if (m2.equals("Incorrect") || m1.equals("Incorrect") || m4.equals("Incorrect")){
            ScoreW();
            setBgw();
            wrongtAnswer1();
        }
        cdt.cancel();
        cdt.start();
        rightAnswer10();
        GenRanNo();
    }
    public void ans4(View view) {


        if (m4.equals("Correct")) {
            rightAnswer1();
            setBgr();
            ScoreR();
        } else if (m2.equals("Incorrect") || m3.equals("Incorrect") || m1.equals("Incorrect")){
            ScoreW();
            setBgw();
            wrongtAnswer1();
        }
        cdt.cancel();
        cdt.start();
        rightAnswer10();
        GenRanNo();
    }



    public void Start(View view){
        countDownTimer();
        AnswerButton1 = (Button) findViewById(R.id.ansb1);
        AnswerButton2 = (Button) findViewById(R.id.ansb2);
        AnswerButton3 = (Button) findViewById(R.id.ansb3);
        AnswerButton4 = (Button) findViewById(R.id.ansb4);
        questionTextView = (TextView) findViewById(R.id.Qtv);
        TimerTextView = (TextView) findViewById(R.id.Ttv);
        ScoreTextView = (TextView) findViewById(R.id.Stv);
        WelcomeTextView = (TextView) findViewById(R.id.Wtv);
        cl = (ConstraintLayout) findViewById(R.id.cf);
        LetsGo = (TextView) findViewById(R.id.lg);
        Intro = (TextView) findViewById(R.id.in);
        cl.setBackgroundResource(R.drawable.bluebg);
        AnswerButton1.setVisibility(VISIBLE);
        AnswerButton2.setVisibility(VISIBLE);
        AnswerButton3.setVisibility(VISIBLE);
        AnswerButton4.setVisibility(VISIBLE);
        questionTextView.setVisibility(VISIBLE);
        TimerTextView.setVisibility(VISIBLE);
        ScoreTextView.setVisibility(VISIBLE);
        WelcomeTextView.setVisibility(VISIBLE);
        LetsGo.animate().translationXBy(-4000f).setDuration(2000);
        Intro.animate().translationXBy(-4000f).setDuration(2000);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnswerButton1 = (Button) findViewById(R.id.ansb1);
         AnswerButton2 = (Button) findViewById(R.id.ansb2);
         AnswerButton3 = (Button) findViewById(R.id.ansb3);
         AnswerButton4 = (Button) findViewById(R.id.ansb4);
         questionTextView = (TextView) findViewById(R.id.Qtv);
         TimerTextView = (TextView) findViewById(R.id.Ttv);
         ScoreTextView = (TextView) findViewById(R.id.Stv);
         WelcomeTextView = (TextView) findViewById(R.id.Wtv);
     AnswerButton1.setVisibility(INVISIBLE);
        AnswerButton2.setVisibility(INVISIBLE);
        AnswerButton3.setVisibility(INVISIBLE);
        AnswerButton4.setVisibility(INVISIBLE);
        questionTextView.setVisibility(INVISIBLE);
       TimerTextView.setVisibility(INVISIBLE);
        ScoreTextView.setVisibility(INVISIBLE);
        WelcomeTextView.setVisibility(INVISIBLE);

GenRanNo();
    }
}