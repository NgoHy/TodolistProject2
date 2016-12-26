package com.example.admin.todolistproject.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 26/12/2016.
 */

public class Task implements Parcelable{
    public static final long UNSAVED_ID = Long.MAX_VALUE;

    private long id;
    private String title;
    private String description;
    private boolean completed;
    public Task() {
        this.id          = UNSAVED_ID;
        this.title       = "";
        this.description = "";
        this.completed   = false;
    }

    public Task(Long id, String title, String description, boolean completed) {
        this.id          = id;
        this.title       = title;
        this.description = description;
        this.completed   = completed;
    }

    public Task(Cursor cursor) {
        int idIdx           = cursor.getColumnIndex(TaskContract._ID);
        int titleIdx        = cursor.getColumnIndex(TaskContract.TITLE);
        int descriptionIdx  = cursor.getColumnIndex(TaskContract.DESCRIPTION);
        int completedIdx    = cursor.getColumnIndex(TaskContract.COMPLETED);

        this.id          = cursor.getInt(idIdx);
        this.title       = cursor.getString(titleIdx);
        this.description = cursor.getString(descriptionIdx);
        this.completed   = cursor.getInt(completedIdx) == 1;
    }

    public Task(Parcel in) {
        this.id          = in.readLong();
        this.title       = in.readString();
        this.description = in.readString();
        this.completed   = in.readInt() != 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(completed ? 1 : 0);
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    
    
}
