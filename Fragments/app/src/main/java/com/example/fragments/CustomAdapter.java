package com.example.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Darja Kasnikova on 05.05.2015.
 */
public class CustomAdapter extends ArrayAdapter<ListItem> {

    private Context context;
    private int resource;
    private List<ListItem> objects;

    // This is layout inflater. Its jog is to take XML file
    // and turn it into a layout you can interact with.
    private LayoutInflater mInflater;
    private ListItem item;

    private ViewHolder holder;

    private static final String TAG = "CustomAdapter";

    public CustomAdapter(Context context, int resource, List<ListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        // Get the layout from context
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // GetView is called once for each item in ListView
    // and when ListView is ready to inflate the layout
    // associated with specific list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            holder = new ViewHolder();

            convertView = mInflater.inflate(resource, null);

            holder.titletextView = (TextView) convertView.findViewById(R.id.title);
            holder.descriptiontextView = (TextView) convertView.findViewById(R.id.description);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        item = objects.get(position);
        holder.titletextView.setText(item.getTitle());
        holder.descriptiontextView.setText(item.getDescription());

        //return super.getView(position, convertView, parent);
        return convertView;
    }

    private static class ViewHolder{
        TextView titletextView;
        TextView descriptiontextView;
    }
}
