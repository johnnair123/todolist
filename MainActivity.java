package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private EditText editTextTask;
    private ListView listViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        listViewTasks = findViewById(R.id.listViewTasks);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTask.getText().toString().trim();
                if (!task.isEmpty()) {
                    taskList.add(task);
                    adapter.notifyDataSetChanged();
                    editTextTask.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView taskTextView = (TextView) view;
                if (taskTextView.getPaintFlags() == Paint.STRIKE_THRU_TEXT_FLAG) {
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                } else {
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                taskList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task removed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}