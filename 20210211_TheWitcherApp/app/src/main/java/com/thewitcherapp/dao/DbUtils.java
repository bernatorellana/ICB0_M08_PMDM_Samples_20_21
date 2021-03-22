package com.thewitcherapp.dao;

import android.content.Context;

import androidx.room.Room;

public class DbUtils {

    private static ArtefactDB db = null;
    public static ArtefactDB getDb(Context context){
        if(db==null) {
            db = Room.databaseBuilder(context,
                    ArtefactDB.class, "database-name").
                    allowMainThreadQueries(). // BIG KK
                    build();
        }
        return db;
    }
}
