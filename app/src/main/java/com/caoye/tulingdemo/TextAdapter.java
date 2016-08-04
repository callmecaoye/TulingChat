package com.caoye.tulingdemo;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/18/16.
 */
public class TextAdapter extends BaseAdapter{
    private List<ListData> list;
    private LayoutInflater inflater;


    public TextAdapter(List<ListData> list, Context mContext) {
        this.list = list;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getFlag();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        int type = getItemViewType(i);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            switch (type) {
                case ListData.RECEIVE:
                    viewHolder = new ViewHolder();
                    convertView = inflater.inflate(R.layout.left_item, null);
                    viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                    viewHolder.timeView = (TextView) convertView.findViewById(R.id.timeView);
                    break;

                case ListData.SEND:
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.right_item, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                viewHolder.timeView = (TextView) convertView.findViewById(R.id.timeView);
                break;

            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(i).getContent());
        viewHolder.timeView.setText(list.get(i).getTime());

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        TextView timeView;
    }
}
