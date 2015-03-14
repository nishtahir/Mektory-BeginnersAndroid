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

    // Add id to finish button
    // Reference button here
    // set Click listener
    EditText titleEditText;
    EditText descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        titleEditText = (EditText) findViewById(R.id.title_edit);
        descEditText = (EditText) findViewById(R.id.desc_edit);
    }

    public void finishAddingTask(View v){
        Intent answer = new Intent();
        answer.putExtra("title", titleEditText.getText().toString());
        answer.putExtra("description", descEditText.getText().toString());
        setResult(RESULT_OK, answer);
        Log.d("AddTaskActivity", "Set result and things are ok");
        finish();
    }
}
