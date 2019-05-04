package com.reyhan.smartfarm;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        BottomNavigationView navigationView = findViewById(R.id.bottomNav);

        //FRAGMENTS

        final MainFragment mainFragment = new MainFragment();
        final ArtikelFragment artikelFragment = new ArtikelFragment();
        final ProfilFragment profilFragment = new ProfilFragment();

        setFragment(mainFragment);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.homeBar :
                        setFragment(mainFragment);
                        return true;
                    case R.id.artikelBar :
                        setFragment(artikelFragment);
                        return true;
                    case R.id.profilBar :
                        setFragment(profilFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }


}
