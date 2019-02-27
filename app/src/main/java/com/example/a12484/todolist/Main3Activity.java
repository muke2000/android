package com.example.a12484.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main3Activity extends AppCompatActivity {
    private String id;
    private SQLiteDatabase db;
    private TodoHelper dbHelper;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private int rank;
    private int finish;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main3_toolbar);
        setSupportActionBar(toolbar);
        editText = (EditText) findViewById(R.id.edit2);
        radioGroup1 = (RadioGroup) findViewById(R.id.rg2);
        radioGroup2 = (RadioGroup) findViewById(R.id.rg3);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        dbHelper = new TodoHelper(this,"Book.db",null,1);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",null,"id = ?",new String[]{id},null,null,null);
        cursor.moveToFirst();
        editText.setText(cursor.getString(cursor.getColumnIndex("todo")));
        if (cursor.getInt(cursor.getColumnIndex("rank")) == 1) radioGroup1.check(R.id.crucial2);
        if (cursor.getInt(cursor.getColumnIndex("rank")) == 2) radioGroup1.check(R.id.important2);
        if (cursor.getInt(cursor.getColumnIndex("rank")) == 3) radioGroup1.check(R.id.ordinary2);
        if (cursor.getInt(cursor.getColumnIndex("finish")) == 1) radioGroup2.check(R.id.finish);
        if (cursor.getInt(cursor.getColumnIndex("finish")) == 0) radioGroup2.check(R.id.not_finish);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main3_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete2){
            db.delete("Book","id = ?",new String[]{id});
            finish();
        }
        if (item.getItemId() == R.id.done2){
            if (radioGroup1.getCheckedRadioButtonId() == R.id.crucial2) rank = 1;
            if (radioGroup1.getCheckedRadioButtonId() == R.id.important2) rank = 2;
            if (radioGroup1.getCheckedRadioButtonId() == R.id.ordinary2) rank = 3;
            if (radioGroup2.getCheckedRadioButtonId() == R.id.finish) finish = 1;
            if (radioGroup2.getCheckedRadioButtonId() == R.id.not_finish) finish = 0;
            String todo = editText.getText().toString();
            ContentValues values = new ContentValues();
            values.put("finish",finish);
            values.put("rank",rank);
            values.put("todo",todo);
            db.update("Book",values,"id = ?",new String[]{id});
            finish();
        }
        return true;
    }
}
