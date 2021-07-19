package com.example.covidtracker;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;

public class statecases
{
    private String mname;
    private String mconf;
    private String mrec;
    private String mdea;
    private String mdelconf;
    private String mdelrec;
    private String mdeldea;

    public statecases(String name,String conf,String rec,String dea,String delcon,String deldea,String delrec)
    {
        mname=name;
        mconf=conf;
        mrec=rec;
        mdea=dea;
        mdelconf=delcon;
        mdeldea=deldea;
        mdelrec=delrec;
    }


    public String getname(){return mname;}
    public String getconf(){return mconf;}
    public String getrec(){return mrec;}
    public String getdea(){return mdea;}
    public String getdelconf(){return mdelconf;}
    public String getdeldea(){return mdeldea;}
    public String getdelrec(){return mdelrec;}
}
