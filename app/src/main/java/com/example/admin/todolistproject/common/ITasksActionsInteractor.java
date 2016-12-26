package com.example.admin.todolistproject.common;

import com.example.admin.todolistproject.Model.Task;

/**
 * Created by Admin on 26/12/2016.
 */

public interface ITasksActionsInteractor  {
    /**
     * @param task
     */
    void delete(Task task);
}
