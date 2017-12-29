package com.example.anu.androidme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anu.androidme.R;
import com.example.anu.androidme.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

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

    private int mListIndex;
    private List<Integer> mImageIds = new ArrayList<>();
    private static final String TAG = BodyPartFragment.class.getSimpleName();

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
         * Show the image in the list of head images
         * if {@link mImageIds} is not null
         */
        Log.d(TAG, "index : " + mListIndex);
        if (null != mImageIds)
            imgBodyPart.setImageResource(mImageIds.get(mListIndex));
        else
            Log.d(TAG, "mImageIds is null");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }
}
