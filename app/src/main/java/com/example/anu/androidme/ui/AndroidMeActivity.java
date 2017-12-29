package com.example.anu.androidme.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.anu.androidme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidMeActivity extends AppCompatActivity {

    @BindView(R.id.container_head)
    FrameLayout containerBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        ButterKnife.bind(this);

        /**
         * add {@link BodyPartFragment} to display head
         */
        BodyPartFragment headFragment = new BodyPartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container_head, headFragment)
                .commit();
    }
}


