package com.example.myapp.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.App;
import com.example.myapp.R;
import com.example.myapp.data.BoredApiClient;
import com.example.myapp.model.BoredAction;
import com.google.android.material.slider.RangeSlider;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String valueOfSpinner;
    TextView textViewCategory;
    RangeSlider rangeBarPrice, rangeBarAccessibility;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.boredApiClient.getAction(new BoredApiClient.BoredActionCallback() {
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.d("ololo", boredAction.toString());
            }

            @Override
            public void onFailure(Exception exception) {
                Log.d("ololo", exception.getMessage());

            }
        });

        initViews();
        onSpinner();
    }

    private void initViews() {
        spinner = findViewById(R.id.types_spinner);
        textViewCategory = findViewById(R.id.category);
        rangeBarPrice = findViewById(R.id.range_bar_price);
        rangeBarAccessibility = findViewById(R.id.range_bar_accessibility);

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

    }

    public void btn_Next(View view) {

    }
}
