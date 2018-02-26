package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Number of questions
    private int nQuestion = 4;

    // Right Answer
    private int correctAnswers = 0;

    // Answers for questions
    private int answerQ1 = 1;
    private String answerQ3 = "NES";
    private int answerQ4 = 1;

    // EditText name
    private EditText textName;

    // RadioGroup Q1 -> Answer Button 1
    private RadioGroup rgQ1;

    // CheckBox Q2 -> Answer ChecBox 1 & 4
    private CheckBox cbQ2a1;
    private CheckBox cbQ2a2;
    private CheckBox cbQ2a3;
    private CheckBox cbQ2a4;

    // EditText Q3 -> Answer NES
    private EditText textQ3;

    // RadioGroup Q4 -> Answer Button 1
    private RadioGroup rgQ4;

    // Solutions
    private TextView answers;
    private TextView answerSummary;
    private Button  button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = (EditText) findViewById(R.id.name_field);
        rgQ1 = (RadioGroup) findViewById(R.id.rgQ1);
        cbQ2a1 = (CheckBox) findViewById(R.id.q2a1);
        cbQ2a2 = (CheckBox) findViewById(R.id.q2a2);
        cbQ2a3 = (CheckBox) findViewById(R.id.q2a3);
        cbQ2a4 = (CheckBox) findViewById(R.id.q2a4);
        textQ3 = (EditText) findViewById(R.id.name_console);
        rgQ4 = (RadioGroup) findViewById(R.id.rgQ4);

        answers = (TextView) findViewById(R.id.answers);
        answers.setText(getString(R.string.solQ1) + "\n" + getString(R.string.solQ2) + "\n" + getString(R.string.solQ3) + "\n" + getString(R.string.solQ4));
        answerSummary = (TextView) findViewById(R.id.correctA);

        button = (Button) findViewById(R.id.solution);
    }

    /**
     * When Submit Answer is clicked
     * Updates the number of right answers, show the button to see the solution and show the toast
     */
    public void submitAnswers(View view) {
        checkAnswers();
        String name = textName.getText().toString();
        String summary;

        // Check if a name has been introduced
        if(!name.equals(""))
             summary = makeSummary(name);
        else
            summary = getString(R.string.correct_answers_noName, correctAnswers);

        display(summary);
        Toast.makeText(this, getString(R.string.toast, correctAnswers, nQuestion), Toast.LENGTH_SHORT).show();
        correctAnswers = 0;
    }

    /**
     * Updates the TextView
     *
     * @param summary text needed to update
     */
    private void display(String summary) {
        answerSummary.setText(summary);
        answerSummary.setVisibility(View.VISIBLE);

        button.setVisibility(View.VISIBLE);
    }

    /**
     * When Solution button is clicked
     * Makes the solution visible or gone
     */
    public void showSolution(View view){
        if(answers.getVisibility() == View.GONE)
            answers.setVisibility(View.VISIBLE);
        else
            answers.setVisibility(View.GONE);
    }

    /**
     * Check the number of right answers
     */
    private void checkAnswers(){
        for(int i=1; i<=nQuestion; i++){
            switch (i){
                case 1:
                    radioGroupQuestion(answerQ1, rgQ1);
                    break;
                case 2:
                    checkBoxQuestion2();
                    break;
                case 3:
                    editTextQuestion(answerQ3, textQ3);
                    break;
                case 4:
                    radioGroupQuestion(answerQ4, rgQ4);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Checks if the answer is right on RadioGroup questions and updates the number of right answers
     *
     * @param answer    number to indicate which is the right answers between all the buttons
     * @param question  RadioGroup to check
     */
    private void radioGroupQuestion(int answer, RadioGroup question){
        if(answer <= question.getChildCount()){
            RadioButton correctButton = (RadioButton) question.getChildAt(answer-1);
            if(correctButton.isChecked())
                correctAnswers++;
        }
    }

    /**
     * Check the Question 2. The two answers need to be checked.
     * Only works for Question 2
     */
    private void checkBoxQuestion2(){
        if(cbQ2a1.isChecked() && !cbQ2a2.isChecked() && !cbQ2a3.isChecked() && cbQ2a4.isChecked())
            correctAnswers++;
    }

    /**
     * Checks if the answer is right on EditText questions and updates the number of right answers
     *
     * @param answer    String with the right answer
     * @param question  EditText to check
     */
    private void editTextQuestion(String answer, EditText question){
        if(question.getText().toString().equals(answer))
            correctAnswers++;
    }

    /**
     * @param name  person's name that did the quiz
     * @return  The String to show
     */
    private String makeSummary(String name){
        return getString(R.string.correct_answers, name, correctAnswers);
    }
}
