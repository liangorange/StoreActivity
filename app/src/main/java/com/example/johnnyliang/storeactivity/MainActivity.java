package com.example.johnnyliang.storeactivity;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView counter;
    private int count;
    private SharedPreferences setting;

    public static final String fileName = "MyCountFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = (TextView) findViewById(R.id.counter);
        //SharedPreferences setting = getSharedPreferences(fileName, 0);
        setting = getSharedPreferences(fileName, 0);

        count = setting.getInt("Count", 0);
        counter.setText("" + count);
        //count = Integer.parseInt(setting.getString(fileName, ""));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void advanceCounter(View view) {
        count++;

        counter.setText("" + count);
    }

    public void storeCount(View view) {
        SharedPreferences.Editor editor = setting.edit();
        editor.putInt("Count", count);

        // Commit edit
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
