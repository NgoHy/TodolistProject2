package com.example.admin.todolistproject.Tasks;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.todolistproject.Model.Task;
import com.example.admin.todolistproject.R;

import java.util.ArrayList;

/**
 * Created by Admin on 26/12/2016.
 */

public class TasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Task> tasks;
    private Context context;

    private TodoViewHolder.IOnClickToEditListener todoIOnClickToEditListener;
    private TodoViewHolder.IOnLongClickListener todoIOnLongClickListener;

    private static final int TYPE_NO_TODO = 0;
    private static final int TYPE_TODO = 1;

    public TasksAdapter(Context context, ArrayList<Task> tasks, TodoViewHolder.IOnClickToEditListener todoIOnClickToEditListener, TodoViewHolder.IOnLongClickListener todoIOnLongClickListener) {
        this.context = context;
        this.tasks   = tasks;
        this.todoIOnClickToEditListener = todoIOnClickToEditListener;
        this.todoIOnLongClickListener   = todoIOnLongClickListener;
    }

    public void setTodos(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTodos() {
        return tasks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NO_TODO:
                View noTodoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.emptytask, parent, false);
                return new NoTodoViewHolder(noTodoView);
            case TYPE_TODO:
                View todoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notemptytask, parent, false);
                return new TodoViewHolder(todoView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_NO_TODO:
                break;
            case TYPE_TODO:
                final Task task = tasks.get(position);
                TodoViewHolder todoViewHolder = (TodoViewHolder) holder;


                // Data
                todoViewHolder.title.setText(task.getTitle());
                todoViewHolder.description.setText(task.getDescription());

                todoViewHolder.completed.setChecked(task.isCompleted());

                // Listeners
                todoViewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        todoIOnClickToEditListener.onClickToEditListener(task, holder.getAdapterPosition());
                    }
                });
                todoViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        todoIOnLongClickListener.onLongClickListener(task, holder.getAdapterPosition());
                        return true;
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (tasks.size() == 0) ? TYPE_NO_TODO : TYPE_TODO;
    }

    @Override
    public int getItemCount() {
        return (tasks.size() == 0) ? 1 : tasks.size();
    }

    /**
     * To.Do ViewHolder
     */
    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout cardBackground;
        public CardView card;
        public TextView title;
        public TextView description;
        public TextView edited;
        public CheckBox completed;

        public TodoViewHolder(View itemView) {
            super(itemView);
            cardBackground  = (RelativeLayout) itemView.findViewById(R.id.card_background);
            card            = (CardView) itemView.findViewById(R.id.card_todo);
            title           = (TextView) itemView.findViewById(R.id.title);
            description     = (TextView) itemView.findViewById(R.id.description);

            completed       = (CheckBox) itemView.findViewById(R.id.completed);
        }

        /**
         * Interface to ViewHolder onClick
         */
        public interface IOnClickToEditListener {

            void onClickToEditListener(Task task, int position);

        }

        /**
         * Interface to ViewHolder onLongClick
         */
        public interface IOnLongClickListener {

            void onLongClickListener(Task task, int position);

        }
    }

    /**
     * If there are no To.Do's, use this ViewHolder
     */
    public static class NoTodoViewHolder extends RecyclerView.ViewHolder {

        public TextView noTodoText;

        public NoTodoViewHolder(View itemView) {
            super(itemView);
            noTodoText = (TextView) itemView.findViewById(R.id.no_todo_text);
        }
    }
    
}
