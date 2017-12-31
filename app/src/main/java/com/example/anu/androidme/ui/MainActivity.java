package com.example.anu.androidme.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.anu.androidme.R;
import com.example.anu.androidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageclickListner{

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legIndex = 0;

    private Button btnNext;

    public static final String KEY_HEAD_INDEX = "head_index";
    public static final String KEY_BODY_INDEX = "body_index";
    public static final String KEY_LEG_INDEX = "leg_index";

    /**
     * indicates whether to display single screen ui or two pane ui
     * single pane disply referes to phone screen
     * two pane referes to large screen tablets
     */
    private boolean isTwoPaneUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.linear_layout_two_pane) != null){
            isTwoPaneUi = true;

            /**
             * set gridview number of columns to 2
             */
            GridView gridView = (GridView) findViewById(R.id.gridView);
            gridView.setNumColumns(2);

            /**
             * make next button invisible
             */
            btnNext.setVisibility(View.GONE);

            if (null == savedInstanceState){

                // In two-pane mode, add initial BodyPartFragments to the screen

                /**
                 * add {@link BodyPartFragment} to display head
                 */
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                headFragment.setmListIndex(headIndex);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.container_head, headFragment)
                        .commit();

                /**
                 * add {@link BodyPartFragment} to display body
                 */
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setmListIndex(bodyIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.container_body, bodyFragment)
                        .commit();

                /**
                 * add {@link BodyPartFragment} to display leg
                 */
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                legFragment.setmListIndex(legIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.container_leg, legFragment)
                        .commit();
            }

        }else {
            isTwoPaneUi = false;
        }

        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToAndroidMe();
            }
        });

    }

    @Override
    public void onImageSelected(int position) {
        determineIndices(position);
    }

    /**
     * method to determine the head, body and leg indices
     */
    private void determineIndices(int position) {

        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position /12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

       if (isTwoPaneUi){
           BodyPartFragment bodyPartFragment = new BodyPartFragment();
           switch (bodyPartNumber){
               case 0:
                   // A head image has been clicked
                   // Give the correct image resources to the new fragment
                   bodyPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                   bodyPartFragment.setmListIndex(listIndex);
                   // Replace the old head fragment with a new one
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.container_head, bodyPartFragment)
                           .commit();
                   break;
               case 1:
                   // A body image has been clicked
                   // Give the correct image resources to the new fragment
                   bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                   bodyPartFragment.setmListIndex(listIndex);
                   // Replace the old head fragment with a new one
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.container_body, bodyPartFragment)
                           .commit();
                   break;
               case 3:
                   // A leg image has been clicked
                   // Give the correct image resources to the new fragment
                   bodyPartFragment.setmImageIds(AndroidImageAssets.getLegs());
                   bodyPartFragment.setmListIndex(listIndex);
                   // Replace the old head fragment with a new one
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.container_leg, bodyPartFragment)
                           .commit();
                   break;

           }
       }
       else {
           // Set the currently displayed item for the correct body part fragment
           switch(bodyPartNumber) {
               case 0: headIndex = listIndex;
                   break;
               case 1: bodyIndex = listIndex;
                   break;
               case 2: legIndex = listIndex;
                   break;
               default: break;
           }
       }
    }

    /**
     *  Method to pass indices details to {@link AndroidMeActivity}
     */
    private void redirectToAndroidMe() {
        /**
         * wrap head, body and leg indices ina bundle
         */
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_HEAD_INDEX, headIndex);
        bundle.putInt(KEY_BODY_INDEX, bodyIndex);
        bundle.putInt(KEY_LEG_INDEX, legIndex);

        Intent intentAndroidMe = new Intent(this, AndroidMeActivity.class);
        intentAndroidMe.putExtras(bundle);
        startActivity(intentAndroidMe);
    }


}
