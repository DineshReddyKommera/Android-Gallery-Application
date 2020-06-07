package com.example.photoalbum;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnButtonPressListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }

    @Override
    public void onButtonPressed(Integer imageID) {
        ImageFragment imageFragment=(ImageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        imageFragment.onFragmentInteracation(imageID);
    }

    private void addFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        ImageFragment imageFragment=new ImageFragment();
        fragmentTransaction.add(R.id.fragment_container,imageFragment);
        fragmentTransaction.commit();
    }
}