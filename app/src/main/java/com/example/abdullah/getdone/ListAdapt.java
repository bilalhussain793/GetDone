package com.example.abdullah.getdone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapt extends BaseAdapter {
    ArrayList<String> desc1;
    ArrayList<String> price1;
    ArrayList<String> offer1;
    ArrayList<String> loc1;

    Context context;
    int clr;
    private static LayoutInflater inflater=null;

    public ListAdapt(Activity home2, ArrayList<String> desc, ArrayList<String> price, ArrayList<String> offer, ArrayList<String> loc) {
        // TODO Auto-generated constructor stub
        desc1=desc;
        price1=price;
        offer1=offer;
        loc1=loc;
        context=home2;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return desc1.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView os_desc;
        TextView price;
        TextView offers;
        TextView location;
        TextView img;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        final View rowView;

        rowView = inflater.inflate(R.layout.listview_layout, null);
        holder.os_desc =(TextView) rowView.findViewById(R.id.gig_tv);
        holder.price =(TextView) rowView.findViewById(R.id.prc);
        holder.offers =(TextView) rowView.findViewById(R.id.ofr);
        holder.location =(TextView) rowView.findViewById(R.id.adrs);
        //holder.img =(TextView) rowView.findViewById(R.id.gig_tv);
        //holder.os_date =(TextView) rowView.findViewById(R.id.tvd);

        // holder.os_text.setBackgroundColor(clr);

        holder.os_desc.setText(desc1.get(position));
        holder.price.setText(price1.get(position));
        holder.offers.setText(offer1.get(position));
        holder.location.setText(loc1.get(position));

        rowView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+desc1.get(position), Toast.LENGTH_SHORT).show();
                //rowView.setBackgroundColor(R.color.black);
            }
        });

        return rowView;
    }


}
