package com.example.finalyear;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.finalyear.model.User;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Button gohome;
        Typeface font_bold,font_Semibold;
        TextView sign,title,home,messages,circle_count,friends,shop,favourites;
    private UserSession session;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefManager = new PrefManager(this);
   prefManager.setFirstTimeLaunch(false);

        final String PREFS_NAME = "myPrefsFile" +
        "";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        if (settings.getBoolean("my_first_time",true)){
           tapview();
            settings.edit().putBoolean("my_first_time",false).commit();

        }



        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        User user = SharedPrefManager.getInstance(this).getUser();


        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        TextView appname = findViewById(R.id.appname);
        appname.setTypeface(typeface);


        font_bold =  Typeface.createFromAsset(HomeActivity.this.getAssets(),"fonts/Lato-Regular.ttf");
        font_Semibold =  Typeface.createFromAsset(HomeActivity.this.getAssets(),"fonts/Lato-Light.ttf");

      TextView  checker  = (TextView) findViewById(R.id.checker);
      checker.setText(user.getGender());

        title = (TextView)findViewById(R.id.title);
        title.setTypeface(font_bold);

        title.setText(user.getUsername());

        String ab = (String) checker.getText();
        String checklecturer = "Lecturer";
        String checkstudent = "Student";
        String checkadmin = "admin";


if(ab.equals(checklecturer)){
    home = (TextView)findViewById(R.id.home);
    home.setTypeface(font_Semibold);
    home.setText("Helloboi");



    messages = (TextView)findViewById(R.id.messages);
    messages.setTypeface(font_Semibold);
    messages.setText("Lecturer account");


    friends = (TextView)findViewById(R.id.friends);
    friends.setTypeface(font_Semibold);
    friends.setText("Friends");



    favourites = (TextView)findViewById(R.id.favourites);
    favourites.setTypeface(font_Semibold);
    favourites.setText("Favourites");

}

        if(ab.equals(checkstudent)){
            home = (TextView)findViewById(R.id.home);
            home.setTypeface(font_Semibold);
            home.setText("Hellouser");



            messages = (TextView)findViewById(R.id.messages);
            messages.setTypeface(font_Semibold);
            messages.setText("User account");



            friends = (TextView)findViewById(R.id.friends);
            friends.setTypeface(font_Semibold);
            friends.setText("User Friends");



            favourites = (TextView)findViewById(R.id.favourites);
            favourites.setTypeface(font_Semibold);
            favourites.setText("Favourites");

        }

        if(ab.equals(checkadmin)){
            home = (TextView)findViewById(R.id.home);
            home.setText("Students");
            home.setTypeface(font_Semibold);

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it  = new Intent(HomeActivity.this,AddUserActivity.class);
                    startActivity(it);
                }
            });



            messages = (TextView)findViewById(R.id.messages);
            messages.setText("Lecturer");
            messages.setTypeface(font_Semibold);

            messages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it  = new Intent(HomeActivity.this,AddNewLecturer.class);
                    startActivity(it);
                }
            });


            friends = (TextView)findViewById(R.id.friends);
            friends.setText("Courses");
            friends.setTypeface(font_Semibold);
                friends.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent it  = new Intent(HomeActivity.this,AddNewCourse.class);
                        startActivity(it);
                    }
                });




            favourites = (TextView)findViewById(R.id.favourites);
            favourites.setText("Attendance");
            favourites.setTypeface(font_Semibold);
            favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(HomeActivity.this, Attendance.class);
                        startActivity(it);
                }
            });


        }


//        home.setText(user.getUserType());




ImageView logout = (ImageView) findViewById(R.id.logot);
logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
        SharedPrefManager.getInstance(getApplicationContext()).logout();
    }
});






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void tapview() {

        new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.notifintro), "Notifications", "Latest notification updates will be available here !")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.colorAccent)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.accent)
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.first),


                        TapTarget.forView(findViewById(R.id.view_profile), "Your Profile", "You can view and make changes to your profile here !")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.colorAccent)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.accent)
                                .drawShadow(true)
                                .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.fourth))
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        // session.setFirstTime(false);
                        Toasty.success(HomeActivity.this, " You are ready to go !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                }).start();


        ImageView gohome = (ImageView) findViewById(R.id.view_profile);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
    }

}
