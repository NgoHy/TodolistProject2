package com.example.admin.todolistproject.Model;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Admin on 26/12/2016.
 */

public class TaskContract implements BaseColumns {
    private TaskContract() {}

    public static final String AUTHORITY = "com.example.admin.todolistproject.Model.provider.TaskProvider";

    public static final String ENDPOINT = "task";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + ENDPOINT);

    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.example.admin.todolistproject.Model.task";

    public static final String TABLE = "task";

    public static final String TITLE        = "title";
    public static final String DESCRIPTION  = "description";
    public static final String EDITED       = "edited";
    public static final String COMPLETED    = "completed";

    public static final String[] PROJECTION_ALL = {_ID, TITLE, DESCRIPTION, EDITED, COMPLETED};

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE
                    + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TITLE + " TEXT, "
                    + DESCRIPTION + " TEXT, "
                    + EDITED + " INTEGER, "
                    + COMPLETED + " INTEGER DEFAULT 0 "
                    + ");";

    public static final String FIXTURE =
            "INSERT INTO " + TABLE
                    + " (" + TITLE
                    + ", " + DESCRIPTION
                    + ", " + EDITED
                    + ", " + COMPLETED
                    + ") "
                    + "VALUES ('TodoFixture', 'Description', 1434703928, 0);";
}
