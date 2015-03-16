package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    //This is a layout inflater. Its job is to take and XML file and turn it into a
    //layout you can interact with
    private LayoutInflater mInflater;

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
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
        TextView descTextView = (TextView) convertView.findViewById(R.id.description);
        ImageView thumbnailImageView = (ImageView) convertView.findViewById(R.id.thumbnail);

        TaskItem item = objects.get(position);
        titleTextView.setText(item.getTitle());
        descTextView.setText(item.getDescription());
        thumbnailImageView.setBackgroundColor(item.getColor());

        return convertView;
    }
}
