package com.example.finalyear;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyear.lists.UserList;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

import custom_font.MyTextView;
import es.dmoral.toasty.Toasty;


public class AddUserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        final String PREFS_NAME = "myPrefsFile" +
                "";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        if (settings.getBoolean("my_first_time",true)){
            tapview();
            settings.edit().putBoolean("my_first_time",false).commit();

        }

        MyTextView listuser = (MyTextView) findViewById(R.id.listuser);
        listuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AddUserActivity.this, UserList.class);
                startActivity(it);
            }
        });

ImageView back  = (ImageView) findViewById(R.id.back);
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
}

    private void tapview() {

        new TapTargetSequence(this)
                .targets(

                        TapTarget.forView(findViewById(R.id.back), "Back Button", "Safely use the back button when you are through !")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.colorAccent)
                                .titleTextSize(22)
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
                        Toasty.success(AddUserActivity.this, " You are ready to go !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                }).start();


    }

}