package com.isft194.gestin.json;

import com.google.gson.Gson;
import com.isft194.gestin.models.EnrolmentExam;

public class EnrolmentExamParsing implements IParsing<EnrolmentExam>
{
    private Gson gson;
    public EnrolmentExamParsing()
    {
        this.gson=new Gson();
    }
    @Override
    public EnrolmentExam jsonToObj(String json)
    {
        return this.gson.fromJson(json,EnrolmentExam.class);
    }
    @Override
    public String objToJson(EnrolmentExam obj)
    {
        return this.gson.toJson(obj);
    }
}
