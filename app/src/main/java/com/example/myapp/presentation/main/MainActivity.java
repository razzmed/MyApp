package com.example.myapp.presentation.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.App;
import com.example.myapp.R;
import com.example.myapp.data.BoredApiClient;
import com.example.myapp.model.BoredAction;
import com.google.android.material.slider.RangeSlider;

import java.text.MessageFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String valueOfSpinner;
    TextView textViewCategory, textViewExplore, textViewPrice;
    RangeSlider rangeBarPrice, rangeBarAccessibility;
    private List<Float> price, accessibility;
    Float minPrice, maxPrice, minAccessibility, maxAccessibility;
    ProgressBar progressBar;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        onSpinner();
        onRangeBar();
    }

    private void initViews() {
        spinner = findViewById(R.id.types_spinner);
        textViewCategory = findViewById(R.id.category);
        textViewExplore = findViewById(R.id.explore_text);
        textViewPrice = findViewById(R.id.price_text);
        rangeBarPrice = findViewById(R.id.range_bar_price);
        rangeBarAccessibility = findViewById(R.id.range_bar_accessibility);
        progressBar = findViewById(R.id.progressBar);

    }


    private void onSpinner() {
        String[] dropdownCategory = getResources().getStringArray(R.array.types);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dropdownCategory);
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
                Toast.makeText(MainActivity.this, "No item is selected", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(this, "Wrong price", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, "Not available", Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    public void btn_Next(View view) {
        App.boredApiClient.getAction(valueOfSpinner, null, null, minPrice, maxPrice, null, minAccessibility, maxAccessibility, new BoredApiClient.BoredActionCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.e("ololo", boredAction.toString());
                textViewExplore.setText(boredAction.getActivity());
                textViewPrice.setText(MessageFormat.format("{0} $", boredAction.getPrice().toString()));
                progressBar.setProgress ((int) (boredAction.getAccessibility()*100), true);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
