package com.example.anu.androidme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anu.androidme.R;
import com.example.anu.androidme.data.AndroidImageAssets;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Design on 29-12-2017.
 */

public class BodyPartFragment extends Fragment {

    @BindView(R.id.img_body_part)
    ImageView imgBodyPart;
    Unbinder unbinder;

    /**
     * empty constructor is equired for creatng fragment instance
     */
    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * inflate the layout
         */
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.fragment_body_part, container, false);
        unbinder = ButterKnife.bind(this, view);

        /**
         * Show the first image in the list of head images
         */
        imgBodyPart.setImageResource(AndroidImageAssets.getHeads().get(0));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
