package com.schu.caroling.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schu.caroling.models.Lyric;
import com.schu.caroling.repositories.LyricRepository;

@Service
public class LyricService {

	private final LyricRepository lyricRepository;
	
	public LyricService(LyricRepository lyricRepository) {
		this.lyricRepository = lyricRepository;
	}
	
	public Lyric saveLyric(Lyric l) {
		return lyricRepository.save(l);
	}
	
	public List<Lyric> findAllLyricsByShowId(Long id){
		return lyricRepository.findAllBySongId(id);
	}
	
	public void deleteLyric(Long id) {
		lyricRepository.deleteById(id);
	}
	
}
