package com.example.vrs.spinnerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jeevan on 704552016.
 */
public class static_busno extends AppCompatActivity {
    public final static String KEY4 = "com.sliet.jeevansingh.projectjeevankeyone4";
    public final static String KEY3 = "com.sliet.jeevansingh.projectjeevankeyone3";
    public String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);
        ListView listview=(ListView)findViewById(R.id.listv);
        Intent intent= getIntent();
        String jas = intent.getStringExtra(KEY3);
       // Toast.makeText(this,jas,Toast.LENGTH_SHORT).show();
        String[] a=jas.split("__");
        //double x = Double.parseDouble(a);


        // Inflate header view
      //  ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.header, listview,false);
        // Add header view to the ListView
      //  listview.addHeaderView(headerView);
        // Get the string array defined in strings.xml file
        if (a[0].equals("8098")) {
            items = getResources().getStringArray(R.array.firstbus);

        }else if(a[0].equals("8040"))
        {
            items = getResources().getStringArray(R.array.secondbus);

        }else if(a[0].equals("1")){
            items = getResources().getStringArray(R.array.firststation);
        }else if(a[0].equals("2")){
            items = getResources().getStringArray(R.array.secondstation);
        }else{
            Toast.makeText(this,"its not working",Toast.LENGTH_SHORT).show();
        }

        // Create an adapter to bind data to the ListView

        LstViewAdapter adapter=new LstViewAdapter(this,R.layout.rowlayout,R.id.txtname,items);
        // Bind data to the ListView
        listview.setAdapter(adapter);


    }
    public void myClick(View v){

        //  Toast.makeText(this,"I phone 6132121321",Toast.LENGTH_SHORT).show();

        //get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout)v.getParent();
        TableRow child0 = (TableRow) vwParentRow.getChildAt(0);
        TextView tv = (TextView) child0.getChildAt(2);


        String label = tv.getText().toString();

        //Toast.makeText(this,label,Toast.LENGTH_SHORT).show();
        if( label.equals("1")){

            //  Toast.makeText(this,"I phone 6",Toast.LENGTH_SHORT).show();
            extrainto();

        }
        else if( label.equals("2")){
            //  Toast.makeText(this,"iPad Air",Toast.LENGTH_SHORT).show();
            extraintoone();
        }
        else{

        }

        //Button btnChild = (Button)vwParentRow.getChildAt(2);

        //btnChild.setText(child.getText());
        //btnChild.setText("I've been clicked!");

    }

    private void extraintoone() {
        String CurrentString = "b";
        Intent intent = new Intent(static_busno.this, staticbusno.class);
       // intent.putExtra(KEY4,CurrentString);
      //  startActivity(intent);
    }

    public void extrainto() {
        String CurrentString = "a";
        Intent intent = new Intent(static_busno.this, staticbusno.class);
       // intent.putExtra(KEY4,CurrentString);
       // startActivity(intent);
    }

}

