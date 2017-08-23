package com.example.administrator.projectlogin.model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 20/8/2560.
 */

public class Login {
    @SerializedName("status_code")
    private Integer statusCode;
    @SerializedName("status_description")
    private String statusDescription;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}
