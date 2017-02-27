package com.example.vrs.spinnerdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeevan on 7/20/2016.
 */
public class LstViewAdapterone extends ArrayAdapter<String> {
    int groupid;
    String[] item_list;
    ArrayList<String> desc;
    Context context;
    int k1, z,d;
    public final static String KEY20 = "com.sliet.jeevansingh.projectjeevankeytwo0";
    public LstViewAdapterone(Context context, int vg, int id, String[] item_list, int p, int q){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;
        k1=p;
        d=q;
        //  Toast.makeText(getContext(),"value of p "+p,Toast.LENGTH_SHORT).show();


    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView textnameone;
        public TextView textpriceone;
        public TextView p;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the rowlayout.xml file if convertView is null
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textnameone = (TextView) rowView.findViewById(R.id.txtnameone);
            viewHolder.textpriceone = (TextView) rowView.findViewById(R.id.txtpriceone);

            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item

        String[] items = item_list[position].split("__");
        // Toast.makeText(getContext(),"value of k "+k1,Toast.LENGTH_SHORT).show();
        {if(d==1) {
            if (k1 == 1) {
                z = Integer.parseInt(items[1]) - 0;
            } else if (k1 == 2) {
                z = Integer.parseInt(items[1]) - 1;
            } else if (k1 == 3) {
                z = Integer.parseInt(items[1]) - 1;
            } else if (k1 == 4) {
                z = Integer.parseInt(items[1]) - 2;
            } else if (k1 == 5) {
                z = Integer.parseInt(items[1]) - 3;
            } else if (k1 == 6) {
                z = Integer.parseInt(items[1]) - 3;
            } else if (k1 == 7) {
                z = Integer.parseInt(items[1]) - 4;
            } else if (k1 == 8) {
                z = Integer.parseInt(items[1]) - 4;
            } else if (k1 == 9) {
                z = Integer.parseInt(items[1]) - 6;
            } else if (k1 == 10) {
                z = Integer.parseInt(items[1]) - 7;
            } else if (k1 == 11) {
                z = Integer.parseInt(items[1]) - 8;
            } else if (k1 == 12) {
                z = Integer.parseInt(items[1]) - 8;
            } else if (k1 == 13) {
                z = Integer.parseInt(items[1]) - 9;
            } else if (k1 == 14) {
                z = Integer.parseInt(items[1]) - 10;
            } else if (k1 == 15) {
                z = Integer.parseInt(items[1]) - 12;
            } else if (k1 == 16) {
                z = Integer.parseInt(items[1]) - 13;
            } else if (k1 == 17) {
                z = Integer.parseInt(items[1]) - 14;
            } else if (k1 == 18) {
                z = Integer.parseInt(items[1]) - 15;
            } else if (k1 == 19) {
                z = Integer.parseInt(items[1]) - 16;
            } else if (k1 == 20) {
                z = Integer.parseInt(items[1]) - 16;
            } else if (k1 == 21) {
                z = Integer.parseInt(items[1]) - 17;
            } else if (k1 == 22) {
                z = Integer.parseInt(items[1]) - 18;
            } else if (k1 == 23) {
                z = Integer.parseInt(items[1]) - 18;
            } else if (k1 == 24) {
                z = Integer.parseInt(items[1]) - 19;
            } else if (k1 == 25) {
                z = Integer.parseInt(items[1]) - 20;
            } else if (k1 == 26) {
                z = Integer.parseInt(items[1]) - 20;
            } else if (k1 == 27) {
                z = Integer.parseInt(items[1]) - 21;
            } else if (k1 == 28) {
                z = Integer.parseInt(items[1]) - 22;
            } else if (k1 == 29) {
                z = Integer.parseInt(items[1]) - 23;
            } else if (k1 == 30) {
                z = Integer.parseInt(items[1]) - 24;
            } else if (k1 == 31) {
                z = Integer.parseInt(items[1]) - 25;
            } else if (k1 == 32) {
                z = Integer.parseInt(items[1]) - 27;
            } else if (k1 == 33) {
                z = Integer.parseInt(items[1]) - 28;
            }
        }else{
            if (k1 == 1) {
                z = Integer.parseInt(items[1]) - 0;
            } else if (k1 == 2) {
                z = Integer.parseInt(items[1]) - 1;
            } else if (k1 == 3) {
                z = Integer.parseInt(items[1]) - 3;
            } else if (k1 == 4) {
                z = Integer.parseInt(items[1]) - 4;
            } else if (k1 == 5) {
                z = Integer.parseInt(items[1]) - 5;
            } else if (k1 == 6) {
                z = Integer.parseInt(items[1]) - 6;
            } else if (k1 == 7) {
                z = Integer.parseInt(items[1]) - 7;
            } else if (k1 == 8) {
                z = Integer.parseInt(items[1]) - 8;
            } else if (k1 == 9) {
                z = Integer.parseInt(items[1]) - 8;
            } else if (k1 == 10) {
                z = Integer.parseInt(items[1]) - 9;
            } else if (k1 == 11) {
                z = Integer.parseInt(items[1]) - 10;
            } else if (k1 == 12) {
                z = Integer.parseInt(items[1]) - 10;
            } else if (k1 == 13) {
                z = Integer.parseInt(items[1]) - 11;
            } else if (k1 == 14) {
                z = Integer.parseInt(items[1]) - 12;
            } else if (k1 == 15) {
                z = Integer.parseInt(items[1]) - 12;
            } else if (k1 == 16) {
                z = Integer.parseInt(items[1]) - 13;
            } else if (k1 == 17) {
                z = Integer.parseInt(items[1]) - 14;
            } else if (k1 == 18) {
                z = Integer.parseInt(items[1]) - 15;
            } else if (k1 == 19) {
                z = Integer.parseInt(items[1]) - 16;
            } else if (k1 == 20) {
                z = Integer.parseInt(items[1]) - 18;
            } else if (k1 == 21) {
                z = Integer.parseInt(items[1]) - 19;
            } else if (k1 == 22) {
                z = Integer.parseInt(items[1]) - 20;
            } else if (k1 == 23) {
                z = Integer.parseInt(items[1]) - 20;
            } else if (k1 == 24) {
                z = Integer.parseInt(items[1]) - 21;
            } else if (k1 == 25) {
                z = Integer.parseInt(items[1]) - 22;
            } else if (k1 == 26) {
                z = Integer.parseInt(items[1]) - 24;
            } else if (k1 == 27) {
                z = Integer.parseInt(items[1]) - 24;
            } else if (k1 == 28) {
                z = Integer.parseInt(items[1]) - 25;
            } else if (k1 == 29) {
                z = Integer.parseInt(items[1]) - 25;
            } else if (k1 == 30) {
                z = Integer.parseInt(items[1]) - 26;
            } else if (k1 == 31) {
                z = Integer.parseInt(items[1]) - 27;
            } else if (k1 == 32) {
                z = Integer.parseInt(items[1]) - 27;
            } else if (k1 == 33) {
                z = Integer.parseInt(items[1]) - 28;
            }
        }
            // int z=Integer.parseInt(items[1])-k1;
            ViewHolder holder = (ViewHolder) rowView.getTag();
            if (z==0) {
                //  holder.textpriceone.setBackgroundColor(Color.YELLOW);
                holder.textnameone.setText(items[0]);
                holder.textpriceone.setText((String.valueOf(z)+":00 min"));
                holder.textpriceone.setTextColor(Color.YELLOW);

            }else if (z>0) {
                //  holder.textpriceone.setBackgroundColor(Color.GREEN);
                holder.textnameone.setText(items[0]);
                holder.textpriceone.setText((String.valueOf(z)+":00 min"));
                holder.textpriceone.setTextColor(Color.GREEN);

            }else {
                //  holder.textpriceone.setBackgroundColor(Color.rgb());
                holder.textnameone.setText(items[0]);
                holder.textpriceone.setText((String.valueOf(z) + ":00 min"));
                holder.textpriceone.setTextColor(Color.RED);

            }
            return rowView;
        }

    }
}
