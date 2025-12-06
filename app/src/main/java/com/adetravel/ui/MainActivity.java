package com.adetravel.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.adetravel.client.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setOnNavigationItemSelectedListener(item -> {
            Fragment frag = null;
            if (item.getItemId() == R.id.nav_destinos) {
                frag = new DestinationsFragment();
            } else if (item.getItemId() == R.id.nav_paquetes) {
                frag = new PackagesFragment();
            } else if (item.getItemId() == R.id.nav_reservas) {
                frag = new MyReservationsFragment();
            }
            if (frag != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
            }
            return true;
        });
        nav.setSelectedItemId(R.id.nav_destinos);
    }
}
