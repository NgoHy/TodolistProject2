package com.example.admin.todolistproject.Tasks;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.todolistproject.Model.Task;
import com.example.admin.todolistproject.Model.TaskRepository;
import com.example.admin.todolistproject.R;

import java.util.ArrayList;

/**
 * Created by Admin on 26/12/2016.
 */

public class CTasksPresenter implements TasksPresenter {
    private TasksView view;
    private TasksInteractor interactor;
    private Context context;

    public CTasksPresenter(TasksView view, Context context) {
        this.view       = view;
        this.interactor = new TaskRepository(context.getContentResolver());
        this.context    = context;
    }

    @Override
    public void refreshSession() {
        view.setTodos(interactor.get());
        view.notifyListDataSetChanged();
    }

    @Override
    public void onAddTodoButtonClick() {
        view.showTodoView();
    }

    @Override
    public void onClickTodoItemToEdit(Task task) {
        view.showTodoViewToEdit(task);
    }

    @Override
    public void onLongClickTodoItem(Task task) {
        CharSequence items[] = new CharSequence[] {
                context.getString(R.string.edit),
                context.getString(R.string.delete)
        };
        view.showItemDialog(task, items);
    }

    @Override
    public void updateTodoIsCompleted(Task task, boolean completed, int position) {
        task.setCompleted(completed);
        interactor.update(task);
        ArrayList<Task> tasks = interactor.get();
        view.notifyListItemRemoved(position);
        view.setTodos(tasks);
        for (Task todoObject : tasks) {
            if (todoObject.getId() == task.getId()) {
                view.notifyListItemInserted(tasks.indexOf(todoObject));
            }
        }
    }

    @Override
    public void delete(Task task) {
        interactor.delete(task);
        view.setTodos(interactor.get());
        view.notifyListDataSetChanged();
        Toast.makeText(context, "Đã xóa.", Toast.LENGTH_SHORT).show();
    }
}
