package sk.ttomovcik.onefeed.Fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import sk.ttomovcik.onefeed.Activities.Settings;
import sk.ttomovcik.onefeed.R;

public class QuickSettings extends Fragment
        implements View.OnClickListener
{
    private String[] APP_THEMES_TARGET;
    private String storedTheme;
    private String appTheme;
    private int ANDROID_API_VERSION;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public QuickSettings()
    {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_quick_settings,
                container, false);
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Objects.requireNonNull(
                        Objects.requireNonNull(getActivity()).getApplicationContext()));
        appTheme = sharedPreferences.getString("appTheme", "");
        String[] APP_THEMES_PRE_Q = {
                getString(R.string.pref_appTheme_setByBatterySaver),
                getString(R.string.pref_appTheme_light),
                getString(R.string.pref_appTheme_dark)
        };
        String[] APP_THEMES_Q = {
                getString(R.string.pref_appTheme_systemDefault),
                getString(R.string.pref_appTheme_light),
                getString(R.string.pref_appTheme_dark)
        };
        ANDROID_API_VERSION = Build.VERSION.SDK_INT;
        APP_THEMES_TARGET = ANDROID_API_VERSION >= 29 ? APP_THEMES_Q : APP_THEMES_PRE_Q;
        AppCompatButton acb_appTheme = view.findViewById(R.id.acb_appTheme);
        AppCompatButton acb_help = view.findViewById(R.id.acb_help);
        MaterialButton btn_advancedSettings = view.findViewById(R.id.btn_advancedSettings);
        acb_appTheme.setOnClickListener(this);
        acb_help.setOnClickListener(this);
        btn_advancedSettings.setOnClickListener(this);
        return view;
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onClick(View view)
    {
        editor = sharedPreferences.edit();
        switch (view.getId())
        {
            case R.id.acb_appTheme:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.pref_title_item_appTheme));
                builder.setItems(APP_THEMES_TARGET, (dialog, which) ->
                {
                    switch (which)
                    {
                        case 0: // Set by battery saver or system default
                            if (ANDROID_API_VERSION >= 29)
                            {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                                editor.putInt(appTheme, -1).apply();
                            }
                            else
                            {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                                editor.putInt(appTheme, 3).apply();
                            }
                            break;
                        case 1: // Light theme
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            editor.putInt(appTheme, 2).apply();
                            break;
                        case 2: // Dark theme
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            editor.putInt(appTheme, 2).apply();
                            break;
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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
