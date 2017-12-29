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
    private static final String PARAM_IMAGE_IDS = "image_ids";
    private static final String PARAM_INDEX = "index";

    /**
     * empty constructor is equired for creatng fragment instance
     */
    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null != savedInstanceState){
            mImageIds = savedInstanceState.getIntegerArrayList(PARAM_IMAGE_IDS);
            mListIndex = savedInstanceState.getInt(PARAM_INDEX);
        }

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

        imgBodyPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * increment {@link mIndex} remains < the size od the is array
                 */
                if (mListIndex < mImageIds.size() - 1)
                    mListIndex ++;
                /**
                 * return to the first image when index reaches to the last
                 */
                else
                    mListIndex = 0;

                //update the image
                imgBodyPart.setImageResource(mImageIds.get(mListIndex));
            }

        });

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(PARAM_IMAGE_IDS, (ArrayList<Integer>) mImageIds);
        outState.putInt(PARAM_INDEX, mListIndex);
    }
}
