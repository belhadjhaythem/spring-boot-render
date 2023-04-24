package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Gift;
import com.example.demo.repository.GiftRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GiftService implements IGiftService{

	@Autowired
	private GiftRepository giftRepository;
	@Override
	public List<Gift> getAllGifts() {
		Iterable<Gift> iterableArchites = giftRepository.findAll();
		return StreamSupport.stream(iterableArchites.spliterator(), false)
	    .collect(Collectors.toList());
	}

	@Override
	public void removeGift(Gift m) {
		giftRepository.delete(m);
	}

	@Override
	public void removeGiftById(String id) {
		giftRepository.deleteById(id);
	}

	@Override
	public Gift addGift(Gift m) {
		return giftRepository.save(m);
	}

	@Override
	public void removeGifts(List<Gift> gifts) {
		giftRepository.deleteAll(gifts);
	}

}
