package com.example.photoalbum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<Integer> imagesList = new ArrayList<>(Arrays.asList(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6
            , R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10, R.drawable.image11));

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImageListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageListFragment newInstance(String param1, String param2) {
        ImageListFragment fragment = new ImageListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_image_list, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new
                GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(),
                imagesList);
        recyclerView.setAdapter(customAdapter);

        return root;
    }
}
