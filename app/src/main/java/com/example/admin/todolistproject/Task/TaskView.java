package com.example.admin.todolistproject.Task;

/**
 * Created by Admin on 26/12/2016.
 */

public interface TaskView {
    /**
     *
     * @param title field
     * @param description field
     * @param completed checkbox
     */
    void updateFields(String title, String description, boolean completed);
    void finishView();
}
