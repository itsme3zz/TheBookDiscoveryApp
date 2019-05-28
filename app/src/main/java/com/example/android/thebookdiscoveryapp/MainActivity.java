package com.example.android.thebookdiscoveryapp;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FirebaseAuth mAuth;
    private TextView[] mDots;
    private LinearLayout mDotLayout;
    private Button nextBtn,skipBtn,SignBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDotLayout =  findViewById(R.id.mDotLayout);
        nextBtn = findViewById(R.id.nxtBtn);
        skipBtn = findViewById(R.id.skipBtn);
        SignBtn = findViewById(R.id.SignBtn);
        mAuth = FirebaseAuth.getInstance();

        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent home=new Intent(MainActivity.this,PersonalityActivity.class);
            startActivity(home);
            finish();
        }

        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);


        nextBtn.setOnClickListener (new View.OnClickListener(){
              @Override
            public void onClick(View view){
                  viewPager.setCurrentItem(mCurrentPage + 1);

            }
        });

        skipBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                viewPager.setCurrentItem(mCurrentPage + mDots.length-1);


            }
        });

        SignBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null)
                {
                    Intent home=new Intent(MainActivity.this,PersonalityActivity.class);
                    startActivity(home);
                    finish();
                }
                Intent login = new Intent (MainActivity.this, LoginActivity.class);
                startActivity(login);


            }
        });


    }




    public void addDotsIndicator(int position){

            mDots = new TextView[4];
            mDotLayout.removeAllViews();
            for (int i = 0; i < mDots.length; i++){

                mDots[i] = new TextView(this);
                mDots[i].setText(Html.fromHtml("&#8226"));
                mDots[i].setTextSize(35);
                mDots[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorDarkWhite));

                mDotLayout.addView(mDots[i]);
            }

            if (mDots.length > 0){
                mDots[position].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            }

    }


          ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() { //creating viewpager object

              @Override
              public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
              }

              @Override
              public void onPageSelected(int i) {

                  addDotsIndicator(i);
                  mCurrentPage = i;

                  if (i == mDots.length-1){

                      nextBtn.setEnabled(false);
                      nextBtn.setVisibility(viewPager.INVISIBLE);
                      skipBtn.setEnabled(false);
                      skipBtn.setVisibility(viewPager.INVISIBLE);
                      SignBtn.setVisibility(viewPager.VISIBLE);

                  }else{
                      nextBtn.setEnabled(true);
                      nextBtn.setVisibility(viewPager.VISIBLE);
                      skipBtn.setEnabled(true);
                      skipBtn.setVisibility(viewPager.VISIBLE);
                      SignBtn.setVisibility(viewPager.INVISIBLE);
                  }

              }

              @Override
              public void onPageScrollStateChanged(int state) {
              }
          };


    public void openRegister(View view) {
        Intent register =new Intent (MainActivity.this , RegisterActivity.class);
        startActivity(register);
    }


}
