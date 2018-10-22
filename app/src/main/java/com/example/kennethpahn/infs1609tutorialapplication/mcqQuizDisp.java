package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class mcqQuizDisp extends AppCompatActivity {
    private TextView questionTxt;
    private RadioButton aOption;
    private RadioButton bOption;
    private RadioButton cOption;
    private RadioButton dOption;
    private Button nextBtn;
    private TextView resultTxt;
    private int solution;
    private int moduleNo;
    private int counter = 0;
    private boolean add = true; // so this prevents retried questions from being taken as correct.
    private boolean next = false;
    // this is to hold the marks
    private int mark;
    private int total;
    private mcqQuizContent[] populateMcqQuiz(int moduleNo){
        mcqQuizContent[] mcqQuiz = new mcqQuizContent[10];
        if (moduleNo == 0){
            mcqQuiz[0] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 3", 3, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[1] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[2] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 0", 0, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[3] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 2", 2, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[4] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[5] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 3", 3, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[6] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[7] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 0", 0, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[8] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 2", 2, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[9] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
        }
        return mcqQuiz;
    }
    // handle checking answers for us
    private int checkAnswer(int user, int answer){
        if (user == answer) {
            if (add == true) {
                mark++;
                total++;
            }
            counter++;
            add = true;
            resultTxt.setText("Result: Correct!\nMark: " + mark + "/" + total);

            return 1;
        } else {
            if (add == true) {
                total++;
            }
            add = false;
            resultTxt.setText("Result: Incorrect. Try again!\nMark: " + mark + "/" + total);
            return 0;
        }
    }
    // handles the error for us (as in, moves it back to Main Activity).
    private void error(){
        if (next == false){
            resultTxt.setText("Mark: " + mark + "/" + total + "\nClick Next when you are ready to learn more.");
            next = true;
        } else if (next == true){
            Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
            a.putExtra("moduleNo", moduleNo);
            startActivity(a);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_quiz_disp);
        questionTxt = (TextView) findViewById(R.id.questionTxt);
        aOption = (RadioButton) findViewById(R.id.aOption);
        bOption = (RadioButton) findViewById(R.id.bOption);
        cOption = (RadioButton) findViewById(R.id.cOption);
        dOption = (RadioButton) findViewById(R.id.dOption);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        resultTxt = (TextView) findViewById(R.id.resultTxt);
        // figure out what module they're running
        // get intent details
        Bundle infoPassed = getIntent().getExtras();
        moduleNo = infoPassed.getInt("moduleNo");
        // populate details based on that.
        final mcqQuizContent[] mcqQuiz = populateMcqQuiz(moduleNo);
        questionTxt.setText(mcqQuiz[counter].getQuestion());
        String[] answers = mcqQuiz[counter].getAnswers();
        solution = mcqQuiz[counter].getSolution();
        aOption.setText(answers[0]);
        bOption.setText(answers[1]);
        cOption.setText(answers[2]);
        dOption.setText(answers[3]);
        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (aOption.isChecked() == true){
                    try {
                        if (checkAnswer(0, mcqQuiz[counter].getSolution()) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            aOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
                if (bOption.isChecked() == true){
                    try {
                        if (checkAnswer(1, mcqQuiz[counter].getSolution()) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            bOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
                if (cOption.isChecked() == true){
                    try {
                        if (checkAnswer(2, mcqQuiz[counter].getSolution()) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            cOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
                if (dOption.isChecked() == true) {
                    try {
                        if (checkAnswer(3, mcqQuiz[counter].getSolution()) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            dOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
            }
        });
    }
}
