package com.example.recipe_mania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btnView;
    FrameLayout cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView=findViewById(R.id.btnView);

        btnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.nav_Home){
                    loadFrag(new HomeFragment(),true);
                }
                else if (id==R.id.nav_Explore){
                    loadFrag(new ExploreFragment(),false);
                }
                else if (id==R.id.nav_Favourite){
                    loadFrag(new FavFragment(),false);
                }
                else{
                    loadFrag(new ProfileFragment(),false);
                }
            
                return true;
            }
        });

    btnView.setSelectedItemId(R.id.nav_Home);
    }

    public void loadFrag(Fragment fragment,Boolean flag){
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(flag)
            ft.add(R.id.cont, fragment);
        else
            ft.replace(R.id.cont,fragment);
        ft.commit();

    }
}