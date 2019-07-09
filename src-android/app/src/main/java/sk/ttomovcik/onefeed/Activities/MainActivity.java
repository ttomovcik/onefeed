package sk.ttomovcik.onefeed.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sk.ttomovcik.onefeed.Fragments.Feed;
import sk.ttomovcik.onefeed.Fragments.Home;
import sk.ttomovcik.onefeed.Fragments.Settings;
import sk.ttomovcik.onefeed.R;


public class MainActivity extends AppCompatActivity
{
    FragmentTransaction fragmentTransaction;
    BottomNavigationView bottomNavigationView;

    /*
    * TODO (1): Finish home UI - show profile image and info, connected accounts - read cookies?
    * TODO (2): Add webView into feed with SMSwitcher
    * TODO (3): Options -> Dark theme switcher dialog thingy
    * TODO (4): Profile UI -> Store name and photo
    * TODO (5): Add Google Firebase for backup
    * TODO (6): Add Crashlytics
    * TODO (7): Prolly add ButterKnife
    */

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.nav_home:
                    switchFragment(new Home());
                    return true;
                case R.id.nav_feed:
                    switchFragment(new Feed());
                    return true;
                case R.id.nav_settings:
                    switchFragment(new Settings());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment(new Home());
    }

    /**
     * @param fragmentName Name of the target fragment. Called with 'new Fragment()'
     */
    public void switchFragment(Fragment fragmentName)
    {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentName);
        fragmentTransaction.commit();
    }
}
