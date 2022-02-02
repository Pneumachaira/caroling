package com.schu.caroling.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.schu.caroling.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
	
	// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html

	List<Song> findAllByOrderByTitleAsc();
	
	List<Song> findAllByStyle(String style);

}
