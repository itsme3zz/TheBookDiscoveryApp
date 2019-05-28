package com.example.android.thebookdiscoveryapp;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText password,email;
    Button backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        backToMain=findViewById(R.id.backToMain);


        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent home=new Intent(LoginActivity.this,PersonalityActivity.class);
            startActivity(home);
            finish();
        }

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }



    public void resetPassword(View view){

        Intent home=new Intent(LoginActivity.this,PasswordResetActivity.class);
        startActivity(home);
        finish();
    }

    public void backToMain(View view){

        Intent home=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(home);
        finish();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




    public void Login(View view) {
        String passwordText,emailText;

        passwordText=password.getText().toString();
        emailText=email.getText().toString();

        if (!passwordText.isEmpty()  && !emailText.isEmpty() )

        {

            mAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                checkIfEmailVerified();
                            } else {
                                mAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener
                                        (new OnCompleteListener<SignInMethodQueryResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                                boolean check = !task.getResult().getSignInMethods().isEmpty();
                                                if (!check){
                                                    email.setError("Email Not Registered");}
                                                    else {
                                                    password.setError("Password not correct");
                                                }

                                            }
                                        });
                            }
                        }
                    });

        } else {email.setError("please don't leave empty fields");}


    }

    public void openRegister(View view) {
        Intent register =new Intent (LoginActivity.this , RegisterActivity.class);
        startActivity(register);
    }

    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Intent home = new Intent(LoginActivity.this, PersonalityActivity.class);
            startActivity(home);
            finish();
            Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(LoginActivity.this, "Email Not Verified!", Toast.LENGTH_SHORT).show();
            //restart this activity

        }
    }
}
