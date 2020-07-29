package com.example.myapp.presentation.main;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.App;
import com.example.myapp.R;
import com.example.myapp.data.BoredApiClient;
import com.example.myapp.model.BoredAction;
import com.example.myapp.presentation.favorites.FavoritesFragment;
import com.google.android.material.slider.RangeSlider;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.text.MessageFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    Spinner spinner;
    String valueOfSpinner;
    TextView textViewCategory, textViewExplore, textViewPrice;
    RangeSlider rangeBarPrice, rangeBarAccessibility;
    private List<Float> price, accessibility;
    Float minPrice, maxPrice, minAccessibility, maxAccessibility;
    ProgressBar progressBar;
    ImageView iconReplace, iconOnePerson, iconTwoPerson, iconThreePerson, iconThreePlusPerson;
    Button btnMainNext;
    LikeButton likeButton;
    BoredAction saveBoredAction;

    public MainFragment() {

    }

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        spinner = view.findViewById(R.id.types_spinner);
        textViewCategory = view.findViewById(R.id.category);
        textViewExplore = view.findViewById(R.id.explore_text);
        textViewPrice = view.findViewById(R.id.price_text);
        rangeBarPrice = view.findViewById(R.id.range_bar_price);
        rangeBarAccessibility = view.findViewById(R.id.range_bar_accessibility);
        progressBar = view.findViewById(R.id.progressBar);
        iconReplace = view.findViewById(R.id.icon_replace);
        iconOnePerson = view.findViewById(R.id.icon_one_user);
        iconTwoPerson = view.findViewById(R.id.icon_two_user);
        iconThreePerson = view.findViewById(R.id.icon_three_user);
        iconThreePlusPerson = view.findViewById(R.id.icon_three_plus_user);
        btnMainNext = view.findViewById(R.id.btn_main_next);
        likeButton = view.findViewById(R.id.btn_like);


        onSpinner();
        onRangeBar();
        btn_Next();
        addFavorites();
    }

    public void onSpinner() {
        String[] dropdownCategory = getResources().getStringArray(R.array.types);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueOfSpinner = spinner.getSelectedItem().toString();
                textViewCategory.setText(valueOfSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "No item is selected", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onRangeBar() {
        rangeBarPrice.addOnChangeListener(((slider, value, fromUser) -> {
            try {
                price = slider.getValues();
                minPrice = price.get(0);
                maxPrice = price.get(1);
                Log.e("ololo", price.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                if (price == null) {
                    Toast.makeText(getContext(), "Wrong price", Toast.LENGTH_SHORT).show();
                    e.getMessage();
                }
            }
        }));
        rangeBarAccessibility.addOnChangeListener(((slider, value, fromUser) -> {
            try {
                accessibility = slider.getValues();
                minAccessibility = accessibility.get(0);
                maxAccessibility = accessibility.get(1);
                Log.e("ololo", accessibility.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                if (accessibility == null) {
                    Toast.makeText(getContext(), "Not available", Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    public void setImageIcon() {
        iconReplace.setVisibility(View.VISIBLE);
        iconOnePerson.setVisibility(View.GONE);
        iconTwoPerson.setVisibility(View.GONE);
        iconThreePerson.setVisibility(View.GONE);
        iconThreePlusPerson.setVisibility(View.GONE);
    }

    public void participantQuantity(BoredAction boredAction) {
        switch (boredAction.getParticipants()) {
            case 1:
                setImageIcon();
                if (boredAction.getParticipants() == 1)
                    iconReplace.setImageResource(R.drawable.ic_user_1);
                break;
            case 2:
                if (boredAction.getParticipants() == 2)
                    iconReplace.setImageResource(R.drawable.ic_user_2);
                break;
            case 3:
                if (boredAction.getParticipants() == 3)
                    iconReplace.setImageResource(R.drawable.ic_user_3);
                break;
            case 4:
                if (boredAction.getParticipants() == 4)
                    iconReplace.setImageResource(R.drawable.ic_user_3_plus);
                break;
        }
    }

    public void btn_Next() {
        btnMainNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.boredApiClient.getAction(valueOfSpinner, null, null, minPrice, maxPrice, null, minAccessibility, maxAccessibility, new BoredApiClient.BoredActionCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        Log.e("ololo", boredAction.toString());
                        textViewExplore.setText(boredAction.getActivity());
                        textViewPrice.setText(MessageFormat.format("{0} $", boredAction.getPrice().toString()));
                        participantQuantity(boredAction);
                        progressBar.setProgress((int) (boredAction.getAccessibility() * 100), true);
                        saveBoredAction = boredAction;
                    }

                    @Override
                    public void onFailure(Exception exception) {
                        Toast.makeText(getContext(), "No internet", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    public void addFavorites() {
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                App.boredStorage.saveBoredAction(saveBoredAction);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                App.boredStorage.deleteBoredAction(saveBoredAction);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        addFavorites();
    }

    @Override
    public void onPause() {
        super.onPause();
        addFavorites();
    }
}
