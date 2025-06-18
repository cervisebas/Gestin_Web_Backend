package com.isft194.gestin.interfaces;

public interface IMapper<Model, Request, Response> {
    public Model fromRequestToModel(Request request);
    public Response fromModelToResponse(Model model);
}
