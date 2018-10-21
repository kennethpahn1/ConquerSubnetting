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
                    if (solution == 0){
                        counter++;
                        try {
                            if (add == true){
                                mark++;
                                total++;
                            }
                            counter++;
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            add = true;
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            aOption.setChecked(false);
                            add = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (next == false){
                                resultTxt.setText("Mark: " + mark + "/" + total + "\n\n Click Next when you are ready to learn more.");
                                next = true;
                            } else if (next == true){
                                Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
                                a.putExtra("moduleNo", moduleNo);
                                startActivity(a);
                            }
                        }
                        resultTxt.setText("Result: Correct!\n\n Mark: " + mark + "/" + total);
                    } else{
                        total++;
                        add = false;
                        resultTxt.setText("Result: Incorrect. Try again!\n\n Mark: " + mark + "/" + total);
                    }
                }
                if (bOption.isChecked() == true){
                    if (solution == 1){
                        counter++;
                        try {
                            if (add == true){
                                mark++;
                                total++;
                            }
                            counter++;
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            add = true;
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            bOption.setChecked(false);
                            add = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (next == false){
                                resultTxt.setText("Mark: " + mark + "/" + total + "\n\n Click Next when you are ready to learn more.");
                                next = true;
                            } else if (next == true){
                                Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
                                a.putExtra("moduleNo", moduleNo);
                                startActivity(a);
                            }
                        }
                        resultTxt.setText("Result: Correct!\n\n Mark: " + mark + "/" + total);
                    } else{
                        total++;
                        add = false;
                        resultTxt.setText("Result: Incorrect. Try again!\n\n Mark: " + mark + "/" + total);
                    }
                }
                if (cOption.isChecked() == true){
                    if (solution == 2){
                        counter++;
                        try {
                            if (add == true){
                                mark++;
                                total++;
                            }
                            counter++;
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            add = true;
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            cOption.setChecked(false);
                            add = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (next == false){
                                resultTxt.setText("Mark: " + mark + "/" + total + "\n\n Click Next when you are ready to learn more.");
                                next = true;
                            } else if (next == true){
                                Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
                                a.putExtra("moduleNo", moduleNo);
                                startActivity(a);
                            }
                        }
                        resultTxt.setText("Result: Correct!\n\n Mark: " + mark + "/" + total);
                    } else{
                        total++;
                        add = false;
                        resultTxt.setText("Result: Incorrect. Try again!\n\n Mark: " + mark + "/" + total);
                    }
                }
                if (dOption.isChecked() == true){
                    if (solution == 3){
                        counter++;
                        try {
                            if (add == true){
                                mark++;
                                total++;
                            }
                            counter++;
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            add = true;
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            dOption.setChecked(false);
                            add = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (next == false){
                                resultTxt.setText("Mark: " + mark + "/" + total + "\n\n Click Next when you are ready to learn more.");
                                next = true;
                            } else if (next == true){
                                Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
                                a.putExtra("moduleNo", moduleNo);
                                startActivity(a);
                            }
                        }
                        resultTxt.setText("Result: Correct!\n\n Mark: " + mark + "/" + total);
                    } else{
                        total++;
                        add = false;
                        resultTxt.setText("Result: Incorrect. Try again!\n\n Mark: " + mark + "/" + total);
                    }
                }
            }
        });
    }
}
