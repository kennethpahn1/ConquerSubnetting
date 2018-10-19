package com.example.kennethpahn.infs1609tutorialapplication;

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
    private mcqQuizContent mcqQuizQ1 = new mcqQuizContent(1, 1, 1, "Lorem Ipsum?", 1, "lorem ipsum yes!", "test wrong answer", "test wrong answer", "test wrong answer");
    public void populateMcqQuiz(){

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
        questionTxt.setText(mcqQuizQ1.getQuestion());
        String[] answers = mcqQuizQ1.getAnswers();
        solution = mcqQuizQ1.getSolution();
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
                    } else{
                        resultTxt.setText("Result: Incorrect!");
                    }
                }
                if (bOption.isChecked() == true){
                    if (solution == 2){
                        resultTxt.setText("Result: Correct!");
                    } else{
                        resultTxt.setText("Result: Incorrect!");
                    }
                }
                if (cOption.isChecked() == true){
                    if (solution == 3){
                        resultTxt.setText("Result: Correct!");
                    } else{
                        resultTxt.setText("Result: Incorrect!");
                    }
                }
                if (dOption.isChecked() == true){
                    if (solution == 4){
                        resultTxt.setText("Result: Correct!");
                    } else{
                        resultTxt.setText("Result: Incorrect!");
                    }
                }
            }
        });
    }
}
