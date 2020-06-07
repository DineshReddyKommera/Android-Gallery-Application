package com.example.photoalbum;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.photoalbum.OnButtonPressListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ControlsFragment extends Fragment {
    Button nextImage;
    Button previousImage;
    CheckBox galleryView;
    CheckBox slideShow;
    int currentImageIndex = 0;
    ArrayList<Integer> imagesList = new ArrayList<>(Arrays.asList(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6
            , R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10, R.drawable.image11));
    OnButtonPressListener onButtonPressListener;

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
        if (currentImageIndex == 0)
            previousImage.setEnabled(false);
        else
            previousImage.setEnabled(true);
        if (currentImageIndex == imagesList.size() - 1)
            nextImage.setEnabled(false);
        else
            nextImage.setEnabled(true);
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressListener.onButtonPressed(imagesList.get(++currentImageIndex));
                if (currentImageIndex == 0)
                    previousImage.setEnabled(false);
                else
                    previousImage.setEnabled(true);
                if (currentImageIndex == imagesList.size() - 1)
                    nextImage.setEnabled(false);
                else
                    nextImage.setEnabled(true);
            }
        });
        previousImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressListener.onButtonPressed(imagesList.get(--currentImageIndex));
                if (currentImageIndex == 0)
                    previousImage.setEnabled(false);
                else
                    previousImage.setEnabled(true);
                if (currentImageIndex == imagesList.size() - 1)
                    nextImage.setEnabled(false);
                else
                    nextImage.setEnabled(true);
            }
        });
        galleryView = viewGroup.findViewById(R.id.mChkBoxGalleryView);
        slideShow = viewGroup.findViewById(R.id.mChkBoxSlideShow);
        galleryView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    slideShow.setEnabled(false); // disable checkbox
                    previousImage.setEnabled(false);
                    nextImage.setEnabled(false);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    ImageListFragment imageListFragment=new ImageListFragment();
                    fragmentTransaction.replace(R.id.fragment_container,imageListFragment);
                    fragmentTransaction.commit();
                } else {
                    slideShow.setEnabled(true);
                    previousImage.setEnabled(true);
                    nextImage.setEnabled(true);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    ImageFragment imageFragment=new ImageFragment();
                    fragmentTransaction.replace(R.id.fragment_container,imageFragment);
                    fragmentTransaction.commit();
                }
            }
        });
        slideShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    galleryView.setEnabled(false); // disable checkbox
                } else {
                    galleryView.setEnabled(true);
                }
            }
        });


        return viewGroup;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onButtonPressListener = (OnButtonPressListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onButtonPressed");
        }
    }


}
