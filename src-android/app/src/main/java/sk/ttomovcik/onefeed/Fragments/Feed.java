package sk.ttomovcik.onefeed.Fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import sk.ttomovcik.onefeed.R;

public class Feed extends Fragment
{
    private WebView wv_feed;

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

        // Prepare webView
        wv_feed.getSettings().setDomStorageEnabled(true);
        wv_feed.getSettings().setDatabaseEnabled(true);
        wv_feed.getSettings().setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(wv_feed, true);
        wv_feed.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view, String url)
            {
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        wv_feed.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES)
        {
            wv_feed.loadData("<html><body bgcolor=\"black\"></body></html>",
                    "text/html", "UTF-8");
        }
        setRetainInstance(true);
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
