package com.example.anu.androidme.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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
    private OnImageclickListner mCallBack;
    private static final String TAG = MasterListFragment.class.getSimpleName();

    public MasterListFragment() {
    }

    /**
     * interface that triggers a callback in the host activity
     */
    public interface OnImageclickListner{
        void onImageSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_master_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        masterListAdapter = new MasterListAdapter(getActivity(), AndroidImageAssets.getAll());
        gridView.setAdapter(masterListAdapter);

        /**
         * trigger the callback on clicking an image in the gridview
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                mCallBack.onImageSelected(pos);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * override this method to make sure that the host activity has implemented OnImageClickcListener
     * otherwise it will throw an error
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallBack = (OnImageclickListner) context;
        }catch (ClassCastException exception){
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }
    }
}
