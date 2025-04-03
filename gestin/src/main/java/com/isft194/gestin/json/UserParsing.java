package com.isft194.gestin.json;

import com.google.gson.Gson;
import com.isft194.gestin.models.User;

public class UserParsing implements IParsing<User>
{
    private Gson gson;
    public UserParsing()
    {
        this.gson=new Gson();
    }

    @Override
    public User jsonToObj(String json)
    {
        return this.gson.fromJson(json,User.class);
    }
    @Override
    public String objToJson(User obj)
    {
        return this.gson.toJson(obj);
    }
}
