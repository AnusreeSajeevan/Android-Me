package com.example.anu.androidme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.anu.androidme.R;
import com.example.anu.androidme.data.AndroidImageAssets;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Design on 30-12-2017.
 */

public class MasterListFragment extends Fragment {

    @BindView(R.id.gridView)
    GridView gridView;
    Unbinder unbinder;

    private MasterListAdapter masterListAdapter;

    public MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_master_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        masterListAdapter = new MasterListAdapter(getActivity(), AndroidImageAssets.getAll());
        gridView.setAdapter(masterListAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
