package com.zemosolabs.zettacabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zemosolabs.zetarget.sdk.ZeTarget;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SplashActivity extends Activity implements View.OnClickListener {
    static final String GCM_SENDER_ID ="914500168484";
    static final String ZETARGET_API_KEY = "d6e7225e-bf6c-4b34-885f-f4795e14b066";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZeTarget.initializeWithContextAndKey(getApplicationContext(), ZETARGET_API_KEY, GCM_SENDER_ID);
        setContentView(R.layout.activity_splash);
        SharedPreferences demoData = getSharedPreferences("DemoData",MODE_PRIVATE);
        TextView logInButton = (TextView) findViewById(R.id.login_button_id);
        logInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button_id:
                Intent intent = new Intent(this,HireScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

}
