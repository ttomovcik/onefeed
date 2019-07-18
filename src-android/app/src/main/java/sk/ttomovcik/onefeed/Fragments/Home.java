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

import sk.ttomovcik.onefeed.R;

public class Home extends Fragment
{
    private AppCompatButton
            acb_help_help,
            acb_help_appCrash,
            acb_help_connectingAccounts;

    public Home()
    {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        acb_help_appCrash = view.findViewById(R.id.acb_help_appCrash);
        acb_help_connectingAccounts = view.findViewById(R.id.acb_help_connectingAccounts);
        acb_help_help = view.findViewById(R.id.acb_help_help);
        setOnclickListeners();
        return view;
    }

    private void setOnclickListeners()
    {
        acb_help_help.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Open wiki on Github
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/ttomovcik/onefeed/wiki")));
            }
        });
        acb_help_appCrash.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Open dialog with Crashlytics explanation and next steps
            }
        });
        acb_help_connectingAccounts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Open wiki on Github
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/ttomovcik/onefeed/wiki/connecting-accounts")));
            }
        });
    }
}
