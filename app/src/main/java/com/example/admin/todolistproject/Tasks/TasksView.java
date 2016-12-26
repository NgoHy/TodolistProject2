package com.example.admin.todolistproject.Tasks;

import com.example.admin.todolistproject.Model.Task;

import java.util.ArrayList;

/**
 * Created by Admin on 26/12/2016.
 */

public interface TasksView {

    /**
     * Set To.Dos in the Adapter
     *
     * @param tasks
     */
    void setTodos(ArrayList<Task> tasks);

    /**
     * Notify To.Dos data set has changed
     */
    void notifyListDataSetChanged();

    /**
     * Notify item removed in Adapter
     *
     * @param position
     */
    void notifyListItemRemoved(int position);

    /**
     * Notify item inserted in Adapter
     *
     * @param position
     */
    void notifyListItemInserted(int position);

    /**
     * Show Dialog with To.Do actions
     *
     * @param task
     */
    void showItemDialog(Task task, CharSequence items[]);

    /**
     * Show TodoView to edit old To.Do
     *
     * @param task
     */
    void showTodoViewToEdit(Task task);

    /**
     * Show TodoView to create new To.Do
     */
    void showTodoView();

}
