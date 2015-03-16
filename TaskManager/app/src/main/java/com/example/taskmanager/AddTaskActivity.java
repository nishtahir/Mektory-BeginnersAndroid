package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Nish on 2/28/15.
 */
public class AddTaskActivity extends ActionBarActivity {
    private static final String TAG = AddTaskActivity.class.getSimpleName();
    // Add id to finish button
    // Reference button here
    // set Click listener
    EditText titleEditText;
    EditText descEditText;

    String title;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Get the intent that was used to start the activity
        Intent passedIntent = getIntent();

        // Find the views that we added to our XML files
        titleEditText = (EditText) findViewById(R.id.title_edit);
        descEditText = (EditText) findViewById(R.id.desc_edit);

        // If the intent has an Extra (A primitive or String that was passed along with it), then we
        // only want to view the task item.
        if (passedIntent.hasExtra("title")) {
            Log.d(TAG, "I've got a title extra");
            title = passedIntent.getStringExtra("title");
            description = passedIntent.getStringExtra("description");

            // to prevent null pointer exceptions
            if (title != null)
                titleEditText.setText(title);

            if (description != null)
                descEditText.setText(description);

            // disable the edit text field so that we can't change the contents
            titleEditText.setEnabled(false);
            descEditText.setEnabled(false);
        }
    }

    public void finishAddingTask(View v) {
        // Create a new intent to send back to ViewTaskActivity
        Intent answer = new Intent();
        //Add an extra to the intent
        // Extras are like little messages we bundle with intents
        // They are key value pairs; answer.putExtra(Key, Value);
        answer.putExtra("title", titleEditText.getText().toString());
        answer.putExtra("description", descEditText.getText().toString());
        //set the result of the activity with a result code and the intent
        setResult(RESULT_OK, answer);
        //End the activity
        finish();
    }
}
