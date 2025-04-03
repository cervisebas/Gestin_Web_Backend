package com.isft194.gestin.json;

import com.google.gson.Gson;
import com.isft194.gestin.models.Exam;

public class ExamParsing implements IParsing<Exam>
{
    private Gson gson;
    public ExamParsing()
    {
        this.gson=new Gson();
    }
    @Override
    public Exam jsonToObj(String json)
    {
        return this.gson.fromJson(json,Exam.class);
    }
    @Override
    public String objToJson(Exam obj)
    {
        return this.gson.toJson(obj);
    }
}
