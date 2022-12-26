package com.project.movie.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.movie.business.abstracts.ActorService;
import com.project.movie.dto.requests.ActorRequestDTO;
import com.project.movie.dto.requests.UpdateActorRequestDTO;
import com.project.movie.dto.responses.ActorResponseDTO;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

	private ActorService actorService;

	public ActorController(ActorService actorService) {
		this.actorService = actorService;
	}

	@PostMapping("/add")
	public void add(@RequestBody ActorRequestDTO createActorRequest) throws Exception {
		actorService.add(createActorRequest);
	}

	@GetMapping("/getall")
	public List<ActorResponseDTO> getAll() {
		return actorService.getAll();
	}

	@GetMapping("/getactorbymovieid")
	public List<ActorResponseDTO> getByMovieId(@RequestParam long id) {
		return actorService.getActorByMovieId(id);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		actorService.delete(id);
	}

	@PutMapping("/update")
	public void update(@RequestBody UpdateActorRequestDTO updateActorRequestDTO) throws Exception {
		actorService.update(updateActorRequestDTO);
	}

	@PutMapping("/addmovie")
	public void addMovie(@RequestParam long id, @RequestParam List<Long> movieId) throws Exception {
		actorService.addMovie(id, movieId);
	}

	@PutMapping("/deletemovie")
	public void deleteMovie(@RequestParam long id, @RequestParam List<Long> movieIds) throws Exception {
		actorService.deleteMovie(id, movieIds);
	}

}
