package com.nl3designs.njrealtorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.HashSet;
import java.util.Set;

public class FlashCards extends AppCompatActivity {

    private TextView tv_question;
    private TextView tv_answers;
    private Button btn_answer;
    private Button btn_details;
    private Button btn_menu;
    private String sAnswer0, sAnswer1, sAnswer02;
    private String sDetails;
    private String[] sArray = new String[3];
    private ImageView iv_questionimage;
    private QuestionManager questionManager;
    public static String results = "";
    StorageManager storageManager;
    private TextView tv_tries;
    int tries = 0;
    int prevint = 0;
    int numQuestions = 0;
    int myQuestionAnswer = -0;
    int myans = -0;
    private Button btn_prev;
    private Button btn_next;
    private LinearLayout ll_customCards;
    private boolean sw1State;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcards);
        storageManager = new StorageManager(this);
        questionManager = QuestionManager.getInstance();
        questionManager.loadQuestions(this, "");
        numQuestions = questionManager.questionitems.size();
        tv_question = findViewById(R.id.tv_question);
        tv_answers = findViewById(R.id.tv_answers);
        btn_answer = findViewById(R.id.btn_answer);
        btn_details = findViewById(R.id.btn_details);
        tv_tries = findViewById(R.id.tries);
        btn_menu = findViewById(R.id.btn_menu);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);
        ll_customCards = findViewById(R.id.ll_customCards);
        iv_questionimage = findViewById(R.id.questionImage);

        // rand cards switch
        boolean swState = Boolean.parseBoolean(storageManager.load("flashRand"));
        if (swState == true) {
            questionManager.randomize();
        }

        // next card switch
        sw1State = Boolean.parseBoolean(storageManager.load("nextCard"));

        if(results.equals("")){
            results = storageManager.load("results");
        }

        // *** new code ***
        if (!storageManager.areCategoriesSelected()){
            ll_customCards.setVisibility(View.GONE);
        }

        //  custom card switch
        Switch sw = findViewById(R.id.customCards);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loadCustomQuestions();
                } else {
                    loadAllQuestions();
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for next button

            @Override
            public void onClick(View v) {

                myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                myans = myQuestionAnswer;
                tv_question.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_05)); // ?? 1-24
                tv_question.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black)); // ?? 1-24
                enableAnswerBtn();
                checkForEnd();
                btn_next.setVisibility(View.INVISIBLE);
                QuestionItem questionItem = questionManager.getPrev();
                prevint = 1;

                if (questionItem != null) {  // if not null
                    setQuestionScreen(questionItem);
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    myans = myQuestionAnswer;
                } else {
                    questionManager.currentIndex = 0;
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for next button

            @Override
            public void onClick(View v) {

                myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                myans = myQuestionAnswer;
                tv_question.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_05)); // ?? 1-24
                tv_question.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black)); // ?? 1-24
                enableAnswerBtn();
                checkForEnd();
                btn_next.setVisibility(View.INVISIBLE);
                btn_details.setVisibility(View.GONE); // new code 1-24
                QuestionItem questionItem = questionManager.getNext();
                prevint = 0;

                if (questionItem != null) {  // if not null
                    setQuestionScreen(questionItem);
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    myans = myQuestionAnswer;
                } else {
                    questionManager.currentIndex = 0;
                }
            }
        });

        btn_answer.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for answer button
            @Override
            public void onClick(View v) {

                btn_next.setVisibility(View.VISIBLE);
                disableAnswerBtn();
                showAnswer();
            }
        });

        // new code 1-24
        btn_details.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for answer button
            @Override
            public void onClick(View v) {

                btn_details.setVisibility(View.GONE);
                showDetails();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });

        QuestionItem questionItem = questionManager.getNext();
        setQuestionScreen(questionItem);
    }

    private void loadCustomQuestions() {
        String selected = storageManager.load("customCards");
        String[] selectedArray = selected.split(",");
        Set<String> selectedCat = new HashSet<>();
        for(String cat : selectedArray){
            selectedCat.add(cat);
        }
        questionManager.loadAllQuestion(this,selectedCat);
        updateList();
    }

    private void loadAllQuestions() {
        questionManager.loadAllQuestion(this,null);
        updateList();
    }

    private void updateList() {
        QuestionItem questionItem = questionManager.getNext();
        setQuestionScreen(questionItem);
        numQuestions = questionManager.questionitems.size();
        tries = 1;
        updateScoreCount();
    }

    private void setQuestionScreen(QuestionItem questionItem) {

        if (sw1State == true) {
            btn_next.setVisibility(View.VISIBLE);
        }

        tv_answers.setVisibility(View.GONE);
        btn_answer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Green_08));
        btn_answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.White));
        btn_answer.setText("show answer");

        if (questionManager.getCurrentIndex() == 0) {
            btn_prev.setVisibility(View.INVISIBLE);
        } else {
            btn_prev.setVisibility(View.VISIBLE);
        }

        if (prevint == 1) {
              tries -= 1;
        } else {
              tries += 1;
        }

        iv_questionimage.setImageResource(questionManager.categoryMap.get(questionItem.category));
        updateScoreCount();
        tv_question.setText(questionItem.question);
        sArray[0] = (questionItem.answers[0]);  // answer0 use to show for all of the above
        sArray[1] = (questionItem.answers[1]);  // answer1 use to show for all of the above
        sArray[2] = (questionItem.answers[2]);  // answer2 use to show for all of the above
        TextView textview = findViewById(R.id.tv_title);
        textview.setText(getTitleFromCategory(questionItem.category));
    }

    private String getTitleFromCategory(String category){
        category = category.substring(0, 1).toUpperCase() + category.substring(1);
        return category;
    }


    private void showAnswer(){

        // TODO : check json file answers "all of the above" for spaces
        if (questionManager.getCurrentAnswer().matches("All of the above")){
            tv_answers.setVisibility(View.VISIBLE);
            tv_answers.setText("1. " + sArray[0] + "\n" + "2. "+ sArray[1] + "\n" + "3. " + sArray[2] + "\n");
        }

        btn_answer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_08));
        btn_answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black));
        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
        myans = myQuestionAnswer;
        btn_details.setVisibility(View.VISIBLE); // new code 1-24
        btn_answer.setText(questionManager.getCurrentAnswer());
    }

    // new code 1-24
    private void showDetails(){

        // TODO : new code 1-24
        // TODO : check json file answers "all of the above" for spaces
        if (questionManager.getCurrentAnswer().matches("All of the above")){
            tv_answers.setVisibility(View.GONE);
        }

        tv_question.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Green_02));
        tv_question.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Green_13));
        sDetails = (questionManager.getCurrentQuestion().details);
        btn_details.setVisibility(View.GONE);
        tv_question.setText(sDetails);
    }

        private void checkForEnd(){

            if (tries == numQuestions) {
                endOfCards();
            }
        }

        private void updateScoreCount() {

            tv_tries.setText(String.valueOf(tries)+" / "+numQuestions);
        }

        private void disableAnswerBtn() {

            btn_answer.setClickable(false);
        }

        private void enableAnswerBtn() {

            btn_answer.setClickable(true);
        }

        private void endOfCards() {

            new AlertDialog.Builder(this)
                    .setTitle("Done with flashcards")
                    .setMessage("Congrates the force is strong with you, your test awaits you!")
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })
                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, MenuActivity.class);
            startActivity(intent);
        }

        private void gotoMenu() {
            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, MenuActivity.class);
            startActivity(intent);
        }

        @Override
        public void onBackPressed() {

            startActivity(new Intent(this, MenuActivity.class));
            finish();
        }
}
