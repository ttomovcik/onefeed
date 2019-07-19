package sk.ttomovcik.onefeed.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import sk.ttomovcik.onefeed.Activities.Settings;
import sk.ttomovcik.onefeed.R;

public class QuickSettings extends Fragment
        implements View.OnClickListener
{
    public QuickSettings()
    {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_quick_settings, container, false);
        AppCompatButton acb_appTheme = view.findViewById(R.id.acb_appTheme);
        acb_appTheme.setOnClickListener(this);
        AppCompatButton acb_help = view.findViewById(R.id.acb_help);
        acb_help.setOnClickListener(this);
        MaterialButton btn_advancedSettings = view.findViewById(R.id.btn_advancedSettings);
        btn_advancedSettings.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.acb_appTheme:
                break;
            case R.id.acb_help:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ttomovcik/onefeed/wiki/")));
                break;
            case R.id.btn_advancedSettings:
                startActivity(new Intent(getActivity(), Settings.class));
                break;
        }
    }
}
