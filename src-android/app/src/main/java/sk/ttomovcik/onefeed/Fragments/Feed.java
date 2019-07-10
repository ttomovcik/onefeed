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
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import sk.ttomovcik.onefeed.R;

public class Feed extends Fragment implements View.OnClickListener
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

        // Find views
        AppCompatButton acb_facebook = view.findViewById(R.id.acb_facebook);
        AppCompatButton acb_twitter = view.findViewById(R.id.acb_twitter);
        AppCompatButton acb_reddit = view.findViewById(R.id.acb_reddit);
        AppCompatButton acb_devrant = view.findViewById(R.id.acb_devrant);
        wv_feed = view.findViewById(R.id.wv_feed);

        // Set OnclickListeners
        acb_facebook.setOnClickListener(this);
        acb_twitter.setOnClickListener(this);
        acb_reddit.setOnClickListener(this);
        acb_devrant.setOnClickListener(this);

        // Prepare webView
        wv_feed.getSettings().setDomStorageEnabled(true); /* Allow localStorage */
        wv_feed.getSettings().setDatabaseEnabled(true); /* Allow DBs */
        wv_feed.getSettings().setJavaScriptEnabled(true); /* Allow JavaScript */
        if (android.os.Build.VERSION.SDK_INT >= 21) /* Allow cookies */
        {
            CookieManager.getInstance().setAcceptThirdPartyCookies(wv_feed, true);
        }
        else
        {
            CookieManager.getInstance().setAcceptCookie(true);
        }
        wv_feed.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view, String url)
            {
                // Nobody likes getting flash banged while webView loads
                new Timer().schedule(
                        new TimerTask()
                        {
                            @Override
                            public void run()
                            {
                                wv_feed.setVisibility(View.VISIBLE);
                            }
                            /*
                             * Sorry for the delay. On some devices webView loads
                             * slower and still displays white screen which sucks.
                             */
                        },
                        100);
            }
        });
        // Check if night mode is on and set background to black
        int nightModeFlags =
                Objects.requireNonNull(getContext()).getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES)
        {
            wv_feed.loadData("<html><body bgcolor=\"black\"></body></html>",
                    "text/html", "UTF-8");
        }
        // TODO: If running after FTR, load first item (Facebook) automatically
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onClick(View view)
    {
        String URL_SOCIAL_FACEBOOK = "https://www.facebook.com";
        String URL_SOCIAL_TWITTER = "https://www.twitter.com";
        String URL_SOCIAL_REDDIT = "https://www.reddit.com";
        String URL_SOCIAL_DEVRANT = "https://www.devrant.com/";
        switch (view.getId())
        {
            case R.id.acb_facebook:
                wv_feed.loadUrl(URL_SOCIAL_FACEBOOK);
                break;
            case R.id.acb_twitter:
                wv_feed.loadUrl(URL_SOCIAL_TWITTER);
                break;
            case R.id.acb_reddit:
                wv_feed.loadUrl(URL_SOCIAL_REDDIT);
                break;
            case R.id.acb_devrant:
                wv_feed.loadUrl(URL_SOCIAL_DEVRANT);
                break;
        }
    }
}
