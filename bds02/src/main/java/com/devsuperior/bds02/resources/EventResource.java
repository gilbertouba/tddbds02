package com.devsuperior.bds02.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.service.EventService;

@RestController
@RequestMapping(value="/events")
public class EventResource {
	
	@Autowired
	EventService eventSer;
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EventDTO> insert(@PathVariable Long id, @RequestBody EventDTO dto){
		
		dto = eventSer.update(id,dto);
		return ResponseEntity.ok().body(dto);
		
	}

	
	

}
