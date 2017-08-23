package com.example.administrator.projectlogin.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.projectlogin.R;
import com.example.administrator.projectlogin.view.fragment.HeroListFragment;

public class HeroListActivity extends AppCompatActivity {
    HeroListFragment heroListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);


        heroListFragment = HeroListFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frg_hero_list, heroListFragment)
                .commit();

//        new LoginAsyncTask().execute("http://coemygroup.fr/test-mobile/iOS/json/test2.json");
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
//            Intent intent = new Intent(getBaseContext(), DetailActivity.class);
//            intent.putExtra("Json_data", result);
//            startActivity(intent);
////            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
//        }
//
//    }
}
