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
