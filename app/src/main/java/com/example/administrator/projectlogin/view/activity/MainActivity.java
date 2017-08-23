package com.example.administrator.projectlogin.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.projectlogin.R;
import com.example.administrator.projectlogin.Util;
import com.example.administrator.projectlogin.model.Login;
import com.example.administrator.projectlogin.service.LoginService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txt_username)
    EditText userName;
    @BindView(R.id.txt_password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @OnClick(R.id.btn_log_in)
    void onLoginButtonClick() {
        String usernameString = userName.getText().toString();
        String passwordString = password.getText().toString();
//        String paramsUrl = "";
//        try {
//            paramsUrl = "username=" + URLEncoder.encode(usernameString, "UTF-8");
//            paramsUrl += "&password=" + URLEncoder.encode(passwordString, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        if (Util.validateValueAccount(usernameString, passwordString, this)) {
        } else {
//            new LoginAsyncTask().execute("https://tigercoding.000webhostapp.com/login_api.php?", paramsUrl);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://tigercoding.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginService loginService = retrofit.create(LoginService.class);
            Call<Login> call = loginService.getLoginData(usernameString, passwordString);

            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Login login = response.body();

                    if (login.getStatusCode() == 1000) {
                        Intent intent = new Intent(getBaseContext(), HeroListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), login.getStatusDescription(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

//    private class LoginAsyncTask extends AsyncTask<String, Void, String> {
//        String response = "";
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getBaseContext(), "Loading Data...", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                URL url = new URL(params[0]);
//                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//                httpCon.setRequestMethod("POST");
//                httpCon.setDoInput(true);
//                httpCon.setDoOutput(true);
//                httpCon.connect();
//
//                DataOutputStream outStream = new DataOutputStream(httpCon.getOutputStream());
//                outStream.writeBytes(params[1]);
//                outStream.flush();
//                outStream.close();
//
//                InputStream inStream = httpCon.getInputStream();
//                Scanner scanner = new Scanner(inStream, "Windows-874");
//                response = scanner.useDelimiter("\\A").next();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Intent intent = new Intent(getBaseContext(), HeroListActivity.class);
//            intent.putExtra("Json_data", result);
//            startActivity(intent);
////            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
//        }

}

