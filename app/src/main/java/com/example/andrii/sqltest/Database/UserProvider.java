package com.example.andrii.sqltest.Database;

import com.example.andrii.sqltest.User;

import java.util.List;

public interface UserProvider {


    List<User> getUsersFromDatabase();
}
