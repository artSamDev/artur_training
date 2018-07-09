package com.example.andrii.sqltest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrii.sqltest.Database.DbHelper;
import com.example.andrii.sqltest.Database.SqliteUserProvider;
import com.example.andrii.sqltest.Database.UserProvider;

import java.util.List;

public class ContactsListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        listView = findViewById(R.id.list);


        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        UserProvider userProvider = new SqliteUserProvider(database);

        listView.setAdapter(new ContactsAdapter(userProvider.getUsersFromDatabase()));
    }

    private static class ContactsAdapter extends BaseAdapter {

        private final List<User> users;

        private ContactsAdapter(List<User> users) {
            this.users = users;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return users.get(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User user = users.get(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contact_cell, parent, false);
                ViewHolder viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

            ViewHolder vh = (ViewHolder) convertView.getTag();

            vh.textEmail.setText(user.getEmail());
            vh.textName.setText(user.getName());
            vh.textPhone.setText(user.getPhone());

            return convertView;
        }

        protected class ViewHolder {
            private TextView textEmail;
            private TextView textPhone;
            private TextView textName;

            public ViewHolder(View view) {
                textEmail = view.findViewById(R.id.textEmail);
                textPhone = view.findViewById(R.id.textPhone);
                textName = view.findViewById(R.id.textName);
            }
        }
    }
}
