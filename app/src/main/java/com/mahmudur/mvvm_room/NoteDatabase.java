package com.mahmudur.mvvm_room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; //singleton

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                        /*when we increment the version no of this database,
                        we have to tell room that how to migrate to the new schema.
                        if we dont do this and try to increase the version number,
                        the app will crash because we get an illegal state exception.
                        By using this we can avoid this issue.*/
                    .build();
        }
        return instance;
    }
}
