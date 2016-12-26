package com.example.admin.todolistproject.Task;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.todolistproject.Model.Task;
import com.example.admin.todolistproject.Model.TaskRepository;

/**
 * Created by Admin on 26/12/2016.
 */

public class CTaskPresenter implements TaskPresenter {
    private TaskView view;
    private TaskInterator interactor;
    private Context context;

    private Task editTodo;

    public CTaskPresenter(TaskView view, Context context) {
        this.view       = view;
        this.interactor = new TaskRepository(context.getContentResolver());
        this.context    = context;
    }

    @Override
    public void setEditTodo(Task todo) {
        this.editTodo = todo;
        view.updateFields(todo.getTitle(), todo.getDescription(), todo.isCompleted());
    }

    @Override
    public void create(String title, String description, boolean completed) {
        if (!title.equals("") || !description.equals("")) {
            Task todo = (this.editTodo != null) ? this.editTodo : new Task();
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setCompleted(completed);
            interactor.create(todo);
            Toast.makeText(context, "Đã lưu thành công!", Toast.LENGTH_SHORT).show();
        }
        view.finishView();
    }

    @Override
    public void delete() {
        if (editTodo != null) {
            interactor.delete(editTodo);
            Toast.makeText(context, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
        }
        view.finishView();
    }

    @Override
    public void discard() {
        Toast.makeText(context, "Hủy bỏ thao tác!", Toast.LENGTH_SHORT).show();
        view.finishView();
    }

}
