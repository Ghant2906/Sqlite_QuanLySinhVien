package com.example.quanlysinhvienwithsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SVAdapter extends BaseAdapter {
    Context context_335;
    List<SinhVien> listSV_335;

    public SVAdapter(Context context, List<SinhVien> listSV) {
        this.context_335 = context;
        this.listSV_335 = listSV;
    }

    @Override
    public int getCount() {
        return listSV_335.size();
    }

    @Override
    public Object getItem(int position) {
        return listSV_335.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context_335).inflate(R.layout.layout_sv_item, null);
        }

        TextView textViewID = convertView.findViewById(R.id.textViewIDSV);
        TextView textViewName = convertView.findViewById(R.id.textViewNameSV);

        SinhVien sv = listSV_335.get(position);
        textViewID.setText("" + sv.getIdSV());
        textViewName.setText((sv.getNameSV()));

        return convertView;
    }
}
