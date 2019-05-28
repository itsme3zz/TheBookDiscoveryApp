package com.example.android.thebookdiscoveryapp;

import android.Manifest;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.folioreader.FolioReader;
import com.folioreader.model.locators.ReadLocator;
import com.folioreader.util.ReadLocatorListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

public class PersonalityActivity extends AppCompatActivity {
    private TextView person, details;
    private FirebaseAuth mAuth;

    DatabaseReference current_user_id;
    String message, user_id, filePath,sh,url1,url2,url3;
    ProgressBar pb, pb1, pb2, pb3;
    private ImageView b1, b2, b3;
    FolioReader folioReader = FolioReader.get();
    Button upload;
    public static final int REQUEST_CODE = 2;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality);

        person = findViewById(R.id.person);
        details = findViewById(R.id.details);
        pb = findViewById(R.id.pb);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        pb1 = findViewById(R.id.pb1);
        pb2 = findViewById(R.id.pb2);
        pb3 = findViewById(R.id.pb3);
        upload = findViewById(R.id.upload);


        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


        requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new MaterialFilePicker()
                            .withActivity(PersonalityActivity.this)
                            .withRequestCode(1)
                            .withFilterDirectories(true) // Set directories filterable (false by default)
                            .withHiddenFiles(true) // Show hidden files and folders
                            .start();



            }
        });



        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String type = String.valueOf(dataSnapshot.getValue());
                message = type;
                pb.setVisibility(View.INVISIBLE);

                if (message.contentEquals("INTJ")) {
                    person.setText("ARCHITECT (INTJ)");
                    details.setText("People with the Architect personality type are imaginative yet decisive, ambitious yet private, amazingly curious, but they do not squander their energy.");
                    b1.setImageResource(R.drawable.intj_1);
                    b2.setImageResource(R.drawable.intp_3);
                    b3.setImageResource(R.drawable.intj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/95564818/one-of-us-is-lying/mcmanus-karenm/";
                    url2="https://www.ebooks.com/357483/quiet/cain-susan/";
                    url3="https://www.ebooks.com/en-sa/538775/clockwork-angel/clare-cassandra/";
                }
                if (message.contentEquals("INTP")) {
                    person.setText("LOGICIAN (INTP)");
                    details.setText("Logicians pride themselves on their inventiveness and creativity, their unique perspective and vigorous intellect. Usually known as the philosopher, the architect, or the dreamy professor, Logicians have been responsible for many scientific discoveries throughout history.");
                    b1.setImageResource(R.drawable.intp_1);
                    b2.setImageResource(R.drawable.intp_2);
                    b3.setImageResource(R.drawable.intp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/1471841/creativity-inc/catmull-ed-wallace-amy/";
                    url2="https://www.ebooks.com/716375/the-power-of-habit/duhigg-charles/";
                    url3="https://www.ebooks.com/357483/quiet/cain-susan/";

                }
                if (message.contentEquals("ENTJ")) {
                    person.setText("COMMANDER (ENTJ)");
                    details.setText("Commanders are natural-born leaders. People with this personality type embody the gifts of charisma and confidence, and project authority in a way that draws crowds together behind a common goal.");
                    b1.setImageResource(R.drawable.entj_1);
                    b2.setImageResource(R.drawable.entj_2);
                    b3.setImageResource(R.drawable.entj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/555932/how-to-win-friends-and-influence-people/carnegie-dale/";
                    url2="https://www.ebooks.com/en-sa/282179/the-glass-castle/walls-jeannette/";
                    url3="https://www.ebooks.com/en-sa/780829/boy/dahl-roald-blake-quentin/";
                }
                if (message.contentEquals("ENTP")) {
                    person.setText("DEBATER (ENTP)");
                    details.setText("The Debater personality type is the ultimate devil’s advocate, thriving on the process of shredding arguments and beliefs and letting the ribbons drift in the wind for all to see. ");
                    b1.setImageResource(R.drawable.entp_1);
                    b2.setImageResource(R.drawable.entp_2);
                    b3.setImageResource(R.drawable.entp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/2267289/this-is-where-it-ends/nijkamp-marieke/";
                    url2="https://www.ebooks.com/en-sa/345947/the-shining/king-stephen/";
                    url3="https://www.ebooks.com/en-sa/1718302/the-girl-on-the-train/hawkins-paula/";
                }
                if (message.contentEquals("INFJ")) {
                    person.setText("ADVOCATE (INFJ)");
                    details.setText("Advocates have an inborn sense of idealism and morality, but what sets them apart is that they are not idle dreamers, but people capable of taking concrete steps to realize their goals and make a lasting positive impact.");
                    b1.setImageResource(R.drawable.infj_1);
                    b2.setImageResource(R.drawable.infj_2);
                    b3.setImageResource(R.drawable.infj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/365300/the-lion-the-witch-and-the-wardrobe/lewis-c-s-baynes-pauline/";
                    url2="https://www.ebooks.com/en-sa/193671/eragon/paolini-christopher/";
                    url3="https://www.ebooks.com/en-sa/989266/the-highly-sensitive-person-s-survival-guide/zeff-ted-aron-elaine/";
                }
                if (message.contentEquals("INFP")) {
                    person.setText("MEDIATOR (INFP)");
                    details.setText("Mediator personalities are true idealists, always looking for the hint of good in even the worst of people and events, searching for ways to make things better. While they may be perceived as calm, reserved, or even shy, Mediators have an inner flame and passion that can truly shine.");
                    b1.setImageResource(R.drawable.infp_1);
                    b2.setImageResource(R.drawable.infp_2);
                    b3.setImageResource(R.drawable.infp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/714258/mr-fox/oyeyemi-helen/";
                    url2="https://www.ebooks.com/en-sa/601422/the-night-circus/morgenstern-erin/";
                    url3="https://www.ebooks.com/en-sa/355496/flow/csikszentmihalyi-mihaly/";
                }
                if (message.contentEquals("ENFJ")) {
                    person.setText("PROTAGONIST (ENFJ)");
                    details.setText("Protagonists are natural-born leaders, full of passion and charisma. Forming around two percent of the population, they are oftentimes our politicians, our coaches and our teachers, reaching out and inspiring others to achieve and to do good in the world.");
                    b1.setImageResource(R.drawable.enfj_1);
                    b2.setImageResource(R.drawable.enfj_2);
                    b3.setImageResource(R.drawable.enfj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/260839/the-kite-runner/hosseini-khaled/";
                    url2="https://www.ebooks.com/en-sa/158825/the-great-gatsby/fitzgerald-f-scott/";
                    url3="https://www.ebooks.com/en-sa/138844/authentic-happiness/seligman-martine-p/";
                }
                if (message.contentEquals("ENFP")) {
                    person.setText("CAMPAIGNER (ENFP)");
                    details.setText("The Campaigner personality is a true free spirit. They are often the life of the party, but unlike types in the Explorer Role group, Campaigners are less interested in the sheer excitement and pleasure of the moment than they are in enjoying the social and emotional connections they make with others.");
                    b1.setImageResource(R.drawable.enfp_1);
                    b2.setImageResource(R.drawable.intp_1);
                    b3.setImageResource(R.drawable.enfp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.amazon.com/Giving-Tree-Shel-Silverstein/dp/0060256656/ref=sr_1_1?keywords=The+Giving+Tree&qid=1554297804&s=books&sr=1-1";
                    url2="https://www.ebooks.com/1471841/creativity-inc/catmull-ed-wallace-amy/";
                    url3="https://www.ebooks.com/en-sa/670857/drive/pink-danielh/";
                }
                if (message.contentEquals("ISTJ")) {
                    person.setText("LOGISTICIAN (ISTJ)");
                    details.setText("Their defining characteristics of integrity, practical logic and tireless dedication to duty make Logisticians a vital core to many families, as well as organizations that uphold traditions, rules and standards, such as law offices, regulatory bodies and military.");
                    b1.setImageResource(R.drawable.istj_1);
                    b2.setImageResource(R.drawable.istj_2);
                    b3.setImageResource(R.drawable.istj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/95514365/the-hate-u-give/thomas-angie/";
                    url2="https://www.ebooks.com/en-sa/96311913/dopesick/macy-beth/";
                    url3="https://www.ebooks.com/en-sa/1567299/emotional-intelligence/goleman-daniel/";
                }
                if (message.contentEquals("ISFJ")) {
                    person.setText("DEFENDER (ISFJ)");
                    details.setText("Defenders are true altruists, meeting kindness with kindness-in-excess and engaging the work and people they believe in with enthusiasm and generosity.");
                    b1.setImageResource(R.drawable.intp_1);
                    b2.setImageResource(R.drawable.isfj_2);
                    b3.setImageResource(R.drawable.infj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/1471841/creativity-inc/catmull-ed-wallace-amy/";
                    url2="https://www.ebooks.com/en-sa/2146576/big-magic/gilbert-elizabeth/";
                    url3="https://www.ebooks.com/en-sa/989266/the-highly-sensitive-person-s-survival-guide/zeff-ted-aron-elaine/";
                }
                if (message.contentEquals("ESTJ")) {
                    person.setText("EXECUTIVE (ESTJ)");
                    details.setText("Executives are classic images of the model citizen: they help their neighbors, uphold the law, and try to make sure that everyone participates in the communities and organizations they hold so dear.");
                    b1.setImageResource(R.drawable.estj_1);
                    b2.setImageResource(R.drawable.estj_2);
                    b3.setImageResource(R.drawable.estj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/1641896/into-the-wild/krakauer-jon/";
                    url2="https://www.ebooks.com/en-sa/95938549/black-klansman/stallworth-ron/";
                    url3="https://www.ebooks.com/en-sa/95913560/what-color-is-your-parachute-2019/bolles-richardn/";
                }
                if (message.contentEquals("ESFJ")) {
                    person.setText("CONSUL (ESFJ)");
                    details.setText("At their hearts, Consul personalities are social creatures, and thrive on staying up to date with what their friends are doing.");
                    b1.setImageResource(R.drawable.esfj_1);
                    b2.setImageResource(R.drawable.esfj_2);
                    b3.setImageResource(R.drawable.esfj_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/1144340/the-rosie-project/simsion-graeme/";
                    url2="https://www.ebooks.com/en-sa/555940/how-to-stop-worrying-and-start-living/carnegie-dale/";
                    url3="https://www.ebooks.com/en-sa/765512/one-day/nicholls-david/";
                }
                if (message.contentEquals("ISTP")) {
                    person.setText("VIRTUOSO (ISTP)");
                    details.setText("Rather than some sort of vision quest though, Virtuosos are merely exploring the viability of a new interest when they make these seismic shifts.");
                    b1.setImageResource(R.drawable.istp_1);
                    b2.setImageResource(R.drawable.istp_2);
                    b3.setImageResource(R.drawable.istp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/410309/the-help/stockett-kathryn/";
                    url2="https://www.ebooks.com/en-sa/340949/the-book-thief/zusak-markus/";
                    url3="https://www.ebooks.com/en-sa/879911/casino-royale/fleming-ian-judd-alan/";
                }
                if (message.contentEquals("ISFP")) {
                    person.setText("ADVENTURER (ISFP)");
                    details.setText("Adventurers always know just the compliment to soften a heart that’s getting ready to call their risks irresponsible or reckless.");
                    b1.setImageResource(R.drawable.isfp_1);
                    b2.setImageResource(R.drawable.isfp_2);
                    b3.setImageResource(R.drawable.isfp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/95801300/other-people-s-houses/waxman-abbi/";
                    url2="https://www.ebooks.com/en-sa/894163/fairy-tales-from-the-brothers-grimm/pullman-philip/";
                    url3="https://www.ebooks.com/en-sa/1577378/the-watermen/michener-jamesa-moll-john-berry-steve/";
                }
                if (message.contentEquals("ESTP")) {
                    person.setText("ENTREPRENEUR (ESTP)");
                    details.setText("Sometimes Entrepreneurs’ instantaneous observation and action is just what’s required, as in some corporate environments, and especially in emergencies.");
                    b1.setImageResource(R.drawable.estp_1);
                    b2.setImageResource(R.drawable.estp_2);
                    b3.setImageResource(R.drawable.estp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/780548/percy-jackson-and-the-lightning-thief-book-1/riordan-rick/";
                    url2="https://www.ebooks.com/en-sa/939241/life-of-pi/martel-yann/";
                    url3="https://www.ebooks.com/en-sa/409258/a-study-in-scarlet/doyle-sirarthurconan/";
                }
                if (message.contentEquals("ESFP")) {
                    person.setText("ENTERTAINER (ESFP)");
                    details.setText("There’s nothing that makes Entertainers feel quite as unhappy as realizing that they are boxed in by circumstance, unable to join their friends.");
                    b1.setImageResource(R.drawable.esfp_1);
                    b2.setImageResource(R.drawable.esfp_2);
                    b3.setImageResource(R.drawable.esfp_3);
                    pb1.setVisibility(View.INVISIBLE);
                    pb2.setVisibility(View.INVISIBLE);
                    pb3.setVisibility(View.INVISIBLE);
                    url1 ="https://www.ebooks.com/en-sa/1635283/yes-please/poehler-amy/";
                    url2="https://www.ebooks.com/en-sa/760513/oryx-and-crake/atwood-margaret/";
                    url3="https://www.ebooks.com/en-sa/757454/me-talk-pretty-one-day/sedaris-david/";
                }
                if (!dataSnapshot.exists()) {
                    Intent Person = new Intent(PersonalityActivity.this, HomeActivity.class);
                    startActivity(Person);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void testAgain(View view) {
        Intent Person = new Intent(PersonalityActivity.this, HomeActivity.class);
        startActivity(Person);

    }

    public void book1(View view) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url1));
        startActivity(i);
    }

    public void book2(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url2));
        startActivity(i);
    }

    public void book3 (View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url3));
        startActivity(i);
    }


    public void readBook (View view) {
        loadData();
        folioReader.openBook(sh);

    }


    // FILE PICKER

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateData();
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            saveData();

        }
    }


    // SHARED PREFERENCE METHODS

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, filePath);
        editor.commit();

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sh = sharedPreferences.getString(TEXT, "");

    }

    public void updateData() {
        SharedPreferences preferences = getSharedPreferences(TEXT, MODE_PRIVATE);
        preferences.edit().remove(TEXT).commit();

    }


     // LOG OUT

    public void logOut(View view) {

        FirebaseAuth.getInstance().signOut();
        Intent home = new Intent(PersonalityActivity.this, LoginActivity.class);
        startActivity(home);
        finish();
    }

}
