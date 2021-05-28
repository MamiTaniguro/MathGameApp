package com.example.mathgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;
    TextView answer;

    Button submitButton;
    Button nextQuestionButton;

    Random random = new Random();
    int number1;
    int number2;
    int userAnswer;
    int correctAnswer;
    int userScore = 0;
    int userLife = 3;

    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);
        submitButton = findViewById(R.id.submitButton);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);

        gameContinue();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAnswer = Integer.valueOf(answer.getText().toString());

                pauseTimer();

                if (userAnswer == correctAnswer)
                {
                    userScore = userScore + 10;
                    score.setText("" + userScore);
                    question.setText("Your answer is correct. Congratulations!");
                }
                else
                {
                    userLife = userLife - 1;
                    life.setText("" + userLife);
                    question.setText("Incorrect. The answer is " + correctAnswer);
                }
            }
        });

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer.setText("");
                gameContinue();
                resetTimer();

            }
        });
    }

    public void gameContinue()
    {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);

        correctAnswer = number1 + number2;
        question.setText(number1 + " + " + number2);
        startTimer();
    }

    public void startTimer()
    {
        timer = new CountDownTimer(time_left_in_milis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                time_left_in_milis = millisUntilFinished;
                updateTimerText();

            }

            @Override
            public void onFinish() {

                timer_running = false;
                pauseTimer();
                resetTimer();
                updateTimerText();
                userLife = userLife - 1;
                life.setText("" + userLife);
                question.setText("TIME IS UP!");
            }
        }.start();

        timer_running = true;
    }

    public void updateTimerText()
    {
        int second = (int)(time_left_in_milis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time.setText(time_left);
    }

    public void pauseTimer()
    {
        timer.cancel();
        timer_running = false;
    }

    public void resetTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateTimerText();
    }



}