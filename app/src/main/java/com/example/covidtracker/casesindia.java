package com.example.covidtracker;

import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class casesindia extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casesinindia);


        String url="https://api.covid19india.org/data.json";

        TextView totactive = findViewById(R.id.totact);
        TextView totrecov= findViewById(R.id.totrec);
        TextView totdeath = findViewById(R.id.totdea);
        TextView totconfirmed=findViewById(R.id.totcon);
        TextView totldact=findViewById(R.id.ldact);
        TextView totldrec=findViewById(R.id.ldrec);
        TextView totldconf=findViewById(R.id.ldconf);
        TextView totlddea=findViewById(R.id.lddea);
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);

        ArrayList<statecases> cases=new ArrayList<>();
        JsonObjectRequest jsonObject=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("statewise");
                        JSONObject tot=jsonArray.getJSONObject(0);
                        String active=tot.getString("active");
                        String confirm=tot.getString("confirmed");
                        String death=tot.getString("deaths");
                        String recovered=tot.getString("recovered");
                        JSONArray jsonArray1=response.getJSONArray("cases_time_series");
                        JSONObject tot1=jsonArray1.getJSONObject(jsonArray1.length()-1);
                        totlddea.append(tot1.getString("dailydeceased"));
                        totldrec.append(tot1.getString("dailyrecovered"));
                        totldconf.append(tot1.getString("dailyconfirmed"));
                        totactive.append(active);
                        totrecov.append(recovered);
                        totdeath.append(death);
                        totconfirmed.append(confirm);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject obj=jsonArray.getJSONObject(i);
                            String nam=obj.getString("state");
                            String confir=obj.getString("confirmed");
                            String deat=obj.getString("deaths");
                            String recovere=obj.getString("recovered");
                            String delconf=obj.getString("deltaconfirmed");
                            String deldeat=obj.getString("deltadeaths");
                            String delrec=obj.getString("deltarecovered");
                            cases.add(new statecases(nam,confir,recovere,deat,delconf,deldeat,delrec));
                        }
                    ListView statecase=(ListView)findViewById(R.id.list);
                        statecasesadaptor adapter = new statecasesadaptor(getBaseContext(),cases);
                        statecase.setAdapter((ListAdapter)adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObject);
    }
}
