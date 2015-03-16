package com.example.taskmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * This activity will be used to keep and display our list of tasks
 * It's also where our app will start from when launched
 */
public class ViewTasksActivity extends ActionBarActivity {

    // This is a list of taskItem objects to display
    List<TaskItem> myTaskList;

    // This is our custom list adapter. It's what we will use to
    // bind our custom list view to our data
    CustomAdapter adapter;

    // In the activity lifecycle on create is the first method called when the
    // activity starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This sets the activity's layout to what we defined in the activity_view_tasks.xml file
        setContentView(R.layout.activity_view_tasks);

        //Here we initialize our ArrayList
        myTaskList = new ArrayList<>();

        //We are creating task items for testing purposes
        myTaskList.add(new TaskItem("Get Milk",
                "Go to store get fresh milk. Pay for it",
                Color.GREEN));
        myTaskList.add(new TaskItem("Raise the roof",
                "Literally raise the roof, it's too low",
                Color.BLACK));

        //We have an image button, which we used for our floating action button
        // Material design is so awesome
        ImageButton addTaskButton = (ImageButton) findViewById(R.id.btn_add_task);

        ListView listView = (ListView) findViewById(R.id.my_list);

        //We are now initializing our custom adapter
        adapter = new CustomAdapter(this, R.layout.custom_list_item, myTaskList);

        //Set the listview adapter to our custom adapter.
        listView.setAdapter(adapter);

        //The contents of this method will be executed when ever a listview item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ViewTasksActivity.this, AddTaskActivity.class);
                intent.putExtra("title", myTaskList.get(position).getTitle());
                intent.putExtra("description", myTaskList.get(position).getDescription());
                //Start the add task activity with the intent
                startActivity(intent);
            }
        });

        //This is an onclick listener. The method will be invoked when ever the button is clicked
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
            //A Toast is a transient and non intrusive way of informing the user
            // That something has happened
            Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_refresh) {
            Toast.makeText(this, "You clicked refresh", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * When an activity is started with startActivityForResult, this method is called immediately after
     * the activity is finished.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check the request code to make sure it's the one we sent it
        if (requestCode == ADD_TASK_REQUEST) {
            // Check the result code to see if our action was completed
            if (resultCode == RESULT_OK) {

                // get the extras from the intent that was sent with it
                String title = data.getStringExtra("title");
                String desc = data.getStringExtra("description");
                // Create a new task item, using the extras
                TaskItem newItem = new TaskItem(title, desc, Color.MAGENTA);
                //Add the new task item to our list of tasks
                myTaskList.add(newItem);
            }
        }
        //Tell the adapter that the list of items have changed.
        //This will cause it to updated the listview
        adapter.notifyDataSetChanged();
    }
}
