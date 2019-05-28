package com.example.android.thebookdiscoveryapp;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,password,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();


        email=findViewById(R.id.register_email);
        password= findViewById(R.id.register_password);
        confirmPassword=findViewById(R.id.register_confirm_password);




        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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



    public void openLogin(View view){

       Intent login = new Intent (this, LoginActivity.class);
               startActivity(login);

    }


    public void Register(final View view){
        final String emailText,passwordText,confirmPasswordText;

        emailText=email.getText().toString();
        passwordText=password.getText().toString();
        confirmPasswordText=confirmPassword.getText().toString();

        if(emailText.isEmpty()||emailText.equals(" "))
        {
            email.setError("Email Not Valid!");
            return;
        }
        if (!(emailText.contains("@")) || !(emailText.contains("."))){

            email.setError("Email Not Valid!");
            return;
        }

        if(passwordText.isEmpty()||passwordText.equals(" "))
        {
            password.setError("password Not Valid!");
            return;
        }


        if(!confirmPasswordText.equals(passwordText))
        {
            confirmPassword.setError("Password not match!");
            return;
        }


        if(confirmPasswordText.isEmpty()||confirmPasswordText.equals(" "))
        {
            confirmPassword.setError("password Not Valid!");
            return;
        }

        if(passwordText.length()<8)
        {
            password.setError("Length must be greater than 8");
            return;
        }








        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegisterActivity.this, "Email Verification Link Sent", Toast.LENGTH_SHORT).show();
                            sendVerificationEmail();

                        } else {
                            // If sign in fails, display a message to the user.
                            mAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener
                                    (new OnCompleteListener<SignInMethodQueryResult>() {
                                @Override
                                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                    boolean check = !task.getResult().getSignInMethods().isEmpty();
                                    if (check){


                                        email.setError("Email Already Registered");}

                                }
                            });
                        }

                        // ...
                    }
                });



    }
    public void backToMain(View view){

        Intent home=new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(home);
        finish();
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent


                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do

                            //restart this activity
                            Toast.makeText(RegisterActivity.this, "Error Sending Verification Link", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
