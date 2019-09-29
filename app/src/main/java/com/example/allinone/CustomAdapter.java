package com.example.allinone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends BaseAdapter {

    Context context;
    String [] sensorNames;
    int[] sensorPics;
    private LayoutInflater inflater;

    CustomAdapter(Context context,String[] sensorNames, int[] sensorPics){
        this.context = context;
        this.sensorNames = sensorNames;
        this.sensorPics = sensorPics;
    }

    @Override
    public int getCount() {
        return sensorNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_layout,parent,false);

        }
        ImageView imageView = convertView.findViewById(R.id.imageViewId);
        TextView textView = convertView.findViewById(R.id.textViewId);

        imageView.setImageResource(sensorPics[position]);
        textView.setText(sensorNames[position]);

        return convertView;
    }
}