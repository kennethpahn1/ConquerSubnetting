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
    private mcqQuizContent[] populateMcqQuiz(int moduleNo){
        mcqQuizContent[] mcqQuiz = new mcqQuizContent[10];
        if (moduleNo == 0){
            mcqQuiz[0] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 3, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[1] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[2] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 0, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[3] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 2, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[4] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[5] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 3, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[6] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[7] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 0, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[8] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 2, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[9] = new mcqQuizContent(1, 1, 2, "Hello, how are you today?", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
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
                    if (solution == 1){
                        resultTxt.setText("Result: Correct!");
                        counter++;
                        try {
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Intent a = new Intent(mcqQuizDisp.this, tfQuizDisp.class);
                            a.putExtra("moduleNo", moduleNo);
                            startActivity(a);
                        }
                    } else{
                        resultTxt.setText("Result: Incorrect. Try again.");
                    }
                }
                if (bOption.isChecked() == true){
                    if (solution == 2){
                        resultTxt.setText("Result: Correct!");
                        counter++;
                        try {
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Intent a = new Intent(mcqQuizDisp.this, tfQuizDisp.class);
                            a.putExtra("moduleNo", moduleNo);
                            startActivity(a);
                        }
                    } else{
                        resultTxt.setText("Result: Incorrect. Try again.");
                    }
                }
                if (cOption.isChecked() == true){
                    if (solution == 3){
                        resultTxt.setText("Result: Correct!");
                        counter++;
                        try {
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Intent a = new Intent(mcqQuizDisp.this, tfQuizDisp.class);
                            a.putExtra("moduleNo", moduleNo);
                            startActivity(a);
                        }
                    } else{
                        resultTxt.setText("Result: Incorrect. Try again.");
                    }
                }
                if (dOption.isChecked() == true){
                    if (solution == 4){
                        resultTxt.setText("Result: Correct!");
                        counter++;
                        try {
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            solution = mcqQuiz[counter].getSolution();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Intent a = new Intent(mcqQuizDisp.this, tfQuizDisp.class);
                            a.putExtra("moduleNo", moduleNo);
                            startActivity(a);
                        }
                    } else{
                        resultTxt.setText("Result: Incorrect. Try again.");
                    }
                }
            }
        });
    }
}
