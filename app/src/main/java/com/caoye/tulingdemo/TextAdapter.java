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
        inflater.from(mContext);
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        int type = list.get(i).getFlag();
        ReceiveViewHolder rViewHolder = null;
        SendViewHolder sViewHolder = null;

        if (convertView == null) {
            switch (type) {
                case ListData.RECEIVE:
                    convertView = inflater.inflate(R.layout.left_item, null);
                    rViewHolder = new ReceiveViewHolder();
                    rViewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                    rViewHolder.timeView = (TextView) convertView.findViewById(R.id.timeView);
                    convertView.setTag(0, rViewHolder);
                    break;
                case ListData.SEND:
                    convertView = inflater.inflate(R.layout.right_item, null);
                    sViewHolder = new SendViewHolder();
                    sViewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                    sViewHolder.timeView = (TextView) convertView.findViewById(R.id.timeView);
                    convertView.setTag(1, sViewHolder);
                    break;
            }
        } else {
            switch (type) {
                case ListData.RECEIVE:
                    rViewHolder = (ReceiveViewHolder) convertView.getTag(0);
                    break;
                case ListData.SEND:
                    sViewHolder = (SendViewHolder) convertView.getTag(1);
                    break;
            }
        }

        switch (type) {
            case ListData.RECEIVE:
                rViewHolder.textView.setText(list.get(i).getContent());
                rViewHolder.timeView.setText(list.get(i).getTime());
                break;
            case ListData.SEND:
                sViewHolder.textView.setText(list.get(i).getContent());
                sViewHolder.timeView.setText(list.get(i).getTime());
                break;
        }

        return convertView;
    }

    static class ReceiveViewHolder {
        TextView textView;
        TextView timeView;
    }

    static class SendViewHolder {
        TextView textView;
        TextView timeView;
    }
}
