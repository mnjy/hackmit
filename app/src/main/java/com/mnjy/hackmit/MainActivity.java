package com.mnjy.hackmit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class MainActivity extends Activity {
    private static final int MENU_HOME = 0;
    private static final int MENU_CREATE = 1;
    private static final int MENU_MAP = 2;
    private static final int MENU_PROFILE = 3;
    private static final int MENU_SETTINGS = 4;

    private static final String TAG = "MAIN_ACTIVITY";

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

        //TODO: set fragmentToGoTo
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
