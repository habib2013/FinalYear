package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import custom_font.MyTextView;
import es.dmoral.toasty.Toasty;

public class Attendance extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

Dialog daters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);



            ImageView button = (ImageView) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment datePicker = new DatePickerFragment();
                    datePicker.show(getSupportFragmentManager(), "date picker");
                }
            });

            ImageView newbutton = (ImageView) findViewById(R.id.newbutton);


        ImageView back  = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView disableduser = (ImageView) findViewById(R.id.disableduser);
        disableduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it  = new Intent(Attendance.this,VerifyUser.class);
                startActivity(it);
            }
        });



    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//        Calendar d = Calendar.getInstance();
//        d.set(Calendar.YEAR, year);
//        d.set(Calendar.MONTH, month);
//        d.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

      int a =  view.getYear();
       int b = view.getMonth();
      int e =  view.getDayOfMonth();

//        int f =  view.getYear();
//        int g = view.getMonth();
//        int h =  view.getDayOfMonth();

      String shower = a + "/" + b + "/" + e;
//        String newshower = f + "/" + g + "/" + h;

MyTextView anotherdate = (MyTextView) findViewById(R.id.anotherdate);


        MyTextView textView = (MyTextView) findViewById(R.id.textView);
        textView.setText(shower);
//       anotherdate.setText(newshower);


    }
}
