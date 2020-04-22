package com.example.audio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FeeListAdapter extends ArrayAdapter<FeePojo> {

    Context context;
    int resource;

    public FeeListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);

        FeePojo feePojo = getItem(position);

        TextView fee = convertView.findViewById(R.id.fee_mny);
        TextView time = convertView.findViewById(R.id.fee_time);
        TextView remark = convertView.findViewById(R.id.fee_remark);




        fee.setText(feePojo.getFee());
        remark.setText(feePojo.getRemark());
        time.setText(feePojo.getTime());



        return convertView;
    }
}
