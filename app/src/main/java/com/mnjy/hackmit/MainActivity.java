package com.mnjy.hackmit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    private static final String TAG = "MAIN_ACTIVITY";

    public static final int MENU_HOME = R.id.home;
    public static final int MENU_CREATE = R.id.create;
    public static final int MENU_MAP = R.id.map;
    public static final int MENU_PROFILE = R.id.profile;
    public static final int MENU_SETTINGS = R.id.settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Fragment fragmentToGoTo = null;

        //set fragmentToGoTo
        switch (id){
            case MENU_HOME:
                fragmentToGoTo = new ListFragment();
                break;
            case MENU_CREATE:
                fragmentToGoTo = new CreateFragment();
                break;
            case MENU_MAP:
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case MENU_PROFILE:
                break; //less important
            case MENU_SETTINGS:
                break; //less important
            default:
                break;
        }

        if (fragmentToGoTo != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.container, fragmentToGoTo);
            transaction.addToBackStack(TAG);
            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    public void popBackStack(){
        getFragmentManager().popBackStack();
    }
}
