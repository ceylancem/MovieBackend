package com.project.movie.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.movie.business.abstracts.ActorService;
import com.project.movie.dto.requests.ActorRequestDTO;
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

}
