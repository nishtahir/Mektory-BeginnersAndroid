package com.example.taskmanager;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nish on 3/7/15.
 */
public class CustomAdapter extends ArrayAdapter<TaskItem> {
    private Context context;
    private int resource;
    private List<TaskItem> objects;
    private TaskItem item;

    //This is a layout inflater. Its job is to take and XML file and turn it into a
    //layout you can interact with
    private LayoutInflater mInflater;
    private TextView titleTextView;
    private TextView descTextView;
    private ImageView thumbnailImageView;

    public CustomAdapter(Context context, int resource, List<TaskItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        //This is how to get the layout from context
        //Remember that Activity's are context
        mInflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * getView is called when the listview is ready to inflate the layout associated with
     * a specific list item
     *
     * It is called once for each item in the listView
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(resource, null);
        titleTextView = (TextView) convertView.findViewById(R.id.title);
        descTextView = (TextView) convertView.findViewById(R.id.description);
        thumbnailImageView = (ImageView) convertView.findViewById(R.id.thumbnail);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.task_completed);

        item = objects.get(position);
        titleTextView.setText(item.getTitle());
        descTextView.setText(item.getDescription());
        thumbnailImageView.setBackgroundColor(item.getColor());
        checkBox.setChecked(item.isTaskComplete());

        if(item.isTaskComplete()){
            titleTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            descTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            titleTextView.setPaintFlags(titleTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            descTextView.setPaintFlags(descTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setTaskComplete(isChecked);
                item.save();
                notifyDataSetChanged();

            }
        });
        return convertView;
    }
}
