package com.isft194.gestin.json;

import com.google.gson.Gson;
import com.isft194.gestin.models.AcademicRecord;

public class AcademicRecordParsing implements IParsing<AcademicRecord>
{
    private Gson gson;
    public AcademicRecordParsing()
    {
        this.gson=new Gson();
    }
    @Override
    public AcademicRecord jsonToObj(String json)
    {
        return this.gson.fromJson(json, AcademicRecord.class);
    }
    @Override
    public String objToJson(AcademicRecord obj)
    {
        return this.gson.toJson(obj);
    }
}
