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
        loadFragment(R.drawable.image1); // fragment is loaded with first image from the list
    }

    /**
     * Implementation of abstract methods from {@link OnButtonPressListener} interface
     * @param imageID
     */
    @Override
    public void onButtonPressed(Integer imageID) {
        loadFragment(imageID);
    }

    /**
     * Fragment is loaded with default
     * @param imageID or imageID send from {@link ImageFragment}
     */
    private void loadFragment(Integer imageID){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        ImageFragment imageFragment=new ImageFragment(imageID);
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit)
                .replace(R.id.fragment_container,imageFragment);
        fragmentTransaction.commit();
    }
}