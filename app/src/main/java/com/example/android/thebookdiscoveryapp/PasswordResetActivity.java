package com.example.android.thebookdiscoveryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class PasswordResetActivity extends AppCompatActivity {
    EditText email;
    TextView text1,text2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        email=findViewById(R.id.reset_password);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text1.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();



        //keyboard disappearing
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }


    public void main(View view){

        Intent home=new Intent(PasswordResetActivity.this,LoginActivity.class);
        startActivity(home);
    }

    public void resetPass (View view){
        String emailText;


        emailText = email.getText().toString();

        if(emailText.isEmpty()||emailText.equals(" "))
        {
            email.setError("Please Don't Leave Empty Fields!");
            return;
        }
        if (!(emailText.contains("@"))){

            email.setError("Email Not Valid!");
            return;
        }


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = emailText;

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            mAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener
                                    (new OnCompleteListener<SignInMethodQueryResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                            boolean check = !task.getResult().getSignInMethods().isEmpty();
                                            if (check){
                                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                                imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
                                                text1.setVisibility(View.VISIBLE);
                                                text2.setVisibility(View.INVISIBLE);

                                            }

                                        }
                                    });
                        }else {email.setError("Email Not Registered");}
                    }
                });

    }
    public void backToMain(View view){

        Intent home=new Intent(PasswordResetActivity.this,MainActivity.class);
        startActivity(home);
        finish();
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
