package com.devsuperior.dspesquisa.repositories;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dspesquisa.entities.Record;


@Repository
public interface RecordRepositorie extends JpaRepository<Record, Long>{

	//coalesce -> peculiaridade do postgres
	@Query("SELECT obj FROM Record obj WHERE "
			+ "(coalesce(:dtMin, null) IS NULL OR obj.moment >= :dtMin) AND"
			+ "(coalesce(:dtMin, null) IS NULL OR obj.moment <= :dtMax)")
	Page<Record> findByMoments(Instant dtMin, Instant dtMax, Pageable pageRequest);

}
