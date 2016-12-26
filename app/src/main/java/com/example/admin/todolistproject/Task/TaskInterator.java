package com.example.admin.todolistproject.Task;

import com.example.admin.todolistproject.Model.Task;
import com.example.admin.todolistproject.common.ITasksActionsInteractor;

/**
 * Created by Admin on 26/12/2016.
 */

public interface TaskInterator extends ITasksActionsInteractor{
    /**
     * @param task
     */
    void create(Task task);
}
