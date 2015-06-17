package com.example.johnnyliang.storeactivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView counter;
    private TextView counterDB;
    private int count;
    private int dataCount;
    private SharedPreferences setting;

    public static final String fileName = "MyCountFile";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = (TextView) findViewById(R.id.counter);
        counterDB = (TextView) findViewById(R.id.counterDB);
        setting = getSharedPreferences(fileName, 0);

        count = setting.getInt("Count", 0);
        counter.setText("" + count);

        // Create database if not already exist
        db = openOrCreateDatabase("CounterDB", MODE_PRIVATE, null);
        // Create new table if not already exist
        db.execSQL("create table if not exists mytable(countNumber integer)");

        String query = "Select * from mytable";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0)
        {
            counterDB.setText("0");
        }
        else
            counterDB.setText("" + getCountDB());
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

        insert(count);
    }

    public int getCountDB() {
        int dbCounter;

        Cursor c = db.rawQuery("select * from mytable", null);
        c.moveToFirst();


        do {
            dbCounter = c.getInt(c.getColumnIndex("countNumber"));
        } while (c.moveToNext());

        return dbCounter;
    }

    public void insert(int value) {
        // Insert data into table
        db.execSQL("insert into mytable values('" + value + "')");
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
