package com.ttomovcik.onefeed.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ttomovcik.onefeed.R;

public class Feed extends Fragment
{

    WebView mWebViewFeed;
    TextView mTextViewHeader;

    public Feed()
    {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        mWebViewFeed = view.findViewById(R.id.wv_social);
        mTextViewHeader = view.findViewById(R.id.tv_header);
        mTextViewHeader.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("test", "test");
            }
        });
        return view;
    }
}
