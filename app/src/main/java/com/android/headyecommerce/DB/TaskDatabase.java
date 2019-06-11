package com.android.headyecommerce.DB;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {ProductTable.class}, version = 1)
public abstract class TaskDatabase  extends RoomDatabase{

    public abstract TableDao tableDao();

    public static TaskDatabase sInstance;

    public static synchronized TaskDatabase getDatabaseInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
        }
        return sInstance;
    }

    // Create the database
    static TaskDatabase create(Context context) {
        RoomDatabase.Builder<TaskDatabase> builder =    Room.databaseBuilder(context.getApplicationContext(),
                TaskDatabase.class,
                "ProductTable");
        return builder.build();
    }
}
