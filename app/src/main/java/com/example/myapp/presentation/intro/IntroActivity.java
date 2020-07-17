package com.example.myapp.presentation.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapp.presentation.main.MainActivity;
import com.example.myapp.R;

public class IntroActivity extends AppCompatActivity {

    private Button skip, next;
    private ViewPager viewPager;
    private LinearLayout layoutOnBoardIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        layoutOnBoardIndicator = findViewById(R.id.onBoardIndicator);

        skip = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(IntroActivity.this);
                finish();
            }
        });

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardIndicator(position);
                if (position == SectionPagerAdapter.PAGES_COUNT - 1) {
                    next.setText("Start");
                    skip.setVisibility(View.GONE);
                } else {
                    next.setText("Next");
                    skip.setVisibility(View.VISIBLE);
                }
                setupOnBoardIndicator();
                setCurrentOnBoardIndicator(0);
            }
        });

    }

    private void onNextClick() {
        if (viewPager.getCurrentItem() < SectionPagerAdapter.PAGES_COUNT - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } else {
            MainActivity.start(IntroActivity.this);
            finish();
        }
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {
        public static final int PAGES_COUNT = 3;

        public SectionPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return IntroFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }

    private void setupOnBoardIndicator() {
        ImageView[] indicator = new ImageView[viewPager.getCurrentItem()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicator.length; i++) {
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboard_indicator_2));
            indicator[i].setLayoutParams(layoutParams);
            layoutOnBoardIndicator.addView(indicator[i]);
        }
    }

    private void setCurrentOnBoardIndicator(int index) {
        int childCount = layoutOnBoardIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnBoardIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboard_indicator));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboard_indicator_2));
            }
        }
    }
}
