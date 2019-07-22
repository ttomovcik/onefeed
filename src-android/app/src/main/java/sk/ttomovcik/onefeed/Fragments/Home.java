package sk.ttomovcik.onefeed.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import java.util.Objects;

import sk.ttomovcik.onefeed.Activities.Settings;
import sk.ttomovcik.onefeed.R;

public class Home extends Fragment
{
    private AppCompatButton
            acb_help_help,
            acb_help_appCrash,
            acb_help_connectingAccounts;
    private TextView
            tv_aboutYou_profileName,
            tv_aboutYou_profileHelpMessage;
    private String
            profileName,
            profilePicturePath;
    private ImageView iv_aboutYou_profilePicture;
    private SharedPreferences sharedPreferences;

    public Home()
    {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Objects.requireNonNull(
                        Objects.requireNonNull(getActivity()).getApplicationContext()));
        acb_help_appCrash = view.findViewById(R.id.acb_help_appCrash);
        acb_help_connectingAccounts = view.findViewById(R.id.acb_help_connectingAccounts);
        acb_help_help = view.findViewById(R.id.acb_help_help);
        tv_aboutYou_profileName = view.findViewById(R.id.tv_aboutYou_profileName);
        tv_aboutYou_profileHelpMessage = view.findViewById(R.id.tv_aboutYou_profileHelpMessage);
        iv_aboutYou_profilePicture = view.findViewById(R.id.iv_aboutYou_profilePicture);
        getProfileInformation();
        setOnclickListeners();
        return view;
    }

    private void getProfileInformation()
    {
        /*
         *   If the user has saved name, change text from tap here to change settings to:
         *   "idk, something". TODO: Add helpful message or QoTD.
         */
        profileName = sharedPreferences.getString("profileName", null);
        profilePicturePath = sharedPreferences.getString("imageFilePath", null);
        if (profileName != null) tv_aboutYou_profileName.setText(profileName);
    }

    private void setOnclickListeners()
    {
        tv_aboutYou_profileHelpMessage.setOnClickListener(view ->
        {
            startActivity(new Intent(getActivity(), Settings.class));
        });
        acb_help_help.setOnClickListener(view ->
        {
            // Open wiki on Github
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/ttomovcik/onefeed/wiki")));
        });
        acb_help_appCrash.setOnClickListener(view ->
        {
            // Open dialog with Crashlytics explanation and next steps
        });
        acb_help_connectingAccounts.setOnClickListener(view ->
        {
            // Open wiki on Github
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/ttomovcik/onefeed/wiki/connecting-accounts")));
        });
    }
}
