package com.example.admin.todolistproject.Task;

import com.example.admin.todolistproject.Model.Task;

/**
 * Created by Admin on 26/12/2016.
 */

public interface TaskPresenter {
    /**
     * @param task
     */
    void setEditTodo(Task task);
    /**
     * @param title
     * @param description
     * @param completed
     */
    void create(String title, String description, boolean completed);
    void delete();
    void discard();
}
