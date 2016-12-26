package com.example.admin.todolistproject.Tasks;

import com.example.admin.todolistproject.Model.Task;

/**
 * Created by Admin on 26/12/2016.
 */

public interface TasksPresenter {
    
    void refreshSession();

    
    void onAddTodoButtonClick();

    /**
     * @param task
     */
    void onClickTodoItemToEdit(Task task);

    /**
     * @param task
     */
    void onLongClickTodoItem(Task task);

    /**
     * @param task
     * @param completed
     * @param position
     */
    void updateTodoIsCompleted(Task task, boolean completed, int position);

    /**
     * @param task
     */
    void delete(Task task);


}
