package sk.ttomovcik.onefeed

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity() {

    private var webView: WebView? = null
    private val socialNetworks = arrayOf(
            "4chan",
            "Facebook",
            "Instagram",
            "Pinterest",
            "Tumlbr",
            "Twitter",
            "Reddit",
            "VK")
    private val socialNetworkUrls = arrayOf(
            "https://www.4chan.org/",
            "https://www.facebook.com/",
            "https://www.instagram.com/",
            "https://www.pinterest.com/",
            "https://www.tumblr.com/",
            "https://www.twitter.com",
            "https://www.reddit.com/",
            "https://www.vk.com/")

    @SuppressLint("SetJavaScriptEnabled", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val storedTheme = Integer.parseInt(sharedPref.getString("appTheme", "-1000")!!)
        val nightModeFlags = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        if (storedTheme == 2) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        webView = findViewById(R.id.wv_feed)
        webView!!.settings.domStorageEnabled = true
        webView!!.settings.databaseEnabled = true
        webView!!.settings.javaScriptEnabled = true

        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            webView!!.loadData("<html><body bgcolor=\"black\"></body></html>",
                    "text/html", "UTF-8")
        }

        webView!!.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view12: WebView, url: String) {
                webView!!.visibility = View.VISIBLE
            }
        }
    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.switch_site -> {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.text_switch_site))
            builder.setItems(socialNetworks) { _, pos ->
                when (pos) {
                    0 -> webView!!.loadUrl(socialNetworkUrls[pos])
                    1 -> webView!!.loadUrl(socialNetworkUrls[pos])
                    2 -> webView!!.loadUrl(socialNetworkUrls[pos])
                    3 -> webView!!.loadUrl(socialNetworkUrls[pos])
                    4 -> webView!!.loadUrl(socialNetworkUrls[pos])
                    5 -> webView!!.loadUrl(socialNetworkUrls[pos])
                    6 -> webView!!.loadUrl(socialNetworkUrls[pos])
                    7 -> webView!!.loadUrl(socialNetworkUrls[pos])
                }
            }
            val dialog = builder.create()
            dialog.show()
            true
        }
        R.id.open_settings -> {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = sharedPreferences.edit()
            val appTheme = sharedPreferences.getString("appTheme", "")
            val themesNonQ = arrayOf(
                    getString(R.string.pref_appTheme_setByBatterySaver),
                    getString(R.string.pref_appTheme_light),
                    getString(R.string.pref_appTheme_dark))
            val themesQ = arrayOf(
                    getString(R.string.pref_appTheme_systemDefault),
                    getString(R.string.pref_appTheme_light),
                    getString(R.string.pref_appTheme_dark))
            val androidApiVersion = Build.VERSION.SDK_INT
            val themeTarget = if (androidApiVersion >= 29) themesQ else themesNonQ
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.pref_title_item_appTheme))
            builder.setItems(themeTarget) { _, which ->
                when (which) {
                    0 // Set by battery saver or system default
                    -> if (androidApiVersion >= 29) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        editor.putInt(appTheme, -1).apply()
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                        editor.putInt(appTheme, 3).apply()
                    }
                    1 // Light theme
                    -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        editor.putInt(appTheme, 2).apply()
                    }
                    2 // Dark theme
                    -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        editor.putInt(appTheme, 2).apply()
                    }
                    else -> {
                    }
                }
            }
            val dialog = builder.create()
            dialog.show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}
