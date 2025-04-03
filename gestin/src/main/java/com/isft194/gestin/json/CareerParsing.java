package com.isft194.gestin.json;

import com.google.gson.Gson;
import com.isft194.gestin.models.Career;

public class CareerParsing implements IParsing<Career>
{
    private Gson gson;
    public CareerParsing()
    {
        this.gson=new Gson();
    }
    @Override
    public Career jsonToObj(String json)
    {
        return this.gson.fromJson(json, Career.class);
    }
    @Override
    public String objToJson(Career obj)
    {
        return this.gson.toJson(obj);
    }
}
