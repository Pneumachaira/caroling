package com.schu.caroling.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.schu.caroling.models.Lyric;
import com.schu.caroling.models.Playlist;
import com.schu.caroling.models.PlaylistSong;
import com.schu.caroling.models.Song;
import com.schu.caroling.services.LyricService;
import com.schu.caroling.services.PlaylistService;
import com.schu.caroling.services.PlaylistSongService;
import com.schu.caroling.services.SongService;

@Controller
public class MainController {

	private final SongService songService;
	private final LyricService lyricService;
	private final PlaylistSongService playlistSongService;
	private final PlaylistService playlistService;
	
	public MainController(SongService songService, LyricService lyricService,
			PlaylistSongService playlistSongService, PlaylistService playlistService) {
		this.songService = songService;
		this.lyricService = lyricService;
		this.playlistSongService = playlistSongService;
		this.playlistService = playlistService;
	}
	
	////////////////////////
	// GET ROUTES
	////////////////////////
	
	@GetMapping("/")
	public String index(Model model) {
		List<Song> songs = songService.findAllSongs();
		model.addAttribute("songs", songs);
		List<Playlist> playlists = playlistService.findAllPlaylists();
		model.addAttribute("playlists", playlists);
		return "index.jsp";
	}
	
	@GetMapping("/newsong")
	public String newSong(@ModelAttribute("song") Song song) {
		return "addSong.jsp";
	}
	
	@GetMapping("/songs/{id}")
	public String oneSong(@PathVariable("id") Long id, Model model) {
		Song song = songService.findOneSong(id);
		model.addAttribute("song", song);
		return "oneSong.jsp";
	}
	
	@GetMapping("/editsong/{id}")
	public String editSong(
			@PathVariable("id") Long id,
			Model model) {
		Song song = songService.findOneSong(id);
		model.addAttribute("song", song);
		return "editSong.jsp";
	}
	
	@GetMapping("/newplaylist")
	public String newPlaylist(
			@ModelAttribute("playlist") Playlist playlist) {
		return "addPlaylist.jsp";
	}
	
	@GetMapping("/editplaylist/{id}")
	public String editPlaylist(
			@ModelAttribute("playlistSong") PlaylistSong playlistSong,
			@PathVariable("id") Long id,
			Model model) {
		Playlist playlist = playlistService.findOnePlaylistById(id);
		model.addAttribute("playlist", playlist);
		List<Song> songs = songService.findAllSongs();
		model.addAttribute("songs", songs);
		return "editPlaylist.jsp";
	}
	
	@GetMapping("/playlists/{id}")
	public String onePlaylist(@PathVariable("id") Long id, Model model) {
		Playlist playlist = playlistService.findOnePlaylistById(id);
		model.addAttribute("playlist", playlist);
		List<PlaylistSong> playlistSongs = playlistSongService.findAllPlaylistSongs(id);
		model.addAttribute("playlistSongs", playlistSongs);
		return "onePlaylist.jsp";
	}
	
	///////////////////////
	// POST ROUTES
	///////////////////////
	
	@PostMapping("/songs")
	public String submitNewSong(
			@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if (result.hasErrors()) {
			return "addSong.jsp";
		}
		else {
			songService.saveSong(song);
			return "redirect:/";
		}
	}
	
	@PostMapping("/songs/{id}")
	public String submitNewLyric(
			Model model,
			@PathVariable("id") Long id,
			@RequestParam("line") String line) {
		Song song = songService.findOneSong(id);
		Lyric lyric = new Lyric();
		lyric.setSong(song);
		lyric.setLine(line);
		lyricService.saveLyric(lyric);
		model.addAttribute("song", song);
		return "redirect:/editsong/" + id;
	}
	
	@PostMapping("/playlists")
	public String submitNewPlaylist(
			@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result) {
		if (result.hasErrors()) {
			return "addPlaylist.jsp";
		}
		else {			
			playlistService.savePlaylist(playlist);
			return "redirect:/";
		}
	}
	
	@PostMapping("/playlists/{id}")
	public String newPlaylistSong(
			@Valid @ModelAttribute("playlistSong") PlaylistSong playlistSong, BindingResult result,
			@PathVariable("id") Long id,
			Model model) {
		if (result.hasErrors()) {
			List<Song> songs = songService.findAllSongs();
			model.addAttribute("songs", songs);
			return "editPlaylist.jsp";
		}
		else {
			playlistSongService.savePlaylistSong(playlistSong);
			List<Song> songs = songService.findAllSongs();
			model.addAttribute("songs", songs);
			return "redirect:/editplaylist/" + id;
		}
	}

	/////////////////////////
	// PUT ROUTES
	/////////////////////////
	
	@PutMapping("/songs")
	public String editSong(
			@Valid @ModelAttribute("song") Song song, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "editSong.jsp";
		}
		else {
			songService.saveSong(song);
			return "redirect:/";
		}
	}
	
	@PutMapping("/playlists/{id}")
	public String editPlaylist(
			@RequestParam("name") String name,
			@PathVariable("id") Long id,
			Model model
			) {
		if (name.length() < 1) {
			return "redirect:/editplaylist/" + id;
		}
		else {
			Playlist playlist = playlistService.findOnePlaylistById(id);
			playlist.setName(name);
			playlistService.savePlaylist(playlist);
			return "redirect:/";
		}
	}
	
//	@PutMapping("/songs/{songId}/{lyricId}/up")
//	public String moveLyricUp(
//			@PathVariable("songId") Long songId,
//			@PathVariable("lyricId") Long lyricId,
//			Model model) {
//		//move lyric up function, songService
//		return "editSong.jsp";
//	}
//	
//	@PutMapping("/songs/{songId}/{lyricId}/down")
//	public String moveLyricDown(
//			@PathVariable("songId") Long songId,
//			@PathVariable("lyricId") Long lyricId,
//			Model model) {
//		//move lyric down function, songService
//		return "editSong.jsp";
//	}
	
	/////////////////////
	// DELETE ROUTES
	/////////////////////
	
	@DeleteMapping("/songs/{id}")
	public String deleteSong(
			@PathVariable("id") Long id,
			Model model) {
		songService.deleteSong(id);
		List<Song> songs = songService.findAllSongs();
		model.addAttribute("songs", songs);
		return "redirect:/";
	}
	
	@DeleteMapping("/songs/{songId}/{lyricId}")
	public String deleteLyric(
			@PathVariable("songId") Long songId,
			@PathVariable("lyricId") Long lyricId,
			Model model) {
		lyricService.deleteLyric(lyricId);
		Song song = songService.findOneSong(songId);
		model.addAttribute("song", song);
		return "redirect:/editsong/" + songId;
	}
	
//	@DeleteMapping("/playlists/{playlistId}/{songId}")
//	public String removeSongFromPlaylist(
//			@PathVariable("playlistId") Long playlistId,
//			@PathVariable("songId") Long songId) {
//		playlistSongService.deleteSongFromPlaylist(playlistId, songId);
//		return "redirect:/editplaylist/" + playlistId;
//	}
	
}
