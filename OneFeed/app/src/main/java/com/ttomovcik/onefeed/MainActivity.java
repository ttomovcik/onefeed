package com.ttomovcik.onefeed;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    public void setToolbarDescription(String description, Boolean setVisibility)
    {
        TextView tvTitle = findViewById(R.id.tv_appTitleDescription);
        tvTitle.setText(description);
        if (setVisibility)
        {
            tvTitle.setVisibility(View.VISIBLE);
        }
    }
}
