package com.schu.caroling.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schu.caroling.models.Playlist;
import com.schu.caroling.repositories.PlaylistRepository;

@Service
public class PlaylistService {

	private final PlaylistRepository playlistRepository;
	
	public PlaylistService(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
	
	public Playlist savePlaylist(Playlist p) {
		return playlistRepository.save(p);
	}
	
	public List<Playlist> findAllPlaylists(){
		return playlistRepository.findAll();
	}
	
	public Playlist findOnePlaylistById(Long id) {
		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
		if (optionalPlaylist.isPresent()) {
			return optionalPlaylist.get();
		}
		else {
			return null;
		}
	}
	
	public void deletePlaylist(Long id) {
		playlistRepository.deleteById(id);
	}

}
