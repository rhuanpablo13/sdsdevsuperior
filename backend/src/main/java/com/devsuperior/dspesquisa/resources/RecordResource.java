package com.devsuperior.dspesquisa.resources;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;

@RestController
@RequestMapping(value = "/records")
public class RecordResource {

	@Autowired
	private RecordService recordService;
	
	
	
	@GetMapping
	public ResponseEntity<Page<RecordDTO>> findAll(
			@RequestParam(value = "dtMin", defaultValue = "") String dtMin,
			@RequestParam(value = "dtMax", defaultValue = "") String dtMax,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		Instant dtMinI = "".equals(dtMin) ? null : Instant.parse(dtMin);
		Instant dtMaxI = "".equals(dtMax) ? null : Instant.parse(dtMax);
		
		if (linesPerPage == 0)
			linesPerPage = Integer.MAX_VALUE;
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<RecordDTO> list = recordService.findByMoments(dtMinI, dtMaxI, pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto) {
		RecordDTO newDto = recordService.insert(dto);
		return ResponseEntity.ok().body(newDto);
	}
	
}
