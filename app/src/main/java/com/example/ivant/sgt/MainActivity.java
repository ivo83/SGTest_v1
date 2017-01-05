/*
    written by Ivan Tymchuk
    4.01.2017
 */

package com.example.ivant.sgt;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    EditText editText;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        short RedColor = 51;
        short GreenColor = 51;
        short BlueColor = 51;


        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        if(v.getId() == R.id.button){

            while (RedColor < 256){

                GreenColor = 51;    // every new cycle GreenColor starts from 51

                while(GreenColor < 256){

                    BlueColor = 51; // every new cycle BlueColor starts from 51

                    while(BlueColor < 256){

                        // saving Red color
                        contentValues.put(DBHelper.cRed, RedColor);

                        // saving Green color
                        contentValues.put(DBHelper.cGreen, GreenColor);

                        // saving Blue color
                        contentValues.put(DBHelper.cBlue, BlueColor);

                        // saving to database
                        database.insert(DBHelper.TABLE_COLORS, null, contentValues);

                        BlueColor += 51;

                    }

                    GreenColor += 51;
                }

                RedColor += 51;
            }
        }

        Cursor cursor = database.query(DBHelper.TABLE_COLORS, null, null, null, null, null,
                DBHelper.cRed + " ASC, " + DBHelper.cGreen + " DESC");

        if(cursor.moveToFirst()){
            int IdIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int rColor = cursor.getColumnIndex(DBHelper.cRed);
            int gColor = cursor.getColumnIndex(DBHelper.cGreen);
            int bColor = cursor.getColumnIndex(DBHelper.cBlue);

            editText.getText().clear();     // clear if it is already filled

            for(int z=1; z<10; z++){

                editText.append(cursor.getString(rColor) + " "
                        + cursor.getString(gColor) + " " + cursor.getString(bColor) + "\n" );
                cursor.moveToNext();

            }

        }

        cursor.close();

        database.delete(DBHelper.TABLE_COLORS, null, null);

        dbHelper.close();

    }
}