package com.example.a12484.todolist;

import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private List<TodoItem> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View todoView;
        TextView todoText;

        public ViewHolder(View view){
            super(view);
            todoView = view;
            todoText = (TextView) view.findViewById(R.id.item_view);
        }
    }

    public TodoAdapter(List<TodoItem> todoItemList){
        mList = todoItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todo_view,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.todoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                TodoItem todoItem = mList.get(position);
                Intent intent = new Intent(view.getContext(),Main3Activity.class);
                intent.putExtra("id",String.valueOf(todoItem.getId()));
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TodoItem todoItem = mList.get(i);
        if (todoItem.getFinish() == 1) viewHolder.todoText.setBackgroundColor(0xffffffff);
        if (todoItem.getFinish() == 0) viewHolder.todoText.setBackgroundColor(0xff9aff9a);
        viewHolder.todoText.setText(todoItem.getTodo());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
