package com.isft194.gestin.mappers;

public interface IMapper<OBJ,RQT,RES>
{
    public OBJ fromRequestToObj(RQT request) throws Exception;
    public RES fromObjToResponse(OBJ obj);
}
