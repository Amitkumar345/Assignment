package com.example.assignment;


import android.content.Context;
import android.os.AsyncTask;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Crew.class},version = 1)
public abstract class CrewDatabase extends RoomDatabase {
    private static CrewDatabase instance;
    public abstract CrewDao crewDao();
    public static synchronized CrewDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),CrewDatabase.class,"crew_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
