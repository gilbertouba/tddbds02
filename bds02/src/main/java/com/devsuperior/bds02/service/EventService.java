package com.devsuperior.bds02.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.repository.EventRepository;
import com.devsuperior.bds02.service.exceptions.ResourceNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Transactional
	public EventDTO update(Long id,EventDTO dto) {

		Event event;
		try {

			City  city = cityRepo.getOne(dto.getCityId()); 
			event = eventRepo.getOne(id);
			event.setDate(dto.getDate());
			event.setName(dto.getName());
			event.setUrl(dto.getUrl());
			event.setCity(city);

			event = eventRepo.save(event);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("id not found "+id);
		}
		return new EventDTO(event);
	}

	
	
}
