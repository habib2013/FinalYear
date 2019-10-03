package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import custom_font.MyEditText;
import custom_font.MyTextView;

import static android.view.View.GONE;

public class AddNewLecturer extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;


    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lecturer);

        ImageView back  = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        TextView  lecturer  = (TextView) findViewById(R.id.lecturer);
        MyEditText lecturerUser = (MyEditText) findViewById(R.id.lecturerUser);
        MyEditText lecturerMail = (MyEditText) findViewById(R.id.lecturerMail);
        MyEditText lecturerPassword = (MyEditText) findViewById(R.id.lecturerPassword);
        MyTextView submitlecturer  = (MyTextView) findViewById(R.id.submitlecturer);

        submitlecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createLecturer();
            }

            private void createLecturer() {
                String user = lecturerUser.getText().toString().trim();
                String mail = lecturerMail.getText().toString().trim();
                String password = lecturerPassword.getText().toString().trim();
                String Lecturer  = lecturer.getText().toString().trim();

                if(TextUtils.isEmpty(user)){
                    lecturerUser.setError("Please enter lecturer name");
                    lecturerUser.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(mail)){
                    lecturerMail.setError("Please enter lecturer mail");
                    lecturerMail.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    lecturerPassword.setError("Please enter lecturer password");
                    lecturerPassword.requestFocus();
                    return;
                }

                HashMap<String,String> params = new HashMap<>();
                params.put("username",user);
                params.put("email",mail);
                params.put("password",password);
                params.put("gender",Lecturer);

                PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_CREATE_USER, params, CODE_POST_REQUEST);
                request.execute();

            }
        });




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
