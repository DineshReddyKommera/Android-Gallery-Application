package com.example.photoalbum;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.photoalbum.OnButtonPressListener;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A {@link ControlsFragment}, subclass of {@link Fragment}
 * used to place PhotoAlbum controls.
 */
public class ControlsFragment extends Fragment {
    Button nextImage;
    Button previousImage;
    CheckBox galleryView;
    CheckBox slideShow;
    int currentImageIndex = 0, slideShowIndex = 0;
    ArrayList<Integer> imagesList = new ArrayList<>(Arrays.asList(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6
            , R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10, R.drawable.image11));
    OnButtonPressListener onButtonPressListener;
    ImageFragment imageFragment = null;
    ImageListFragment imageListFragment = null;
    CountDownTimer countDownTimer=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_controls, container, false);
        previousImage = viewGroup.findViewById(R.id.mBtnPrevious);
        nextImage = viewGroup.findViewById(R.id.mBtnNext);
        manageButtons();
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressListener.onButtonPressed(imagesList.get(++currentImageIndex),"Next");
                manageButtons();
            }
        });
        previousImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressListener.onButtonPressed(imagesList.get(--currentImageIndex),"Previous");
                manageButtons();
            }
        });
        galleryView = viewGroup.findViewById(R.id.mChkBoxGalleryView);
        slideShow = viewGroup.findViewById(R.id.mChkBoxSlideShow);
        galleryView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    //Disabling other options
                    slideShow.setEnabled(false);
                    previousImage.setEnabled(false);
                    nextImage.setEnabled(false);
                    imageListFragment = new ImageListFragment();
                    imageFragment=null;
                    replaceFragment();
                } else {
                    //Enabling other options as per the application conditions
                    currentImageIndex=0;
                    slideShow.setEnabled(true);
                    previousImage.setEnabled(false);
                    nextImage.setEnabled(true);
                    imageFragment = new ImageFragment(R.drawable.image1);
                    imageListFragment=null;
                    replaceFragment();
                }
            }
        });
        slideShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    //Disabling other options
                    previousImage.setEnabled(false);
                    nextImage.setEnabled(false);
                    galleryView.setEnabled(false);
                     countDownTimer = new CountDownTimer((imagesList.size() - currentImageIndex - 1) * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            Log.d("in Tick", String.valueOf(currentImageIndex));
                            onButtonPressListener.onButtonPressed(imagesList.get(++currentImageIndex),"Next");
                            slideShowIndex = currentImageIndex;
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(getContext(), "Slide show finished", Toast.LENGTH_LONG).show();
                        }
                    };
                    countDownTimer.start();
                } else {
                    countDownTimer.cancel();
                    currentImageIndex = (slideShowIndex == imagesList.size()) ? (slideShowIndex - 1) : slideShowIndex;
                    //Enabling other options as per the application conditions
                    manageButtons();
                    galleryView.setEnabled(true);
                    onButtonPressListener.onButtonPressed(imagesList.get(currentImageIndex),"Next");
                }
            }
        });
        return viewGroup;
    }

    /*
     * Through the overriding of onAttach() we are accessing onButtonPressed implemented in
     * MainActivity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onButtonPressListener = (OnButtonPressListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onButtonPressed");
        }
    }

    /**
     * Enabling and disabling of navigation buttons is managed in this method.
     */
    private void manageButtons() {
        if (currentImageIndex == 0) {
            previousImage.setEnabled(false);
        } else {
            previousImage.setEnabled(true);
        }
        if (currentImageIndex == imagesList.size() - 1) {
            nextImage.setEnabled(false);
        } else {
            nextImage.setEnabled(true);
        }
    }

    /**
     * Fragment transaction between {@link ImageFragment} and
     * {@link ImageListFragment} is managed in this method.
     */
    private void replaceFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(imageFragment!=null){
            fragmentTransaction.replace(R.id.fragment_container, imageFragment);
        } else {
            fragmentTransaction.replace(R.id.fragment_container, imageListFragment);
        }
        fragmentTransaction.commit();
    }

}
