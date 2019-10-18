package com.example.finalyear;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.finalyear.lists.CourseList;
import com.example.finalyear.model.Course;
import com.example.finalyear.model.User;
import com.google.android.material.checkbox.MaterialCheckBox;


import custom_font.MyEditText;
import custom_font.MyTextView;

public class AddNewCourse extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    public static int getid ;

    ProgressBar progressBar;
    ListView listView;
    List<User> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);

       // listView = (ListView) findViewById(R.id.listViewcourse);

        heroList = new ArrayList<User>();

        readHeroes();


        MyEditText coursename = (MyEditText) findViewById(R.id.editCoursename);
        MyEditText coursecode  = (MyEditText)  findViewById(R.id.editCoursecode);
        MyEditText coursestatus = (MyEditText) findViewById(R.id.editCoursestatus);
        Spinner department = (Spinner) findViewById(R.id.department);
        Spinner level = (Spinner) findViewById(R.id.level);

    Button add = (Button) findViewById(R.id.addcourse);

//        heroList = new Arra
//                List<Course> courseList  = new ArrayList<String>();

        MyTextView listcourse = (MyTextView) findViewById(R.id.listcourse);
        listcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AddNewCourse.this, CourseList.class);
                startActivity(it);

            }
        });

       // progressBar = (ProgressBar) findViewById(R.id.progressBar);
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
                String deps =   department.getSelectedItem().toString();
                String levs =   level.getSelectedItem().toString();
                int lecturer_id = getid;



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
                params.put("department",deps);
                params.put("level",levs);
                params.put("lecturer_id",String.valueOf(lecturer_id));



                PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_CREATE_COURSE, params, CODE_POST_REQUEST);
                request.execute();
            }
        });


    }





    private void readHeroes() {
        PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_READ_LECTURER, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void readCourse() {
        PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_READ_LECTURER, null, CODE_GET_REQUEST);
        request.execute();
    }




    private void deleteHero(int id) {
        PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_DELETE_COURSE + id, null, CODE_GET_REQUEST);
        request.execute();
    }


    private void refreshHeroList(JSONArray heroes) throws JSONException {
        heroList.clear();

        for (int i = 0; i < heroes.length(); i++) {
            JSONObject obj = heroes.getJSONObject(i);

            heroList.add(new User(
                    obj.getInt("id"),
                    obj.getString("username"),
                    obj.getString("email"),
                    obj.getString("gender"),
                    obj.getString("status")
            ));
        }

        HeroAdapter adapter = new HeroAdapter(heroList);

        listView.setAdapter(adapter);
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
         //   progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshHeroList(object.getJSONArray("users"));
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

    class HeroAdapter extends ArrayAdapter<User> {
        List<User> heroList;

        public HeroAdapter(List<User> heroList) {
            super(AddNewCourse.this, R.layout.courses, heroList);
            this.heroList = heroList;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.courses, null, true);

            MyTextView textViewName = listViewItem.findViewById(R.id.coursename);
            MaterialCheckBox checkcourse = (MaterialCheckBox) findViewById(R.id.checkcourse);


            final User hero = heroList.get(position);

            textViewName.setText(hero.getUsername());

            String getLecturers = (String) textViewName.getText();
            getid = hero.getId();



            return listViewItem;
        }
    }
    }



