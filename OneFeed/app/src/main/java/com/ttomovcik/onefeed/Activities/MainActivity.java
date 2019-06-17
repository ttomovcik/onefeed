package com.ttomovcik.onefeed.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ttomovcik.onefeed.Fragments.Feed;
import com.ttomovcik.onefeed.Fragments.Home;
import com.ttomovcik.onefeed.Fragments.Me;
import com.ttomovcik.onefeed.R;

public class MainActivity extends AppCompatActivity
{
    FragmentTransaction fragmentTransaction;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        switchFragment(new Home());
                        return true;
                    case R.id.nav_feed:
                        switchFragment(new Feed());
                        return true;
                    case R.id.nav_me:
                        switchFragment(new Me());
                        return true;
                }
                return false;
            }
        });

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_container, new Home());
                fragmentTransaction.commit();
            }
        });
    }

    /**
     * @param fragmentName Name of the target fragment. Called with 'new Fragment()'
     */
    public void switchFragment(Fragment fragmentName)
    {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragmentName);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
