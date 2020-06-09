package com.example.photoalbum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImageFragment extends Fragment {
    ImageView image;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup=(ViewGroup)inflater.inflate(R.layout.fragment_image,container,false);
        image=viewGroup.findViewById(R.id.mImageView);
        image.setImageResource(R.drawable.image1);
        return viewGroup;
    }

    /**
     * Based on the navigation button action, the image is loaded into UI in this method.
     * @param imageID (Resource)
     */
    public void onFragmentInteracation(Integer imageID){
        image.setImageResource(imageID);
    }
}
