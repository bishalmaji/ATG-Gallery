package com.bishal.atggallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.Toast;

import com.bishal.atggallery.adapter.ImageAdapter;
import com.bishal.atggallery.model.ImageModel;
import com.bishal.atggallery.viewmodel.ImageViewModel;

import java.io.IOException;
import java.util.List;


public class Home extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<ImageModel> imageList;
    private ImageAdapter adapter;
    private ImageViewModel viewModel;
    private   SwipeRefreshLayout swipeRefreshLayout;
    private String stat="yes";

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
ConstraintLayout constraintLayout;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        constraintLayout=view.findViewById(R.id.fragmenthome);
        showImage();
        RecyclerView recyclerView=view.findViewById(R.id.imagerecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        viewModel=  ViewModelProviders.of(this).get(ImageViewModel.class);
        viewModel.getImagemodel().observe(getViewLifecycleOwner(), new Observer<List<ImageModel>>() {
            @Override
            public void onChanged(List<ImageModel> imageModels) {
                if (imageModels!=null){
                    imageList=imageModels;
                    adapter.setImageList(imageModels);
                }
                else {
                    Toast.makeText(getContext(), "no data found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        try {
            viewModel.makeApiCall();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter=new ImageAdapter(getContext(),imageList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ImageAdapter.OnImageClickListener() {
            @Override
            public void onItemClick(String url, String title, int position) {
                HomeDirections.ActionHome2ToFullImage actionHome2ToFullImage=HomeDirections.actionHome2ToFullImage(url, title);
                Navigation.findNavController(view).navigate(actionHome2ToFullImage);

            }
        });

        swipeRefreshLayout =view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    private void showImage() {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("once", Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(stat, false)) {
            ImageView imageView=new ImageView(getContext());
            imageView.setImageResource(R.drawable.demopic);
            imageView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setVisibility(View.GONE);
                }
            });
            constraintLayout.addView(imageView);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(stat, Boolean.TRUE);
            editor.apply();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    viewModel.makeApiCall();
                } catch (IOException e) {
                    e.printStackTrace();
                }
              adapter.notifyDataSetChanged();
            }
        }, 2000);
    }
}