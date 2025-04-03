package com.isft194.gestin.json;

public interface IParsing<T>
{
    public T jsonToObj(String json);
    public String objToJson(T obj);
}
