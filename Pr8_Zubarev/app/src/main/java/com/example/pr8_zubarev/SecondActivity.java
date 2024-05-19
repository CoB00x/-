package com.example.pr8_zubarev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.jetbrains.annotations.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        OneTimeWorkRequest work1 = new OneTimeWorkRequest.Builder(MyWorker1.class).build();
        OneTimeWorkRequest work2 = new OneTimeWorkRequest.Builder(MyWorker2.class).build();

        WorkManager.getInstance(getApplicationContext()).enqueue(work1);
        WorkManager.getInstance(getApplicationContext()).enqueue(work2);

        Button button = findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qwe();
            }
        });
    }

    public void qwe(){
        OneTimeWorkRequest downloadImage =
                new OneTimeWorkRequest.Builder(ImageLoader.class).build();
        WorkManager.getInstance(this).enqueue(downloadImage);
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(downloadImage.getId()).observe(
                this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo.getOutputData().getString("url") != null) {
                            String url = workInfo.getOutputData().getString("url");
                            Pattern pattern = Pattern.compile("h.+\"");
                            Matcher matcher = pattern.matcher(url);
                            while (matcher.find()) {
                                url = url.substring(matcher.start(), matcher.end()-1);
                            }
                            new DownloadImage((ImageView) findViewById(R.id.imageView)).execute(url);
                        }
                    }
                }
        );
    }
}