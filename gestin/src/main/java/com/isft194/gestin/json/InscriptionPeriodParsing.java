package com.isft194.gestin.json;

import com.google.gson.Gson;
import com.isft194.gestin.models.InscriptionPeriod;

public class InscriptionPeriodParsing implements IParsing<InscriptionPeriod>
{
    private Gson gson;
    public InscriptionPeriodParsing()
    {
        this.gson=new Gson();
    }
    @Override
    public InscriptionPeriod jsonToObj(String json)
    {
        return this.gson.fromJson(json,InscriptionPeriod.class);
    }
    @Override
    public String objToJson(InscriptionPeriod obj)
    {
        return this.gson.toJson(obj);
    }
}
