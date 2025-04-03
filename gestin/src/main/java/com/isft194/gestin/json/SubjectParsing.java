package com.isft194.gestin.json;

import com.google.gson.Gson;
import com.isft194.gestin.models.Subject;

public class SubjectParsing implements IParsing<Subject>
{
    private Gson gson;
    public SubjectParsing()
    {
        this.gson=new Gson();
    }
    @Override
    public Subject jsonToObj(String json)
    {
        return this.gson.fromJson(json,Subject.class);
    }
    @Override
    public String objToJson(Subject obj)
    {
        return this.gson.toJson(obj);
    }
}
