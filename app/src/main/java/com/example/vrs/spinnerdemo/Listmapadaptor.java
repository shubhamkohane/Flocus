package com.example.vrs.spinnerdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeevan on 7/29/2016.
 */
public class Listmapadaptor extends ArrayAdapter<String> {
    int groupid;
    String[] item_list;
    ArrayList<String> desc;
    Context context;
    public Listmapadaptor(Context context, int vg, int id, String[] item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;

    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView textnameone;
        public TextView textpriceone;
        public TextView textpriceonetwo;
        public TextView tpasstwo;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the rowlayout.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textnameone= (TextView) rowView.findViewById(R.id.txtbusno);
            viewHolder.textpriceone= (TextView) rowView.findViewById(R.id.txtbustime);

            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item

        String[] items=item_list[position].split("__");
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textnameone.setText(items[0]);
        holder.textpriceone.setText(items[1]);

        return rowView;
    }



}
