package com.devsuperior.bds02.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.repository.EventRepository;
import com.devsuperior.bds02.service.exceptions.DatabaseException;
import com.devsuperior.bds02.service.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private EventRepository envRepo;

	private Object e;
	
	
	public List<CityDTO> findAllPaged(){
		
		List<City> lista = cityRepo.findAll();
		List<CityDTO> listaDTO = lista.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
        Collections.sort(listaDTO);
		
		return listaDTO;
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {
		
		City city = new City();
		city.setName(dto.getName());
		cityRepo.save(city);
		return new CityDTO(city);
	}
	
	@Transactional
	public void delete(Long id) {
		System.out.print(id);
		try {
			cityRepo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integridade referencial violada222 "+id);			
		}		
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("id not found "+id);
		}
		catch (RuntimeException e){
			throw new DatabaseException("Integridade referencial violada222 "+id);	
		}
		catch (Exception e){
			throw new DatabaseException("Integridade referencial violada222 "+id);	
		}
		catch (Throwable e){
			throw new DatabaseException("Integridade referencial violada222 "+id);	
		}
		/*finally {
			Event eve = envRepo.getOne(id);
			if (eve.getCity().getId()==id){
				throw new DatabaseException("Integridade referencial violada "+id);
			}
		}
		/*		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("id not found "+id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integridade referencial violada "+id);			
		}*/		
	}


}
