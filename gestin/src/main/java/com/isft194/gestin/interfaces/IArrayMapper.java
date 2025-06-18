package com.isft194.gestin.interfaces;

import java.util.List;

public interface IArrayMapper <Model, Request, Response> {
  public List<Model> fromRequestListToModelList(List<Request> request);
  public List<Response> fromModelListToResponseList(List<Model> model);
}
