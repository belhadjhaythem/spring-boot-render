package com.example.demo.service;

import com.example.demo.model.Model;

import java.util.List;

public interface IModelService {

	public List<Model> getAllModels();
	public void removeModel(Model m);
	public void removeModelById(String id);
	public Model updateModel(Model m);
	public Model addModel(Model m);
}
