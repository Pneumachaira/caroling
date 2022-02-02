package com.schu.caroling.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.schu.caroling.models.PlaylistSong;

@Repository
public interface PlaylistSongRepository extends CrudRepository<PlaylistSong, Long> {

	// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
	
	PlaylistSong findByPlaylistIdAndSongId(Long playlistId, Long songId);

	List<PlaylistSong> findAllByPlaylistId(Long id);
	
}
