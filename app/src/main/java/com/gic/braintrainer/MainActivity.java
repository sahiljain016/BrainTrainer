package com.gic.braintrainer;

import android.content.Context;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.content.ContextCompat;

import com.gic.braintrainer.progress_bar.RoundedHorizontalProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    private AppCompatButton ACB_NEXT;
    private RoundedHorizontalProgressBar Progress_bar;
    private TextView TV_Q_COUNTER, TV_WRONG_COUNTER, TV_RIGHT_COUNTER, TV_CURRENT_Q, TV_Q, TV_Opt1, TV_Opt2, TV_Opt3, TV_Opt4;
    private MotionLayout ML_1, ML_2, ML_3, ML_4, ML_MAIN, ML_CV;
    private ImageView IV_MAIN, IV_Opt1, IV_Opt2, IV_Opt3, IV_Opt4;

    private LinkedHashMap<String, ArrayList<String>> HM_QA;
    private ArrayList<String> als_q_list;
    private ArrayList<String> als_correct_opt;
    private ArrayList<Integer> als_image_list;

    private int WrongCounter = 0, RightCounter = 0, QCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        HM_QA = new LinkedHashMap<>(5);
        als_correct_opt = new ArrayList<>(5);
        als_q_list = new ArrayList<>(5);
        als_image_list = new ArrayList<>(5);

        ACB_NEXT = findViewById(R.id.ACB_NEXT);
        Progress_bar = findViewById(R.id.PB_MAIN);
        TV_Q_COUNTER = findViewById(R.id.TV_Q_COMPLETED);
        TV_WRONG_COUNTER = findViewById(R.id.TV_WRONG_COUNTER);
        TV_RIGHT_COUNTER = findViewById(R.id.TV_TICK_COUNTER);
        TV_CURRENT_Q = findViewById(R.id.TV_CURRENT_Q);
        TV_Q = findViewById(R.id.TV_Q);
        TV_Opt1 = findViewById(R.id.TV_Opt1);
        TV_Opt2 = findViewById(R.id.TV_Opt2);
        TV_Opt3 = findViewById(R.id.TV_Opt3);
        TV_Opt4 = findViewById(R.id.TV_Opt4);
        ML_1 = findViewById(R.id.ML_1);
        ML_2 = findViewById(R.id.ML_2);
        ML_3 = findViewById(R.id.ML_3);
        ML_4 = findViewById(R.id.ML_4);
        ML_MAIN = findViewById(R.id.ML_MAIN);
        ML_CV = findViewById(R.id.ML_CV);
        IV_Opt1 = findViewById(R.id.IV_O1_SYMBOL);
        IV_Opt2 = findViewById(R.id.IV_O2_SYMBOL);
        IV_Opt3 = findViewById(R.id.IV_O3_SYMBOL);
        IV_Opt4 = findViewById(R.id.IV_O4_SYMBOL);
        IV_MAIN = findViewById(R.id.IV_IMAGE);
        initQALists();
        OnOptionSelection();
        OnNextClick();
    }

    private void OnNextClick() {
        ACB_NEXT.setOnClickListener(view -> {
            ResetAllOptions();
            ML_CV.setTransition(R.id.TRANS_RTL);
            ML_CV.transitionToEnd();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                ML_CV.setTransition(R.id.TRANS_LTR);
                ML_CV.transitionToEnd();
                ChangeQuestion();
            }, 1500);
        });
    }

    private void ResetAllOptions() {
        ML_1.setTransition(R.id.TRANS_OPT1);
        ML_1.transitionToStart();
        IV_Opt1.animate().alpha(0).start();
        ML_1.animate().alpha(1).start();
        TV_Opt1.setTextColor(getResources().getColor(R.color.opt_text_color));
        ML_1.setBackgroundTintList(null);
        ML_1.setBackgroundResource(R.drawable.bordered_bg_rounded_20dp);

        ML_2.setTransition(R.id.TRANS_OPT2);
        ML_2.transitionToStart();
        IV_Opt2.animate().alpha(0).start();
        ML_2.animate().alpha(1).start();
        TV_Opt2.setTextColor(getResources().getColor(R.color.opt_text_color));
        ML_2.setBackgroundTintList(null);
        ML_2.setBackgroundResource(R.drawable.bordered_bg_rounded_20dp);

        ML_3.setTransition(R.id.TRANS_OPT3);
        ML_3.transitionToStart();
        IV_Opt3.animate().alpha(0).start();
        ML_3.animate().alpha(1).start();
        TV_Opt3.setTextColor(getResources().getColor(R.color.opt_text_color));
        ML_3.setBackgroundTintList(null);
        ML_3.setBackgroundResource(R.drawable.bordered_bg_rounded_20dp);

        ML_4.setTransition(R.id.TRANS_OPT4);
        ML_4.transitionToStart();
        IV_Opt4.animate().alpha(0).start();
        ML_4.animate().alpha(1).start();
        TV_Opt4.setTextColor(getResources().getColor(R.color.opt_text_color));
        ML_4.setBackgroundTintList(null);
        ML_4.setBackgroundResource(R.drawable.bordered_bg_rounded_20dp);

        TV_Opt1.setEnabled(true);
        TV_Opt1.setClickable(true);
        TV_Opt2.setClickable(true);
        TV_Opt2.setEnabled(true);
        TV_Opt3.setClickable(true);
        TV_Opt3.setEnabled(true);
        TV_Opt4.setClickable(true);
        TV_Opt4.setEnabled(true);
    }

    private void ChangeQuestion() {
        QCounter++;
        SetDetails();
    }

    private void OnOptionSelection() {

        TV_Opt1.setOnClickListener(view -> OnOptionClick(R.id.TRANS_OPT1, 1, TV_Opt1, ML_1, IV_Opt1));

        TV_Opt2.setOnClickListener(view -> OnOptionClick(R.id.TRANS_OPT2, 2, TV_Opt2, ML_2, IV_Opt2));

        TV_Opt3.setOnClickListener(view -> OnOptionClick(R.id.TRANS_OPT3, 3, TV_Opt3, ML_3, IV_Opt3));

        TV_Opt4.setOnClickListener(view -> OnOptionClick(R.id.TRANS_OPT4, 4, TV_Opt4, ML_4, IV_Opt4));
    }


    private void OnOptionClick(Integer TRANS_ID, int pos, TextView TV_Opt, MotionLayout ML, ImageView IV) {
        String correct_ans = als_correct_opt.get(QCounter - 1);
        if (TextUtils.equals(TV_Opt.getText().toString(), correct_ans)) {
            RightCounter++;
            TV_RIGHT_COUNTER.setText(String.valueOf(RightCounter));
            ML.setTransition(TRANS_ID);
            ML.transitionToEnd();
            IV.setImageResource(R.drawable.ic_tick);
            IV.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.white)));

            TV_Opt.setTextColor(getResources().getColor(R.color.white));
            ML.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.color_right_answer)));

            ThumbsAnimation(R.id.TRANS_THUMBS_UP);
            RightAnswerTone();
            TV_Opt1.setEnabled(false);
            TV_Opt1.setClickable(false);
            TV_Opt2.setClickable(false);
            TV_Opt2.setEnabled(false);
            TV_Opt3.setClickable(false);
            TV_Opt3.setEnabled(false);
            TV_Opt4.setClickable(false);
            TV_Opt4.setEnabled(false);
            if (pos != 1) {
                ML_1.animate().alpha(0.5f).start();
            }
            if (pos != 2) {
                ML_2.animate().alpha(0.5f).start();
            }
            if (pos != 3) {
                ML_3.animate().alpha(0.5f).start();
            }
            if (pos != 4) {
                ML_4.animate().alpha(0.5f).start();
            }
        } else {
            WrongCounter++;
            TV_WRONG_COUNTER.setText(String.valueOf(WrongCounter));
            ML.setTransition(TRANS_ID);
            ML.transitionToEnd();
            IV.setImageResource(R.drawable.ic_cross);
            IV.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.white)));

            TV_Opt.setTextColor(getResources().getColor(R.color.white));
            ML.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.color_wrong_answer)));

            ThumbsAnimation(R.id.TRANS_THUMBS_DOWN);
            WrongAnsTone();
        }
    }

    private void ThumbsAnimation(Integer transitionID) {
        ML_MAIN.setTransition(transitionID);
        ML_MAIN.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                ML_MAIN.transitionToStart();
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
        ML_MAIN.transitionToEnd();
    }


    private void initQALists() {
        //Q1
        ArrayList<String> als_temp_1 = new ArrayList<>(4);
        als_temp_1.add("services, broadcast receivers , activities, content providers");
        als_temp_1.add("layouts, code, programming language, ui");
        als_temp_1.add("services, sqlite database, buttons, content providers");
        als_temp_1.add("activities, code, design, lists");

        HM_QA.put("What are the 4 main components of android?", als_temp_1);
        als_correct_opt.add("services, broadcast receivers , activities, content providers");

        als_image_list.add(R.drawable.q1);

        //Q2
        ArrayList<String> als_temp_2 = new ArrayList<>(4);
        als_temp_2.add("Algorithmic Protocol Interface");
        als_temp_2.add("Application Programming Interface");
        als_temp_2.add("Accelerated Programming Interface");
        als_temp_2.add("None of the above");

        HM_QA.put("API stands for _____", als_temp_2);
        als_correct_opt.add("Application Programming Interface");

        als_image_list.add(R.drawable.q2);

        //Q3
        ArrayList<String> als_temp_3 = new ArrayList<>(4);
        als_temp_3.add("/src");
        als_temp_3.add("/res/values");
        als_temp_3.add("/assets");
        als_temp_3.add("/res/layout");

        HM_QA.put("XML Layout files are stored in _____ directory", als_temp_3);
        als_correct_opt.add("/res/layout");

        als_image_list.add(R.drawable.q3);

        //Q4
        ArrayList<String> als_temp_4 = new ArrayList<>(4);
        als_temp_4.add("Breaks the execution");
        als_temp_4.add("Breaks the development code");
        als_temp_4.add("Breaks the application");
        als_temp_4.add("None of the above");

        HM_QA.put("What is a breakpoint in Android?", als_temp_4);
        als_correct_opt.add("Breaks the execution");

        als_image_list.add(R.drawable.q4);

        //Q5
        ArrayList<String> als_temp_5 = new ArrayList<>(4);
        als_temp_5.add("Bundle()");
        als_temp_5.add("startActivityForResult()");
        als_temp_5.add("startActivityToResult()");
        als_temp_5.add("None of the above");

        HM_QA.put("How to obtain a response from an Android activity?", als_temp_5);
        als_correct_opt.add("startActivityForResult()");
        als_q_list = new ArrayList<>(HM_QA.keySet());

        als_image_list.add(R.drawable.q5);

        SetDetails();
    }

    private void SetDetails() {
        try {

            TV_Q_COUNTER.setText("Question " + QCounter + "/5");
            AnimateProgress();
            IV_MAIN.setImageResource(als_image_list.get(QCounter - 1));
            TV_CURRENT_Q.setText("Question " + QCounter + ":");
            TV_Q.setText(als_q_list.get(QCounter - 1));
            ArrayList<String> temp = new ArrayList<>(Objects.requireNonNull(HM_QA.get(als_q_list.get(QCounter - 1))));
            TV_Opt1.setText(temp.get(0));
            TV_Opt2.setText(temp.get(1));
            TV_Opt3.setText(temp.get(2));
            TV_Opt4.setText(temp.get(3));

        } catch (RuntimeException e) {
            Toast.makeText(mContext, "We faced an error! Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void AnimateProgress() {
        Progress_bar.animateProgress(2000, Progress_bar.getProgress(), (QCounter) * (100 / 5));
    }

    public void RightAnswerTone() {
        MediaPlayer au1 = MediaPlayer.create(MainActivity.this, R.raw.rightans);
        au1.start();


    }

    public void WrongAnsTone() {
        MediaPlayer au2 = MediaPlayer.create(MainActivity.this, R.raw.wrongans);
        au2.start();

    }


}