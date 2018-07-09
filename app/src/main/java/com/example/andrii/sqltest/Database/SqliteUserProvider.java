package com.example.andrii.sqltest.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.andrii.sqltest.User;

import java.util.ArrayList;
import java.util.List;

public class SqliteUserProvider implements UserProvider {

    private static final String TAG = "SqliteUserProvider";
    private final SQLiteDatabase database;

    public SqliteUserProvider(@NonNull SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public List<User> getUsersFromDatabase() {
        Cursor cursor = database.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        List<User> users = new ArrayList<>(cursor.getCount());

        try {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(cursor.getString(cursor.getColumnIndex(DbHelper.KEY_NAME)), cursor.getString(cursor.getColumnIndex(DbHelper.KEY_EMAIL)), cursor.getString(cursor.getColumnIndex(DbHelper.KEY_PHONE)));
                    users.add(user);
                } while (cursor.moveToNext());
            }

        } catch (Throwable e) {
            Log.e(TAG, "getUsersFromDatabase: ", e);
        } finally {
            cursor.close();
        }

        return users;
    }
}
