package com.example.a12484.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TodoItem> itemList = new ArrayList<>();
    final TodoAdapter adapter = new TodoAdapter(itemList);
    private TodoHelper dbHelper;
    private SQLiteDatabase db;
    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.crucial_bnv:{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("Book",null,null,null,null,null,null);
                    itemList.clear();
                    if(cursor.moveToFirst()){
                        do{
                            String todo = cursor.getString(cursor.getColumnIndex("todo"));
                            int rank = cursor.getInt(cursor.getColumnIndex("rank"));
                            int id = cursor.getInt(cursor.getColumnIndex("id"));
                            int finish = cursor.getInt(cursor.getColumnIndex("finish"));
                            if (rank == 1) itemList.add(new TodoItem(id,rank,todo,finish));
                        }while (cursor.moveToNext());
                    }
                    adapter.notifyDataSetChanged();
                    return true;
                }
                case R.id.important_bnv:{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("Book",null,null,null,null,null,null);
                    itemList.clear();
                    if(cursor.moveToFirst()){
                        do{
                            String todo = cursor.getString(cursor.getColumnIndex("todo"));
                            int rank = cursor.getInt(cursor.getColumnIndex("rank"));
                            int id = cursor.getInt(cursor.getColumnIndex("id"));
                            int finish = cursor.getInt(cursor.getColumnIndex("finish"));
                            if (rank == 2) itemList.add(new TodoItem(id,rank,todo,finish));
                        }while (cursor.moveToNext());
                    }
                    adapter.notifyDataSetChanged();
                    return true;
                }
                case R.id.ordinary_bnv:{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("Book",null,null,null,null,null,null);
                    itemList.clear();
                    if(cursor.moveToFirst()){
                        do{
                            String todo = cursor.getString(cursor.getColumnIndex("todo"));
                            int rank = cursor.getInt(cursor.getColumnIndex("rank"));
                            int id = cursor.getInt(cursor.getColumnIndex("id"));
                            int finish = cursor.getInt(cursor.getColumnIndex("finish"));
                            if (rank == 3) itemList.add(new TodoItem(id,rank,todo,finish));
                        }while (cursor.moveToNext());
                    }
                    adapter.notifyDataSetChanged();
                    return true;
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        dbHelper = new TodoHelper(this,"Book.db",null,1);
        db = dbHelper.getWritableDatabase();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.crucial_bnv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        bottomNavigationView.setSelectedItemId(bottomNavigationView.getSelectedItemId());
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}