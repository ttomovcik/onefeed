package sk.ttomovcik.onefeed.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
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

import sk.ttomovcik.onefeed.R;

public class Home extends Fragment
{
    private AppCompatButton
            acb_help_help,
            acb_help_connectingAccounts,
            acb_improve_bugReport,
            acb_improve_suggestFeature;
    private TextView tv_aboutYou_profileName;
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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(
                Objects.requireNonNull(getActivity()).getApplicationContext()));
        acb_help_connectingAccounts = view.findViewById(R.id.acb_help_connectingAccounts);
        acb_help_help = view.findViewById(R.id.acb_help_help);
        tv_aboutYou_profileName = view.findViewById(R.id.tv_aboutYou_profileName);
        acb_improve_bugReport = view.findViewById(R.id.acb_improving_reportBugs);
        acb_improve_suggestFeature = view.findViewById(R.id.acb_improving_suggestFeature);
        ImageView iv_aboutYou_profilePicture = view.findViewById(R.id.iv_aboutYou_profilePicture);
        getProfileInformation();
        setOnclickListeners();
        return view;
    }

    private void getProfileInformation()
    {
        String profileName = sharedPreferences.getString("profileName", null);
        String profilePicturePath = sharedPreferences.getString("imageFilePath", null);
        if (isEmpty(profileName))
            tv_aboutYou_profileName.setText(R.string.title_welcomeMsg_hiThere);
        else
            tv_aboutYou_profileName.setText(profileName);
    }

    private void setOnclickListeners()
    {
        View.OnClickListener openIssuesPage = view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ttomovcik/onefeed/issues")));
        acb_help_connectingAccounts.setOnClickListener(view -> new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ttomovcik/onefeed/wiki/connecting-accounts")));
        acb_help_help.setOnClickListener(view -> new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ttomovcik/onefeed/wiki")));
        acb_improve_bugReport.setOnClickListener(openIssuesPage);
        acb_improve_suggestFeature.setOnClickListener(openIssuesPage);

    }

    private static boolean isEmpty(CharSequence str)
    {
        return str == null || str.length() == 0;
    }
}
