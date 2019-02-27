package com.example.a12484.todolist;

public class TodoItem {
    private int finish;
    private int id;
    private int rank;
    private String todo;

    public TodoItem(int id,int rank,String todo,int finish){
        this.id = id;
        this.rank = rank;
        this.todo = todo;
        this.finish = finish;
    }

    public int getFinish() {
        return finish;
    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public String getTodo() {
        return todo;
    }
}
