package com.example.waiataapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.waiataapp.carvings.Carving1Fragment;
import com.example.waiataapp.carvings.Carving2Fragment;
import com.example.waiataapp.carvings.Carving3Fragment;
import com.example.waiataapp.carvings.Carving4Fragment;
import com.example.waiataapp.carvings.Carving5Fragment;
import com.example.waiataapp.carvings.Carving6Fragment;
import com.example.waiataapp.carvings.Carving7Fragment;
import com.example.waiataapp.carvings.CarvingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the bottom navigation bar listener
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //sets the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
        setTitle("Waiata - Home");
        bottomNav.setSelectedItemId(R.id.nav_home);
    }

    //method to run the bottom navigation bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            bottomNav.getMenu().setGroupCheckable(0, true, true);
                            setTitle("Waiata - Home");
                            break;
                        case R.id.nav_songs:
                            selectedFragment = new SongFragment();
                            bottomNav.getMenu().setGroupCheckable(0, true, true);
                            setTitle("Waiata");
                            break;
                        case R.id.nav_about_us:
                            selectedFragment = new AboutUsFragment();
                            bottomNav.getMenu().setGroupCheckable(0, true, true);
                            setTitle("Waiata - About Us");
                            break;
                    }

                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    //method to load fragments from home fragment
    public void loadFragment(View view) {
        Fragment selectedFragment = null;
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        switch (view.getId()) {
            case R.id.songs_placeholder:
                selectedFragment = new SongFragment();
                setTitle("Waita");
                bottomNav.getMenu().setGroupCheckable(0, true, true);
                bottomNav.setSelectedItemId(R.id.nav_songs);
                break;
            case R.id.marae_placeholder:
                selectedFragment = new MaraeFragment();
                setTitle("Waiata - Marae");
                bottomNav.getMenu().setGroupCheckable(0, false, true);
                break;
            case R.id.carvings_placeholder:
                selectedFragment = new CarvingsFragment();
                bottomNav.getMenu().setGroupCheckable(0, false, true);
                setTitle("Waiata - Carvings");
                break;
            case R.id.about_us_placeholder:
                selectedFragment = new AboutUsFragment();
                setTitle("Waiata - About Us");
                bottomNav.getMenu().setGroupCheckable(0, true, true);
                bottomNav.setSelectedItemId(R.id.nav_about_us);
                break;
        }

        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
    }

    //methods to load fragments from carving fragments
    //multiple methods used instead of a select statement as methods are used in multiple cases
    public void loadCarving1(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Carving1Fragment()).commit();
    }

    public void loadCarving2(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Carving2Fragment()).commit();
    }

    public void loadCarving3(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Carving3Fragment()).commit();
    }

    public void loadCarving4(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Carving4Fragment()).commit();
    }

    public void loadCarving5(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Carving5Fragment()).commit();
    }

    public void loadCarving6(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Carving6Fragment()).commit();
    }

    public void loadCarving7(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Carving7Fragment()).commit();
    }

    public void loadCarvings(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CarvingsFragment()).commit();
    }
}
