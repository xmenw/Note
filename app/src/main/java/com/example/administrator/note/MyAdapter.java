package com.example.administrator.note;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by Administrator on 2019/5/19
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    public MyAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_note, null);
            holder = new Holder();
            holder.text = convertView.findViewById(R.id.show_text);
            holder.textTime = convertView.findViewById(R.id.show_time);
            convertView.setTag(holder);
        }else {
            holder = (Holder)convertView.getTag();
        }
        cursor.moveToPosition(position);
        String content = cursor.getString(cursor.getColumnIndex("content"));
        String time = cursor.getString(cursor.getColumnIndex("time"));
        holder.text.setText(content);
        holder.textTime.setText(time);
        return convertView;
    }
}
class Holder {
    TextView text;
    TextView textTime;
}