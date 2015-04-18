package com.example.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nish on 4/18/15.
 */
public class GalleryAdapter extends ArrayAdapter<GalleryItem>{
    Context context;
    int resource;
    List<GalleryItem> objects;

    public GalleryAdapter(Context context, int resource, List<GalleryItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);

            holder.image = (SquareImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String url = objects.get(position).getUrl();
        Picasso.with(context).load(url).into(holder.image);
        holder.title.setText(objects.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder{
        SquareImageView image;
        TextView title;
    }
}
