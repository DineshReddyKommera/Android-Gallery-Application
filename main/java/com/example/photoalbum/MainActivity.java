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
        loadFragment(R.drawable.image1,Animation.SLIDE_RIGHT); // fragment is loaded with first image from the list
    }

    /**
     * Implementation of abstract methods from {@link OnButtonPressListener} interface
     * @param imageID
     * @param direction
     */
    @Override
    public void onButtonPressed(Integer imageID,Integer direction) {
        loadFragment(imageID,direction);
    }

    /**
     * Fragment is loaded with default
     * @param imageID or imageID send from {@link ImageFragment}
     * @param direction specifies in which direction image navigation
     * is taking place
     */
    private void loadFragment(Integer imageID,Integer direction){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        ImageFragment imageFragment=new ImageFragment(imageID);
        if(direction == Animation.SLIDE_RIGHT)
            fragmentTransaction.setCustomAnimations(R.anim.right_to_left_enter, R.anim.right_to_left_exit)
                    .replace(R.id.fragment_container,imageFragment);
        else if(direction == Animation.SLIDE_LEFT)
            fragmentTransaction.setCustomAnimations(R.anim.left_to_right_enter, R.anim.left_to_right_exit)
                .replace(R.id.fragment_container,imageFragment);
        else {
            fragmentTransaction.replace(R.id.fragment_container,imageFragment);
        }
        fragmentTransaction.commit();
    }
}