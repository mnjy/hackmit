package com.mnjy.hackmit;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    public static final int MENU_HOME = 0;
    public static final int MENU_CREATE = 1;
    public static final int MENU_MAP = 2;
    public static final int MENU_PROFILE = 3;
    public static final int MENU_SETTINGS = 4;

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
            case MENU_CREATE:
                fragmentToGoTo = new CreateFragment();
            case MENU_MAP:
                break;
            case MENU_PROFILE:
                break;
            case MENU_SETTINGS:
                break;
            default:
                break;
        }

        if (fragmentToGoTo != null) {
            //TODO: fragment transaction
        }

        return super.onOptionsItemSelected(item);
    }
}
