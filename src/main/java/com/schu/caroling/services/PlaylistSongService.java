package com.schu.caroling.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schu.caroling.models.PlaylistSong;
import com.schu.caroling.repositories.PlaylistSongRepository;

@Service
public class PlaylistSongService {

	private final PlaylistSongRepository playlistSongRepository;
	
	public PlaylistSongService(PlaylistSongRepository playlistSongRepository) {
		this.playlistSongRepository = playlistSongRepository;
	}
	
	public PlaylistSong savePlaylistSong(PlaylistSong p) {
		return playlistSongRepository.save(p);
	}
	
	public List<PlaylistSong> findAllPlaylistSongs(Long id){
		return playlistSongRepository.findAllByPlaylistId(id);
	}
	
}
