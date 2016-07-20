package com.caoye.tulingdemo;

import android.content.Context;
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
    private Context mContext;
    private RelativeLayout layout;

    public TextAdapter(List<ListData> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (list.get(i).getFlag() == ListData.RECEIVE) {
            layout = (RelativeLayout) inflater.inflate(R.layout.left_item, null);
        } else if (list.get(i).getFlag() == ListData.SEND) {
            layout = (RelativeLayout) inflater.inflate(R.layout.right_item, null);
        }
        TextView textView = (TextView) layout.findViewById(R.id.textView);
        textView.setText(list.get(i).getContent());
        TextView timeView = (TextView) layout.findViewById(R.id.timeView);
        timeView.setText(list.get(i).getTime());

        return layout;
    }
}
