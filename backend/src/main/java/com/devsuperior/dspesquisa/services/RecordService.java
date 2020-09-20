package com.devsuperior.dspesquisa.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.repositories.GameRepositorie;
import com.devsuperior.dspesquisa.repositories.RecordRepositorie;

@Service
public class RecordService {

	@Autowired
	private RecordRepositorie recordRepositorie;
	

	@Autowired
	private GameRepositorie gameRepositorie;

	
	
	@Transactional(readOnly = true)
	public List<RecordDTO> findAll() {
		List<Record> list = recordRepositorie.findAll();
		return list.stream().map(x -> new RecordDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public RecordDTO insert(RecordInsertDTO dto) {
		Record entity = new Record();
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now());
		
		Game game = gameRepositorie.getOne(dto.getGameId());
		entity.setGame(game);
		
		entity = recordRepositorie.save(entity);
		return new RecordDTO(entity);
	}
}
