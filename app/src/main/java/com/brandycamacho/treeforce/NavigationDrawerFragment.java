package com.brandycamacho.treeforce;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brandycamacho.treeforce.widget.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    View v;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public static final String PREF_FILE_NAME="testpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private boolean isDrawerOpened=false;
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set layout for this fragment
        v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);


        Typeface mTypeFaceLightItalic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-LightItalic.ttf");
        Typeface mTypeFaceBoldItalic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-BoldItalic.ttf");

        TextView tvUserName = (TextView) v.findViewById(R.id.tv_user_full_name);
        TextView tvUserEmail = (TextView) v.findViewById(R.id.tv_user_email_address);

        tvUserName.setTypeface(mTypeFaceBoldItalic);
        tvUserEmail.setTypeface(mTypeFaceLightItalic);


        // get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.elv_navigation_menu);

        // preparing list data
        createData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);



        // setting list adapter
        expListView.setAdapter(listAdapter);


        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
//                Toast.makeText(getActivity(),
//                "Group Clicked " + listDataHeader.get(groupPosition),
//                Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });


        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String subItem = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                String catagory = listDataHeader.get(groupPosition);


                Toast.makeText(
                        getActivity(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition),

                        "Catagory = " + catagory + "\nSub Item = " + subItem + "\nID = " + id ,

                        Toast.LENGTH_SHORT)
                        .show();


                if(catagory == "Zones" && id == 0) {
                    Toast.makeText(getActivity(),"Hello - Item 0 within Zones!", Toast.LENGTH_SHORT).show();

                }
                return false;
            }

        });

        // Inflate the layout for this fragment
        return v;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.8) {
                    toolbar.setAlpha(1 - slideOffset /4);
                }
                Log.d("TreeForce","Offset " + slideOffset);
            }
        };
        if(!mUserLearnedDrawer &&! mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString (preferenceName, preferenceValue);
//        editor.commit()
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString (preferenceName,defaultValue);
    }

    public void setUp(DrawerLayout drawerLayout, android.widget.Toolbar toolbar) {
    }

    // Create test data for list view
    public void createData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> top250 = new ArrayList<String>();
        List<String> nowShowing = new ArrayList<String>();
        List<String> comingSoon = new ArrayList<String>();


        // Adding child data
        listDataHeader.add("Locations");
        listDataHeader.add("Zones");
        listDataHeader.add("Equipment");

        for (int i = 0; i < 5; i++) {
//            listDataHeader.add("Test " + j);
//            for (int i = 0; i < 5; i++) {
                // Adding child data
                top250.add("Sub Item" + i);
                nowShowing.add("Sub Item" + i);
                comingSoon.add("Sub Item" + i);
            }
            listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
            listDataChild.put(listDataHeader.get(1), nowShowing);
            listDataChild.put(listDataHeader.get(2), comingSoon);

    }


}