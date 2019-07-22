package sk.ttomovcik.onefeed.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import sk.ttomovcik.onefeed.R;

public class Feed extends Fragment
{
    private WebView wv_feed;
    private String[] socialNetworks =
            {
                    "4chan",
                    "Facebook",
                    "Instagram",
                    "Pinterest",
                    "Tumlbr",
                    "Twitter",
                    "Reddit",
                    "VK"
            };
    private String[] socialNetworkUrls =
            {
                    "https://www.boards.4chan.org/",
                    "https://www.facebook.com/",
                    "https://www.instagram.com/",
                    "https://www.pinterest.com/",
                    "https://www.tumblr.com/",
                    "https://www.twitter.com",
                    "https://www.reddit.com/",
                    "https://www.vk.com/"
            };

    public Feed()
    {
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        int nightModeFlags = Objects.requireNonNull(getContext())
                .getResources()
                .getConfiguration()
                .uiMode &
                Configuration.UI_MODE_NIGHT_MASK;

        // Find views
        wv_feed = view.findViewById(R.id.wv_feed);
        FloatingActionButton fab_toggleSite = view.findViewById(R.id.fab_toggleSite);
        final TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.title_feed));

        // Prepare webView
        wv_feed.getSettings().setDomStorageEnabled(true);
        wv_feed.getSettings().setDatabaseEnabled(true);
        wv_feed.getSettings().setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(wv_feed, true);
        wv_feed.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view, String url)
            {
                wv_feed.setVisibility(View.VISIBLE);
            }
        });
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES)
        {
            wv_feed.loadData("<html><body bgcolor=\"black\"></body></html>",
                    "text/html", "UTF-8");
        }
        setRetainInstance(true);

        fab_toggleSite.setOnClickListener(view1 ->
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(getString(R.string.title_dialog_toggleSite));
            builder.setItems(socialNetworks, (dialog, which) ->
            {
                switch (which)
                {
                    case 0:
                        tv_title.setText(socialNetworks[0]);
                        wv_feed.loadUrl(socialNetworkUrls[0]);
                        break;
                    case 1:
                        tv_title.setText(socialNetworks[1]);
                        wv_feed.loadUrl(socialNetworkUrls[1]);
                        break;
                    case 2:
                        tv_title.setText(socialNetworks[2]);
                        wv_feed.loadUrl(socialNetworkUrls[2]);
                        break;
                    case 3:
                        tv_title.setText(socialNetworks[3]);
                        wv_feed.loadUrl(socialNetworkUrls[3]);
                        break;
                    case 4:
                        tv_title.setText(socialNetworks[4]);
                        wv_feed.loadUrl(socialNetworkUrls[4]);
                        break;
                    case 5:
                        tv_title.setText(socialNetworks[5]);
                        wv_feed.loadUrl(socialNetworkUrls[5]);
                        break;
                    case 6:
                        tv_title.setText(socialNetworks[6]);
                        wv_feed.loadUrl(socialNetworkUrls[6]);
                        break;
                    case 7:
                        tv_title.setText(socialNetworks[7]);
                        wv_feed.loadUrl(socialNetworkUrls[7]);
                        break;
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        return view;
    }

    /**
     * @param url   Target website to get stored cookies fom
     * @param cName Target cookie name
     * @return String -> value of target cookie
     */
    public String getCookie(String url, String cName)
    {
        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(url);
        String CookieValue = null;
        String[] sTemp = cookies.split(";");
        for (String cStr : sTemp)
        {
            if (cStr.contains(cName))
            {
                String[] temp1 = cStr.split("=");
                CookieValue = temp1[1];
                break;
            }
        }
        return CookieValue;
    }
}
