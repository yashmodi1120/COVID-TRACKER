package com.example.covidtracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class statecasesadaptor extends ArrayAdapter<statecases>
{
    public statecasesadaptor(Context context,List<statecases> stateCases)
    {
        super(context,0,stateCases);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.caseslistitem,parent,false);
        }
        statecases newstate=getItem(position);

        TextView name=(TextView)listItemView.findViewById(R.id.stateutname);
        TextView confirmed=(TextView)listItemView.findViewById(R.id.conf);
        TextView recovered=(TextView)listItemView.findViewById(R.id.rec);
        TextView death=(TextView)listItemView.findViewById(R.id.dea);
        TextView delconfirmed=(TextView)listItemView.findViewById(R.id.delconf);
        TextView delrecovered=(TextView)listItemView.findViewById(R.id.delrec);
        TextView deldeath=(TextView)listItemView.findViewById(R.id.deldea);
        name.setText(newstate.getname());
        confirmed.setText(newstate.getconf());
        recovered.setText(newstate.getrec());
        death.setText(newstate.getdea());
        delconfirmed.setText(newstate.getdelconf());
        delrecovered.setText(newstate.getdelrec());
        deldeath.setText(newstate.getdeldea());


        return listItemView;
    }

}
