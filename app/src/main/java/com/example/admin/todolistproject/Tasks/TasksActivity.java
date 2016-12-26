package com.example.admin.todolistproject.Tasks;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.admin.todolistproject.BaseActivity;
import com.example.admin.todolistproject.Model.Task;
import com.example.admin.todolistproject.R;
import com.example.admin.todolistproject.Task.TaskActivity;

import java.util.ArrayList;

/**
 * Created by Admin on 26/12/2016.
 */

public class TasksActivity extends BaseActivity implements TasksView{
    public static final String STATE_LIST = "TodoList";

    private TasksPresenter presenter;

    private TasksAdapter tasksAdapter;

    public TasksPresenter getPresenter() {
        if (presenter == null) {
            presenter = new CTasksPresenter(this, this);
        }
        return presenter;
    }

    public RecyclerView getTodoList() {
        return (RecyclerView) findViewById(R.id.todo_list);
    }

    public FloatingActionButton getFabButton() {
        return (FloatingActionButton) findViewById(R.id.fab_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // RecyclerView
        if (savedInstanceState == null) {
            tasksAdapter = new TasksAdapter(this, new ArrayList<Task>(), onTodoClickToEditListener, onTodoLongClickListener);
        } else {
            ArrayList<Task> todoArrayList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            tasksAdapter = new TasksAdapter(this, todoArrayList, onTodoClickToEditListener, onTodoLongClickListener);
        }
        getTodoList().setHasFixedSize(true);
        getTodoList().setLayoutManager(new LinearLayoutManager(this));
        getTodoList().setAdapter(tasksAdapter);

        // Listeners
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(cardSwipeCallback);
        itemTouchHelper.attachToRecyclerView(getTodoList());
        getFabButton().setOnClickListener(fabClickListener);

        // Presenter
        getPresenter().refreshSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().refreshSession();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_task;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, tasksAdapter.getTodos());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private TasksAdapter.TodoViewHolder.IOnClickToEditListener onTodoClickToEditListener = new TasksAdapter.TodoViewHolder.IOnClickToEditListener() {
        @Override
        public void onClickToEditListener(Task todo, int position) {
            presenter.onClickTodoItemToEdit(todo);
        }
    };

    private TasksAdapter.TodoViewHolder.IOnLongClickListener onTodoLongClickListener = new TasksAdapter.TodoViewHolder.IOnLongClickListener() {
        @Override
        public void onLongClickListener(Task todo, int position) {
            presenter.onLongClickTodoItem(todo);
        }
    };


    private ItemTouchHelper.SimpleCallback cardSwipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof TasksAdapter.NoTodoViewHolder) return 0;
            return super.getSwipeDirs(recyclerView, viewHolder);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            tasksAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            int position = viewHolder.getAdapterPosition();
            Task todo = tasksAdapter.getTodos().get(position);
            boolean completed = (!todo.isCompleted());
            presenter.updateTodoIsCompleted(todo, completed, position);
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
            TasksAdapter.TodoViewHolder vh = (TasksAdapter.TodoViewHolder) viewHolder;
            if (vh.getAdapterPosition() != -1) {
                Task todo                     = tasksAdapter.getTodos().get(vh.getAdapterPosition());
                RelativeLayout cardBackground = vh.cardBackground;
                Paint backgroundColor         = new Paint();
                int colorCardBackgroundActive = (todo.isCompleted()) ? R.color.noArchive : R.color.colorArchive;
                backgroundColor.setColor(ContextCompat.getColor(getApplicationContext(), colorCardBackgroundActive));
                c.drawRect(cardBackground.getLeft(), cardBackground.getTop(), cardBackground.getRight(), cardBackground.getBottom() - 5, backgroundColor);
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onAddTodoButtonClick();
        }
    };

    @Override
    public void setTodos(ArrayList<Task> todos) {
        tasksAdapter.setTodos(todos);
    }

    @Override
    public void notifyListDataSetChanged() {
        tasksAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyListItemRemoved(int position) {
        tasksAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyListItemInserted(int position) {
        tasksAdapter.notifyItemInserted(position);
    }

    @Override
    public void showItemDialog(final Task todo, final CharSequence items[]) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which] == getString(R.string.edit)) {
                    presenter.onClickTodoItemToEdit(todo);
                }
                if (items[which] == getString(R.string.delete)) {
                    presenter.delete(todo);
                }
            }
        });
        builder.show();
    }

    @Override
    public void showTodoViewToEdit(Task todo) {
        Intent i = new Intent(this, TaskActivity.class);
        i.putExtra(TaskActivity.EXTRA_EDIT_TODO, todo);
        startActivity(i);
    }

    @Override
    public void showTodoView() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
}
