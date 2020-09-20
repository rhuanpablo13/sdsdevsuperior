package com.devsuperior.dspesquisa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsuperior.dspesquisa.entities.Game;


public interface GameRepositorie extends JpaRepository<Game, Long>{

}
