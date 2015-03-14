package com.example.taskmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ViewTasksActivity extends ActionBarActivity {

    List<TaskItem> myTaskList;
    CustomAdapter adapter;
    //Add id to listview
    //Reference it in Java code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        myTaskList = new ArrayList<>();
        myTaskList.add(new TaskItem("Get Milk",
                "Go to store get fresh milk. Pay for it",
                Color.GREEN));
        myTaskList.add(new TaskItem("Raise the roof",
                "Literally raise the roof, it's too low",
                Color.BLACK));
        // reference
        Button addTaskButton = (Button) findViewById(R.id.btn_add_task);

        ListView listView = (ListView) findViewById(R.id.my_list);
        adapter = new CustomAdapter(this, R.layout.custom_list_item, myTaskList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewTasksActivity.this, "You clicked item" + position, Toast.LENGTH_SHORT).show();
            }
        });

        addTaskButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewTasksActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });
    }

    public static final int ADD_TASK_REQUEST = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_refresh) {
            Toast.makeText(this, "You clicked refresh", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_TASK_REQUEST){
            if(resultCode == RESULT_OK) {
                String title = data.getStringExtra("title");
                String desc = data.getStringExtra("description");

                TaskItem newItem = new TaskItem(title, desc, Color.MAGENTA);
                myTaskList.add(newItem);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
