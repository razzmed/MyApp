package com.example.myapp.presentation.intro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    public static Fragment newInstance(int position){
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textTitle = view.findViewById(R.id.textTitle);
        ImageView imageView = view.findViewById(R.id.imageView);

        super.onViewCreated(view, savedInstanceState);
        int position = getArguments().getInt(ARG_POSITION);
        switch (position) {
            case 0:
                textTitle.setText("Are you bored?");
                imageView.setImageResource(R.drawable.page_one);

                break;
            case 1:
                textTitle.setText("Do you know what to do?");
                imageView.setImageResource(R.drawable.page_two);

                break;
            case 2:
                textTitle.setText("Use this app!");
                imageView.setImageResource(R.drawable.page_three);

                break;
        }
    }
}
