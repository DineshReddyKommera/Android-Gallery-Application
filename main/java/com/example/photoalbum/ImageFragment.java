package com.example.photoalbum;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


@SuppressLint("ValidFragment")
public class ImageFragment extends Fragment {
    ImageView image;
    int imageResId;


    /**
     * Constructor used for setting the image id for each  fragment
     * @param imageID (Resource)
     */
    @SuppressLint("ValidFragment")
    ImageFragment(Integer imageID){
        imageResId=imageID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup=(ViewGroup)inflater.inflate(R.layout.fragment_image,container,false);
        image=viewGroup.findViewById(R.id.mImageView);
        image.setImageResource(imageResId);
        return viewGroup;
    }

}
