package com.example.anu.androidme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.anu.androidme.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageclickListner{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Clicked : " + position, Toast.LENGTH_SHORT).show();
    }
}
