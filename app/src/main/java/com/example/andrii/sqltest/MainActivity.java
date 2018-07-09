package com.example.andrii.sqltest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrii.sqltest.Database.DbHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MY_LOG = "my_log";

    EditText edName, edEmail, edPhone;
    Button btnInsert, btnShow, btnDelete;

    public static DbHelper db;
    public static SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edTxName);
        edEmail = findViewById(R.id.edTxEmail);
        edPhone = findViewById(R.id.edTxPhone);
        btnDelete = findViewById(R.id.btnDelete);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        btnDelete = findViewById(R.id.btnDelete);

        btnShow.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        db = new DbHelper(this);

    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor;

        switch (view.getId()) {
            case R.id.btnInsert:

                cv.put(DbHelper.KEY_NAME, edName.getText().toString());
                cv.put(DbHelper.KEY_EMAIL, edEmail.getText().toString());
                cv.put(DbHelper.KEY_PHONE, edPhone.getText().toString());

                long rowID = database.insert(DbHelper.TABLE_NAME, "_id", cv);

                Log.d(MY_LOG, "Data inserted" + "\n"
                        + "ROW inserted, ID= " + rowID);
                break;
            case R.id.btnShow:
//                cursor = database.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
//                if (cursor.moveToFirst()) {
//                    do {
//                        Log.d(MY_LOG, "id - " + cursor.getColumnIndex("_id") + "\n"
//                                + "name - " + cursor.getString(cursor.getColumnIndex(DbHelper.KEY_NAME)) + "\n"
//                                + "email - " + cursor.getString(cursor.getColumnIndex(DbHelper.KEY_EMAIL)) + "\n"
//                                + "phone - " + cursor.getString(cursor.getColumnIndex(DbHelper.KEY_PHONE)) + "\n"
//                                + "-----------" + "\n");
//                    }while (cursor.moveToNext());
//                    cursor.close();
//                } else {
//                    Log.d(MY_LOG, "Data is empty");
//                    Toast.makeText(this, "Data is empty", Toast.LENGTH_SHORT).show();
//                }
                startActivity(new Intent(this, ContactsListActivity.class));
                break;
            case R.id.btnDelete:
                database.delete(DbHelper.TABLE_NAME, null, null);
                Log.d(MY_LOG, "Table delete");
                Toast.makeText(this, "Table delete", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
