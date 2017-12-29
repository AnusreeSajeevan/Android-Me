package com.example.anu.androidme.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.anu.androidme.R;
import com.example.anu.androidme.data.AndroidImageAssets;

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
        headFragment.setmImageIds(AndroidImageAssets.getHeads());
        headFragment.setmListIndex(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container_head, headFragment)
                .commit();

        /**
         * add {@link BodyPartFragment} to display body
         */
        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
        bodyFragment.setmListIndex(1);
        fragmentManager.beginTransaction()
                .add(R.id.container_body, bodyFragment)
                .commit();

        /**
         * add {@link BodyPartFragment} to display leg
         */
        BodyPartFragment legFragment = new BodyPartFragment();
        legFragment.setmImageIds(AndroidImageAssets.getLegs());
        legFragment.setmListIndex(1);
        fragmentManager.beginTransaction()
                .add(R.id.container_leg, legFragment)
                .commit();
    }
}


