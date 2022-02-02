package com.schu.caroling.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schu.caroling.models.Song;
import com.schu.caroling.repositories.SongRepository;

@Service
public class SongService {

	private final SongRepository songRepository;
	
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	
	public List<Song> findAllSongs(){
		return songRepository.findAllByOrderByTitleAsc();
	}
	
	public Song saveSong(Song s) {
		return songRepository.save(s);
	}
	
	public Song findOneSong(Long id) {
		Optional<Song> optionalSong = songRepository.findById(id);
		if (optionalSong.isPresent()) {
			return optionalSong.get();
		}
		else {
			return null;
		}
	}
	
	public void deleteSong(Long id) {
		songRepository.deleteById(id);
	}
	
	public List<Song> findAllByStyle(String style){
		return songRepository.findAllByStyle(style);
	}
	
}
