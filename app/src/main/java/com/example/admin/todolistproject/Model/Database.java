package com.example.admin.todolistproject.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 26/12/2016.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public Database(Context context) {
        super(context, context.getFilesDir().getPath() + "/" + DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TaskContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
