package com.example.finalyear.lists;


import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.finalyear.Adders.AddNewCourse;
import com.example.finalyear.R;
import com.example.finalyear.database.MyVolley;
import com.example.finalyear.database.RequestHandler;
import com.example.finalyear.database.URLs;
import com.example.finalyear.model.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import custom_font.MyEditText;
import custom_font.MyTextView;
import es.dmoral.toasty.Toasty;

public class CourseList extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    EditText editTextHeroId, editTextName, editTextRealname;
    RatingBar ratingBar;
    Spinner spinnerTeam;
    ProgressBar progressBar;
    ListView listView;
    Button buttonAddUpdate;

    List<Course> heroList;

    private List<String> devices;
    private Spinner spinner;

    private List<String> departments;
    private Spinner spinnerDepartment;

    private List<String> lecturers;
    private Spinner spinnerLecturer;

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        devices = new ArrayList<>();
        departments = new ArrayList<>();
        lecturers = new ArrayList<>();


        listView = (ListView) findViewById(R.id.listViewHeroes);

        heroList = new ArrayList<>();


        readHeroes();
        //loadRegisteredDevices();

      //  loadRegisteredDepartments();

//        loadRegisteredLecturers();


        ImageView back  = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

//    private void loadRegisteredLecturers() {
//        //        progressDialog = new ProgressDialog(this);
////        progressDialog.setMessage("Fetching Devices...");
////        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_READ_LECTURER,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //  progressDialog.dismiss();
//                        JSONObject obj = null;
//                        try {
//                            obj = new JSONObject(response);
//                            if (!obj.getBoolean("error")) {
//                                JSONArray jsonDevices = obj.getJSONArray("users");
//
//                                for (int i = 0; i < jsonDevices.length(); i++) {
//                                    JSONObject d = jsonDevices.getJSONObject(i);
//                                    lecturers.add(d.getString("username"));
//                                }
//
//                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                                        CourseList.this,
//                                        android.R.layout.simple_spinner_dropdown_item,
//                                        lecturers);
//
//                                spinnerLecturer.setAdapter(arrayAdapter);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//
//        };
//        MyVolley.getInstance(this).addToRequestQueue(stringRequest);
//
//
//
//    }

//    private void loadRegisteredDepartments() {
//        //        progressDialog = new ProgressDialog(this);
////        progressDialog.setMessage("Fetching Devices...");
////        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_READ_DEPARTMENT,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //  progressDialog.dismiss();
//                        JSONObject obj = null;
//                        try {
//                            obj = new JSONObject(response);
//                            if (!obj.getBoolean("error")) {
//                                JSONArray jsonDevices = obj.getJSONArray("users");
//
//                                for (int i = 0; i < jsonDevices.length(); i++) {
//                                    JSONObject d = jsonDevices.getJSONObject(i);
//                                    departments.add(d.getString("dept_name"));
//                                }
//
//                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                                        CourseList.this,
//                                        android.R.layout.simple_spinner_dropdown_item,
//                                        departments);
//
//                                spinnerDepartment.setAdapter(arrayAdapter);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//
//        };
//        MyVolley.getInstance(this).addToRequestQueue(stringRequest);
//
//
//    }

//    private void loadRegisteredDevices() {
////        progressDialog = new ProgressDialog(this);
////        progressDialog.setMessage("Fetching Devices...");
////        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_READ_LEVEL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //  progressDialog.dismiss();
//                        JSONObject obj = null;
//                        try {
//                            obj = new JSONObject(response);
//                            if (!obj.getBoolean("error")) {
//                                JSONArray jsonDevices = obj.getJSONArray("users");
//
//                                for (int i = 0; i < jsonDevices.length(); i++) {
//                                    JSONObject d = jsonDevices.getJSONObject(i);
//                                    devices.add(d.getString("level_name"));
//                                }
//
//                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                                        CourseList.this,
//                                        android.R.layout.simple_spinner_dropdown_item,
//                                        devices);
//
//                                spinner.setAdapter(arrayAdapter);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//
//        };
//        MyVolley.getInstance(this).addToRequestQueue(stringRequest);
//    }



    private void readHeroes() {
        PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_READ_COURSE, null, CODE_GET_REQUEST);
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

            heroList.add(new Course(
                    obj.getInt("id"),
                    obj.getInt("lecturer_id"),
                    obj.getString("code"),
                    obj.getString("unit"),
                    obj.getString("status"),
                    obj.getString("department"),
                    obj.getString("level")

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
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toasty.success(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshHeroList(object.getJSONArray("courses"));
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

    class HeroAdapter extends ArrayAdapter<Course> {
        List<Course> heroList;

        public HeroAdapter(List<Course> heroList) {
            super(CourseList.this, R.layout.courselist, heroList);
            this.heroList = heroList;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.courselist, null, true);
            final Course hero = heroList.get(position);

            MyTextView textViewName = listViewItem.findViewById(R.id.textViewName);
            ImageView dels = listViewItem.findViewById(R.id.delete);
            ImageView ups = listViewItem.findViewById(R.id.update);

            dels.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(CourseList.this);

                    builder.setTitle("Delete " + hero.getCode())
                            .setMessage("Are you sure you want to delete it?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteHero(hero.getId());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            });

            ups.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showUpdateDialog();
                }

                public View showUpdateDialog() {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(CourseList.this);
                    alertDialog.setIcon(R.drawable.clogo);
                    alertDialog.setTitle("Update Course");
                    alertDialog.setMessage("Your updates will be available on item refresh");

                    LayoutInflater inflater = getLayoutInflater();
                    View updatenow = inflater.inflate(R.layout.updatecourse, null, true);

                    final MyEditText editCoursecode = (MyEditText) updatenow.findViewById(R.id.editCoursename);
                    final MyEditText editCourseunit = (MyEditText) updatenow.findViewById(R.id.editCoursecode);
                    final MyEditText editCoursestatus = (MyEditText) updatenow.findViewById(R.id.editCoursestatus);

//                    spinner = (Spinner) updatenow.findViewById(R.id.level);
//                    spinnerDepartment = (Spinner) updatenow.findViewById(R.id.department);
                    spinnerLecturer = (Spinner) updatenow.findViewById(R.id.lecturer_id);

                    int courseid = hero.getId();


//                    HashMap<String, String> params = new HashMap<>();
//                    params.put("id", courseid);
//                    params.put("name", name);
//                    params.put("realname", realname);
//                    params.put("rating", String.valueOf(rating));
//                    params.put("teamaffiliation", team);
//
//
//                    PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_UPDATE_HERO, params, CODE_POST_REQUEST);
//                    request.execute();







                    alertDialog.setView(updatenow);



                    alertDialog.show();
                    return updatenow;


                }
            });


            textViewName.setText(hero.getCode());

            return listViewItem;
        }
    }
}