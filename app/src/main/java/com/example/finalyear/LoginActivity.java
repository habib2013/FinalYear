package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyear.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import custom_font.MyEditText;
import custom_font.MyTextView;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    TextView zoo;
    MyTextView create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/Swistblnk Duwhoers Brush.ttf");
        zoo = (TextView) findViewById(R.id.zoo);
        zoo.setTypeface(custom_fonts);

        create = (MyTextView) findViewById(R.id.create);

        MyEditText editTextUsername = (MyEditText) findViewById(R.id.editTextUsername);
        MyEditText editTextPassword = (MyEditText) findViewById(R.id.editTextPassword) ;


        MyTextView buttonLogin = (MyTextView) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }

            private void userLogin() {
                //first getting the values
                final String username = editTextUsername.getText().toString();
                final String password = editTextPassword.getText().toString();

                //validating inputs
                if (TextUtils.isEmpty(username)) {
                    editTextUsername.setError("Please enter your username");
                    editTextUsername.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Please enter your password");
                    editTextPassword.requestFocus();
                    return;
                }




                class UserLogin extends AsyncTask<Void, Void, String> {

                    ProgressBar progressBar;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressBar = (ProgressBar) findViewById(R.id.progressBar);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        progressBar.setVisibility(View.GONE);


                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(s);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                               // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                Toasty.success(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("gender"),
                                        userJson.getString("status")

                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            } else {
                             //   Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                                Toasty.error(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected String doInBackground(Void... voids) {
                        //creating request handler object
                        RequestHandler requestHandler = new RequestHandler();

                        //creating request parameters
                        HashMap<String, String> params = new HashMap<>();
                        params.put("username", username);
                        params.put("password", password);

                        //returing the response
                        return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
                    }
                }

                UserLogin ul = new UserLogin();
                ul.execute();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(it);

            }
        });



    }


}
