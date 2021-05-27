package com.example.mathgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

            }
        });
    }

    public void gameContinue()
    {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);

        correctAnswer = number1 + number2;
        question.setText(number1 + " + " + number2);
    }



}