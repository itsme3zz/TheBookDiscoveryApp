package com.example.android.thebookdiscoveryapp;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    private TextView quest, person;
    private Button c1, c2, res;
    private int questionNo = 0;
    private String personality = "";
    private String Tperson;


    private FirebaseAuth mAuth;
    String user_id;
    DatabaseReference current_user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        quest = findViewById(R.id.questions);
        c1 = findViewById(R.id.choice1);
        c2 = findViewById(R.id.choice2);
        res = findViewById(R.id.res);
        person = findViewById(R.id.person);


        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        res.setVisibility(View.INVISIBLE);
        person.setText("Question 1");
        updateQuestions();



    }


    public void c1(View view) {

        if (questionNo == 1) {
            personality += "I";
            person.setText("Question 2");
        }
        if (questionNo == 2) {
            personality += "I";
            person.setText("Question 3");
        }
        if (questionNo == 3) {
            personality += "I";
            if (personality.contentEquals("III") || personality.contentEquals("IIE") || personality.contentEquals("IEI") || personality.contentEquals("EII"))
                Tperson = "I";
            else
            { Tperson = "E";}
            person.setText("Question 4");
        }
        if (questionNo == 4) {

            personality = "S";
            person.setText("Question 5");
        }
        if (questionNo == 5) {
            personality += "S";
            person.setText("Question 6");
        }
        if (questionNo == 6) {
            personality += "S";
            if (personality.contentEquals("SSS") || personality.contentEquals("SSN") || personality.contentEquals("SNS") || personality.contentEquals("NSS"))
                Tperson += "S";
            else
                Tperson += "N";
            person.setText("Question 7");
        }
        if (questionNo == 7) {

            personality = "T";
            person.setText("Question 8");
        }
        if (questionNo == 8) {
            personality += "T";
            person.setText("Question 9");
        }
        if (questionNo == 9) {
            personality += "T";
            if (personality.contentEquals("TTT") || personality.contentEquals("TTF") || personality.contentEquals("TFT") || personality.contentEquals("FTT"))
                Tperson += "T";
            else
            { Tperson += "F";}
            person.setText("Question 10");
        }
        if (questionNo == 10) {

            personality = "J";
            person.setText("Question 11");
        }
        if (questionNo == 11) {
            personality += "J";
            person.setText("Question 12");
        }
        if (questionNo == 12) {
            personality += "J";
            if (personality.contentEquals("JJJ") || personality.contentEquals("JJP") || personality.contentEquals("JPJ") || personality.contentEquals("PJJ"))
                Tperson += "J";
            else
            {Tperson += "P";}
            person.setText("");
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);
            res.setVisibility(View.VISIBLE);
        }



        updateQuestions();


    }

    public void res(View view) {
        updateQuestions();
        current_user_id.setValue(Tperson);
        sendMessage();
    }


    public void c2(View view) {
        if (questionNo == 1) {
            personality += "E";
            person.setText("Question 2");
        }
        if (questionNo == 2) {
            personality += "E";
            person.setText("Question 3");
        }
        if (questionNo == 3) {
            personality += "E";
            if (personality.contentEquals("EEE") || personality.contentEquals("EEI") || personality.contentEquals("EIE") || personality.contentEquals("IEE"))
                Tperson = "E";
            else
            {Tperson = "I";}
            person.setText("Question 4");
        }
        if (questionNo == 4) {

            personality = "N";
            person.setText("Question 5");
        }
        if (questionNo == 5) {
            personality += "N";
            person.setText("Question 6");
        }
        if (questionNo == 6) {
            personality += "N";
            if (personality.contentEquals("NNN") || personality.contentEquals("NNS") || personality.contentEquals("NSN") || personality.contentEquals("SNN"))
                Tperson += "N";
            else
            {Tperson += "S";}
            person.setText("Question 7");
        }
        if (questionNo == 7) {

            personality = "F";
            person.setText("Question 8");
        }
        if (questionNo == 8) {
            personality += "F";
            person.setText("Question 9");
        }
        if (questionNo == 9) {
            personality += "F";
            if (personality.contentEquals("FFF") || personality.contentEquals("FFT") || personality.contentEquals("FTF") || personality.contentEquals("TFF"))
                Tperson += "F";
            else
            { Tperson += "T";}
            person.setText("Question 10");
        }
        if (questionNo == 10) {

            personality = "P";
            person.setText("Question 11");
        }
        if (questionNo == 11) {
            personality += "P";
            person.setText("Question 12");
        }
        if (questionNo == 12) {
            personality += "P";
            if (personality.contentEquals("PPP") || personality.contentEquals("PPJ") || personality.contentEquals("PJP") || personality.contentEquals("JPP"))
                Tperson += "P";
            else
            { Tperson += "J";}
            person.setText("");
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);
            res.setVisibility(View.VISIBLE);


        }



        updateQuestions();


    }


    public void logOut(View view) {

        FirebaseAuth.getInstance().signOut();
        Intent home = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(home);
        finish();
    }


    public void updateQuestions() {
        if (questionNo < 13) {
            quest.setText(mQuestionLibrary.getQuestion(questionNo));
            c1.setText(mQuestionLibrary.getChoice1(questionNo));
            c2.setText(mQuestionLibrary.getChoice2(questionNo));
            questionNo++;
                Animation fadein = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.fadein);
                quest.startAnimation(fadein);
                person.startAnimation(fadein);



        }
    }

    public void sendMessage() {

        Intent Person = new Intent(HomeActivity.this, PersonalityActivity.class);
        startActivity(Person);
        finish();
    }


}
