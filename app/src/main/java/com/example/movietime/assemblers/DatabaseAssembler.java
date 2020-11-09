package com.example.movietime.assemblers;

import com.example.movietime.Database.SQLDatabase;
import com.example.movietime.DummyDB;
import com.example.movietime.interfaces.Database;

public class DatabaseAssembler {
    static Database database = null;
    public static Database getInstance() {
        if (database == null) {
            database = new DummyDB();
        }
        return database;
    }
}
