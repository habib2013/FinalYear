package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyear.model.User;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_empty);

        User user = SharedPrefManager.getInstance(this).getUser();

        TextView checker  = (TextView) findViewById(R.id.checker);


        String checkvery = user.getStatus();






    }
}
