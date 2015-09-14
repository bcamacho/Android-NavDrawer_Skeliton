package com.brandycamacho.treeforce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Welcome extends ActionBarActivity {

    int count = 1;



    private Toolbar toolbar;
    Fragment welcomeScreen = new Welcome_S1_Fragment();
    FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app_bar);

//      ***  Fragment Content
        ft.replace(R.id.fragment_main_content, welcomeScreen);
        ft.commit();
//      *** End Fragment Content

//      *** ToolBar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

//        toolbar.setNavigationIcon(R.drawable.ic_good);
        toolbar.setTitle("Application");
        toolbar.setSubtitle("Inventory Made Simple");
        toolbar.setLogo(R.drawable.ic_launcher);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

//      *** End of ToolBar

//      *** Navigation Drawer
        if (count == 1) {
            NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                    getSupportFragmentManager()
                            .findFragmentById(R.id.fragment_navigation_drawer);
            drawerFragment.setUp
                    (
                    R.id.fragment_navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout),
                    toolbar
                    );
        }
//      *** End of Navigation Drawer


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings was clicked", Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
