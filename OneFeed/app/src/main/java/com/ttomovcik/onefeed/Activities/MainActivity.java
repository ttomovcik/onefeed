package com.ttomovcik.onefeed.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ttomovcik.onefeed.R;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setToolbarTitle(String title)
    {
        TextView tvTitle = findViewById(R.id.tv_appTitle);
        tvTitle.setText(title);
    }
}