package com.thewitcherapp.dao;

import android.content.Context;

import androidx.room.Room;

public class DbUtils {


    public static ArtefactDB getDb(Context context){
        ArtefactDB db = Room.databaseBuilder(context,
                ArtefactDB.class, "database-name").
                allowMainThreadQueries().
                build();
        return db;
    }
}
