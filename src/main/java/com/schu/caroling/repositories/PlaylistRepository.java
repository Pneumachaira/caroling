package com.schu.caroling.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.schu.caroling.models.Playlist;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long>{
	
	// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html

	List<Playlist> findAll();

}
