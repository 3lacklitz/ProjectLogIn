package com.example.administrator.projectlogin.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.projectlogin.R;
import com.example.administrator.projectlogin.model.Hero;
import com.example.administrator.projectlogin.model.HeroList;
import com.example.administrator.projectlogin.service.HeroListCallService;
import com.example.administrator.projectlogin.view.adapter.HeroListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroListFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView heroList;

    public static HeroListFragment newInstance() {
        return new HeroListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero_list, container, false);
        ButterKnife.bind(this, view);

        //build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://coemygroup.fr/")
                //dao data access object
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroListCallService callService = retrofit.create(HeroListCallService.class);
        Call<HeroList> call = callService.getHeroList();

        call.enqueue(new Callback<HeroList>() {
            @Override
            public void onResponse(Call<HeroList> call, Response<HeroList> response) {
                List<Hero> heroes = response.body().getElement();

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL, false);
                HeroListAdapter heroListAdapter = new HeroListAdapter(getContext(), heroes);
                heroList.setLayoutManager(layoutManager);
                heroList.setAdapter(heroListAdapter);
            }

            @Override
            public void onFailure(Call<HeroList> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        new DownLoadHeroAsyncTask().execute("http://coemygroup.fr/test-mobile/iOS/json/test2.json");
        return view;
    }

//    private class DownLoadHeroAsyncTask extends AsyncTask<String, Void, String> {
//        String response = "";
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getContext(), "Loading Data...", Toast.LENGTH_LONG).show();
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
//            List<Hero> heroes = new ArrayList<>();
//
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                for (int i = 0; i < 1; i++) {
//                    JSONArray elementArray = jsonObject.optJSONArray("elements");
//                    for (int j = 0; j < elementArray.length(); j++) {
//                        JSONObject heroObject = elementArray.getJSONObject(j);
//                        Hero hero = new Hero();
//                        hero.setImage(heroObject.getString("image"));
//                        hero.setTitle(heroObject.getString("title"));
//                        hero.setIntro(heroObject.getString("intro"));
//                        hero.setYear(heroObject.getString("year"));
//                        hero.setText(heroObject.getString("text"));
//                        hero.setColor(heroObject.getString("color"));
//                        heroes.add(j, hero);
//                    }
//                }
//
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                HeroListAdapter heroListAdapter = new HeroListAdapter(getContext(), heroes);
//                heroList.setLayoutManager(layoutManager);
//                heroList.setAdapter(heroListAdapter);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
