package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Gift;

import java.util.List;


@Service
public interface IGiftService {

	public List<Gift> getAllGifts();
	public void removeGift(Gift m);
	public void removeGiftById(String id);
	public Gift addGift(Gift m);
	public void removeGifts(List<Gift> gifts);
}
