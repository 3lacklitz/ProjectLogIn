package com.example.administrator.projectlogin.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.projectlogin.R;
import com.example.administrator.projectlogin.Util;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.text_data)
    TextView jsonTextView;
    @BindView(R.id.prof_img)
    ImageView profImg;

    String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        jsonData = getIntent().getStringExtra("Json_data");
        jsonTextView.setText(jsonData);
    }

    private String paresJson(String json) {
        String output = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            String imgUrl = "";

            for (int i = 0; i < 1; i++) {
                output += "name : " + jsonObject.optString("name") + "\n";
                output += "lastname : " + jsonObject.optString("lastname") + "\n";
                output += "age : " + jsonObject.optString("age") + "\n";
                output += "university : " + jsonObject.optString("university") + "\n";
                imgUrl = jsonObject.optString("img_url");
            }

            Util.setDownloadImageView(this, imgUrl, profImg);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    @OnClick(R.id.btn_json)
    void onParseJsonButtonClick() {
        jsonTextView.setText(paresJson(jsonData));
    }

}
