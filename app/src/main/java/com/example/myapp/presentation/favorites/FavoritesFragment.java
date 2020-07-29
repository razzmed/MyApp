package com.example.myapp.presentation.favorites;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.App;
import com.example.myapp.R;
import com.example.myapp.model.BoredAction;
import com.like.LikeButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    FavoritesAdapter favoritesAdapter;
    ArrayList<BoredAction> favoritesArrayList = new ArrayList<>();
    LikeButton likeButton;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new FavoritesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView favoritesRV = view.findViewById(R.id.rw_favorites);
        favoritesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        favoritesArrayList.addAll(App.boredStorage.getAllAction());
        favoritesAdapter = new FavoritesAdapter(favoritesArrayList);
        favoritesRV.setAdapter(favoritesAdapter);
        likeButton = view.findViewById(R.id.btn_like_fav);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
