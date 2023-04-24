package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Model;
import com.example.demo.repository.ModelRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ModelService implements IModelService {

	@Autowired
	private ModelRepository modelRepository;
	
	@Override
	public List<Model> getAllModels() {
		Iterable<Model> iterableArchites = modelRepository.findAll();
		return StreamSupport.stream(iterableArchites.spliterator(), false)
	    .collect(Collectors.toList());
	}

	@Override
	public void removeModel(Model m) {
		modelRepository.delete(m);
	}

	@Override
	public void removeModelById(String id) {
		modelRepository.deleteById(id);
	}

	@Override
	public Model updateModel(Model m) {
		Model toUpdateModel = modelRepository.findById(m.getId()).orElse(m);
		toUpdateModel.setFile(m.getFile());
		toUpdateModel.setName(m.getName());
		toUpdateModel.setPrice(m.getPrice());
		return modelRepository.save(toUpdateModel);
	}

	@Override
	public Model addModel(Model m) {
		return modelRepository.save(m);
	}

}
