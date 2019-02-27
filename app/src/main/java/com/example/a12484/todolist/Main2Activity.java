package com.example.a12484.todolist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private int rank;
    private EditText editText;
    private RadioGroup radioGroup;
    private TodoHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main2_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            finish();
            return true;
        }
        if (item.getItemId() == R.id.done){
            radioGroup = (RadioGroup) findViewById(R.id.rg);
            editText = (EditText) findViewById(R.id.edit);
            String todo = editText.getText().toString();
            if (radioGroup.getCheckedRadioButtonId() == R.id.crucial) rank = 1;
            if (radioGroup.getCheckedRadioButtonId() == R.id.important) rank = 2;
            if (radioGroup.getCheckedRadioButtonId() == R.id.ordinary) rank = 3;
            dbHelper = new TodoHelper(this,"Book.db",null,1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("finish",0);
            values.put("rank",rank);
            values.put("todo",todo);
            db.insert("Book",null,values);
            values.clear();
            finish();
        }
        return true;
    }
}
