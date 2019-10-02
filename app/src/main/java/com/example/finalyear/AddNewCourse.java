package com.example.finalyear;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.finalyear.URLs;

import custom_font.MyEditText;
import custom_font.MyTextView;

import static android.view.View.GONE;

public class AddNewCourse extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;


    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
         MyEditText coursename = (MyEditText) findViewById(R.id.editCoursename);
        MyEditText coursecode  = (MyEditText)  findViewById(R.id.editCoursecode);
        MyEditText coursestatus = (MyEditText) findViewById(R.id.editCoursestatus);
        Spinner department = (Spinner) findViewById(R.id.department);
    Button add = (Button) findViewById(R.id.addcourse);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        createCourse();
            }

            private void createCourse() {

                String name = coursename.getText().toString().trim();
                String code  = coursecode.getText().toString().trim();
                String status = coursestatus.getText().toString().trim();


                if (TextUtils.isEmpty(name)){
                    coursename.setError("Please enter course name ");
                    coursename.requestFocus();
                    return;

                }

                if (TextUtils.isEmpty(code)){
                    coursecode.setError("Please enter course course code ");
                    coursecode.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(status)){
                    coursestatus.setError("Please enter course course status ");
                    coursestatus.requestFocus();
                    return;

                }

                HashMap<String, String> params = new HashMap<>();
                params.put("code", name);
                params.put("unit", code);
                params.put("status", status);

                PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_CREATE_HERO, params, CODE_POST_REQUEST);
                request.execute();
            }
        });


    }



    private void readHeroes() {
        PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_READ_HEROES, null, CODE_GET_REQUEST);
        request.execute();
    }



    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    //refreshHeroList(object.getJSONArray("heroes"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }


}


