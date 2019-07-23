package sk.ttomovcik.onefeed.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Objects;

import sk.ttomovcik.onefeed.R;

import static android.content.SharedPreferences.*;

public class Settings extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MaterialComponents_DayNight_DarkActionBar);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat
    {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
        {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            String storedTheme;
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(Objects.requireNonNull(getContext()));
            Editor editor = sharedPreferences.edit();
            String appTheme = sharedPreferences.getString("appTheme", "");
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
            int ANDROID_API_VERSION = Build.VERSION.SDK_INT;
            String[] APP_THEMES_TARGET = ANDROID_API_VERSION >= 29 ? APP_THEMES_Q : APP_THEMES_PRE_Q;
            Preference changeTheme = findPreference("appTheme");
            Preference clearData = findPreference("clearData");
            Objects.requireNonNull(changeTheme).setOnPreferenceClickListener(preference ->
            {
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
                return true;
            });
            Objects.requireNonNull(clearData).setOnPreferenceClickListener(preference ->
            {
                Intent intent = new Intent(android.provider.Settings
                        .ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getContext().getPackageName()));
                startActivity(intent);
                return true;
            });
        }
    }
}