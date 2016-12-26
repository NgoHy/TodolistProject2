package com.example.admin.todolistproject.Tasks;

import com.example.admin.todolistproject.Model.Task;
import com.example.admin.todolistproject.common.ITasksActionsInteractor;

import java.util.ArrayList;

/**
 * Created by Admin on 26/12/2016.
 */

public interface TasksInteractor extends ITasksActionsInteractor {
    /**
     * Get all To.Dos from database
     * @return ArrayList
     */
    ArrayList<Task> get();

    /**
     * Update old To.Do
     *
     * @param task
     */
    void update(Task task);

}
