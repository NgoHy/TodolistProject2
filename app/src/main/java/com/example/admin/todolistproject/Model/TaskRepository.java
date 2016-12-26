package com.example.admin.todolistproject.Model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.admin.todolistproject.Task.TaskInterator;
import com.example.admin.todolistproject.Tasks.TasksInteractor;

import java.util.ArrayList;

/**
 * Created by Admin on 26/12/2016.
 */

public class TaskRepository implements TasksInteractor, TaskInterator {
    private ContentResolver contentResolver;

    public ContentResolver getContentResolver() {
        return contentResolver;
    }

    public TaskRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public ArrayList<Task> get() {
        ArrayList<Task> todoList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(
                TaskContract.CONTENT_URI,
                TaskContract.PROJECTION_ALL,
                null,
                null,
                TaskContract.COMPLETED + " ASC, " +
                        TaskContract.EDITED + " DESC");

        if (null == cursor || !cursor.moveToNext()) {
            return todoList;
        }

        do {
            Task todo = new Task(cursor);
            todoList.add(todo);
        } while (cursor.moveToNext());

        cursor.close();
        return todoList;
    }

    @Override
    public void update(Task todo) {
        save(todo);
    }

    @Override
    public void create(Task todo) {
        save(todo);
    }

    @Override
    public void delete(Task todo) {
        String[] selectionArgs = {String.valueOf(todo.getId())};
        getContentResolver().delete(
                TaskContract.CONTENT_URI,
                TaskContract._ID + " = ?",
                selectionArgs);
    }

    private void save(Task todo) {
        ContentValues values = new ContentValues();
        values.put(TaskContract.TITLE, todo.getTitle());
        values.put(TaskContract.DESCRIPTION, todo.getDescription());
        values.put(TaskContract.COMPLETED, todo.isCompleted());

        if (todo.getId() == Task.UNSAVED_ID) {
            Uri insertUri = getContentResolver().insert(TaskContract.CONTENT_URI, values);
            todo.setId(Long.valueOf(insertUri.getLastPathSegment()));
        } else {
            String[] selectionArgs = {String.valueOf(todo.getId())};
            getContentResolver().update(
                    TaskContract.CONTENT_URI,
                    values,
                    TaskContract._ID + " = ?",
                    selectionArgs);
        }
    }
}
